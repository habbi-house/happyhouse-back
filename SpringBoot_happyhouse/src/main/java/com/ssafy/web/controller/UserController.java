package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.web.service.KakaoLogin;
import com.ssafy.web.service.UserService;
import com.ssafy.web.vo.UserVO;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	KakaoLogin kakaoLogin;
	
	@ResponseBody
	@GetMapping("/kakao")
	public Map<String, String> kakaoCallback(@RequestParam String code) {
		Map<String, String> tokens = kakaoLogin.getKaKaoAccessToken(code);
		Map<String, String> userInfo = kakaoLogin.createKakaoUser(tokens.get("accessToken"));
		Map<String, String> res = new HashMap<>();
		res.putAll(tokens);
		res.putAll(userInfo);
		
		return res;
    }
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, HttpSession session,
			RedirectAttributes redirectAttributes) {
		UserVO user = userService.getUser(map);
		if (user != null) {
			session.setAttribute("user", user);

			redirectAttributes.addFlashAttribute("ok", true);
			redirectAttributes.addFlashAttribute("msg", user.getName() + " 님 안녕하세요.");

			return "redirect:/";
		}

		redirectAttributes.addFlashAttribute("ok", false);
		redirectAttributes.addFlashAttribute("msg", "잘못된 아이디 혹은 비밀번호입니다.");

		return "redirect:/user/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		session.invalidate();

		redirectAttributes.addFlashAttribute("ok", true);
		redirectAttributes.addFlashAttribute("msg", "로그아웃 되었습니다.");

		return "redirect:/";
	}

	@GetMapping("/signIn")
	public String signIn() {
		return "user/signIn";
	}

	@PostMapping("/signIn")
	public String createUser(UserVO user, RedirectAttributes redirectAttributes) {
		System.out.println(user);

		int cnt = userService.checkId(user);

		if (cnt == 0) {
			userService.createUser(user);

			redirectAttributes.addFlashAttribute("ok", true);
			redirectAttributes.addFlashAttribute("msg", "회원가입 성공");

			return "redirect:/user/login";
		}

		redirectAttributes.addFlashAttribute("ok", false);
		redirectAttributes.addFlashAttribute("msg", "이미 존재하는 아이디입니다.");

		return "redirect:/user/signIn";
	}

	@GetMapping("/{id}")
	public ModelAndView myPage(@PathVariable("id") int no, HttpSession session, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		UserVO user = userService.getUserByNo(no);
		
		mav.addObject("user", user);
		mav.setViewName("user/mypage");

		return mav;
	}

	@PostMapping("/{id}/update")
	@ResponseBody
	public String updateUser(@PathVariable("id") int no, UserVO newUser) {
		userService.updateUser(newUser);

		JSONObject json = new JSONObject();
		json.put("ok", true);
		json.put("msg", "회원 정보 수정 성공");

		return json.toString();
	}

	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id") int no, HttpSession session, RedirectAttributes redirectAttributes) {
		userService.deleteUser(no);
		session.invalidate();

		redirectAttributes.addFlashAttribute("ok", true);
		redirectAttributes.addFlashAttribute("msg", "회원 탈퇴 성공");

		return "redirect:/";
	}
}
