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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/kakao")
	public Map<String, String> kakaoCallback(@RequestParam String code) {
		Map<String, String> tokens = kakaoLogin.getKaKaoAccessToken(code);
		Map<String, String> userInfo = kakaoLogin.createKakaoUser(tokens.get("accessToken"));
		Map<String, String> res = new HashMap<>();
		res.putAll(tokens);
		res.putAll(userInfo);
		
		return res;
    }
	
	@PostMapping("/signUp")
	public ResponseEntity<String> createUser(@RequestBody UserVO user) {
		System.out.println(user);
		int cnt = userService.checkId(user);

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
			// 추후 JWT발급 
			return new ResponseEntity<String>("로그인 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("아이디와 비밀번호를 확인해주세요.", HttpStatus.UNAUTHORIZED);
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
//	@GetMapping("/{id}/delete")
//	public String deleteUser(@PathVariable("id") int no, HttpSession session, RedirectAttributes redirectAttributes) {
//		userService.deleteUser(no);
//		session.invalidate();
//
//		redirectAttributes.addFlashAttribute("ok", true);
//		redirectAttributes.addFlashAttribute("msg", "회원 탈퇴 성공");
//
//		return "redirect:/";
//	}
}
