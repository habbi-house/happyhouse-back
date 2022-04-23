package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.web.service.UserService;
import com.ssafy.web.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/signIn")
	public String signIn() {
		return "user/signIn";
	}
	
	@PostMapping("signIn")
	public String createUser(UserVO user, RedirectAttributes redirectAttributes) {
		System.out.println(user);
		
		userService.createUser(user);
		
		redirectAttributes.addFlashAttribute("ok", true);
		redirectAttributes.addFlashAttribute("msg", "회원가입 성공");
		
		return "redirect:/user/login";
	}
}
