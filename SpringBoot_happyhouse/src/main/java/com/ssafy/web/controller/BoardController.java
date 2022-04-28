package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.web.service.BoardService;
import com.ssafy.web.vo.PostVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@GetMapping("")
	public ModelAndView board() {
		List<PostVO> postList = boardService.getAllPosts();

		ModelAndView mav = new ModelAndView("board/PostList");
		mav.addObject("postList", postList);

		return mav;
	}

	@GetMapping("/{id}")
	public ModelAndView getPost(@PathVariable String id) {
		PostVO post = boardService.getPost(Integer.parseInt(id));

		ModelAndView mav = new ModelAndView("board/post");
		mav.addObject("post", post);

		return mav;
	}

}
