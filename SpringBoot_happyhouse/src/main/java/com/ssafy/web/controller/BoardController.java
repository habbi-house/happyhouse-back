package com.ssafy.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.web.service.BoardService;
import com.ssafy.web.vo.PostVO;
import com.ssafy.web.vo.UserVO;

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

	@GetMapping("/create")
	public ModelAndView createPostView(@RequestParam(value = "originNo", required = false, defaultValue = "0") int originNo,
			@RequestParam(value = "groupLayer", required = false) int groupLayer) {
		ModelAndView mav = new ModelAndView("board/createPost");
		if (originNo > 0) { // 답글 작성하기
			mav.addObject("originNo", originNo);
			mav.addObject("groupLayer", groupLayer);
		}
		return mav;
	}

	@PostMapping("/create")
	public String createPost(PostVO post, HttpSession session, RedirectAttributes redirectAttributes) {
		int originNo = post.getOriginNo();

		if (originNo > 0) { // 답글 작성하기
			// groupOrd 값 저장
			System.out.println(post.getOriginNo());
			System.out.println(post.getGroupLayer());

			int groupOrd = boardService.getLastGroupOrd(post);
			post.setGroupOrd(groupOrd + 1);
		} else { // 원글 작성하기
			// 작성자 ID 저장
			UserVO user = (UserVO) session.getAttribute("user");
			post.setWriter(user.getId());

			// OriginNo 값 저장
			Integer lastOriginNo = boardService.getLastOriginNo();
			post.setOriginNo(lastOriginNo == null ? 1 : lastOriginNo + 1);
		}

		int code = boardService.createPost(post);

		redirectAttributes.addFlashAttribute("ok", true);
		redirectAttributes.addFlashAttribute("msg", "글 등록 성공");

		return "redirect:/board/" + code;
	}

}
