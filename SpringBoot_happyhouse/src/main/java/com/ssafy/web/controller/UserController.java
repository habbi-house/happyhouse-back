package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.web.service.JwtService;
import com.ssafy.web.service.JwtServiceImpl;
import com.ssafy.web.service.KakaoLogin;
import com.ssafy.web.service.UserService;
import com.ssafy.web.vo.PostVO;
import com.ssafy.web.vo.UserVO;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	KakaoLogin kakaoLogin;
	
	@Autowired
	JwtService jwtService;
	
	@GetMapping("/kakao")
	public Map<String, Object> kakaoCallback(@RequestParam String code) {
		Map<String, String> tokens = kakaoLogin.getKaKaoAccessToken(code);
		UserVO userInfo = kakaoLogin.createKakaoUser(tokens.get("accessToken"));
		String token = jwtService.create("user", userInfo, "user");
		Map<String, Object> res = new HashMap<>();
		res.put("tokens", tokens);
		res.put("token", token);
		
		return res;
    }
	
	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody UserVO user) {
		System.out.println(user);
		int cnt = userService.checkEmail(user);

		if (cnt == 0) {
			userService.createUser(user);
			return new ResponseEntity<String>("회원 가입 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("이미 가입된 회원입니다.", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> map) {
		UserVO user = userService.getUser(map);
		if (user != null) {
			user.setPassword("");
			String token = jwtService.create("user", user, "user");
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("아이디와 비밀번호를 확인해주세요.", HttpStatus.UNAUTHORIZED);
		}

	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestBody int no) {
		if(no == jwtService.getMemberNo()) {
			userService.deleteUser(no);
			// 유저의 정보를 참조하고 있는 테이블도 삭제해줘야 한다.
			return new ResponseEntity<String>("정상적으로 탈퇴되었습니다.",HttpStatus.OK);			
		} else {
			return new ResponseEntity<String>("정상적이지 않은 요청입니다.", HttpStatus.UNAUTHORIZED);
		}
	}
//
//	@GetMapping("/logout")
//	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
//		session.invalidate();
//
//		redirectAttributes.addFlashAttribute("ok", true);
//		redirectAttributes.addFlashAttribute("msg", "로그아웃 되었습니다.");
//
//		return "redirect:/";
//	}
//
//
//
//	@GetMapping("/{id}")
//	public ModelAndView myPage(@PathVariable("id") int no, HttpSession session, RedirectAttributes redirectAttributes) {
//		ModelAndView mav = new ModelAndView();
//		UserVO user = userService.getUserByNo(no);
//		
//		mav.addObject("user", user);
//		mav.setViewName("user/mypage");
//
//		return mav;
//	}
//
//	@PostMapping("/{id}/update")
//	@ResponseBody
//	public String updateUser(@PathVariable("id") int no, UserVO newUser) {
//		userService.updateUser(newUser);
//
//		JSONObject json = new JSONObject();
//		json.put("ok", true);
//		json.put("msg", "회원 정보 수정 성공");
//
//		return json.toString();
//	}
//
}
