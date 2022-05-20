package com.ssafy.web.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.web.service.BoardService;
import com.ssafy.web.vo.PostVO;
import com.ssafy.web.vo.UserVO;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@GetMapping("")
	public ResponseEntity<List<PostVO>> getAllPosts() {
		List<PostVO> postList = boardService.getAllPosts();
		return new ResponseEntity<List<PostVO>>(postList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostVO> getPost(@PathVariable("id") int code) throws NoHandlerFoundException {
		PostVO post = boardService.getPost(code);
		return new ResponseEntity<PostVO>(post, HttpStatus.OK);
	}

//	@GetMapping("/create")
//	public ModelAndView createPostView(
//			@RequestParam(value = "originNo", required = false, defaultValue = "0") int originNo,
//			@RequestParam(value = "groupLayer", required = false, defaultValue = "0") int groupLayer,
//			HttpSession session) {
//		ModelAndView mav = new ModelAndView("board/createPost");
//
//		String token = UUID.randomUUID().toString();
//		session.setAttribute("CSRF_TOKEN", token);
//
//		if (originNo > 0) { // 답글 작성하기
//			mav.addObject("originNo", originNo);
//			mav.addObject("groupLayer", groupLayer);
//		}
//		return mav;
//	}

//	@PostMapping("/create")
//	public String createPost(PostVO post, HttpSession session, RedirectAttributes redirectAttributes) {
//		int originNo = post.getOriginNo();
//
//		// 작성자 ID 저장
//		UserVO user = (UserVO) session.getAttribute("user");
//		post.setWriter(user.getId());
//
//		if (originNo > 0) { // 답글 작성하기
//			int groupOrd = boardService.getLastGroupOrd(post);
//			post.setGroupOrd(groupOrd + 1);
//		} else { // 원글 작성하기
//			// OriginNo 값 저장
//			Integer lastOriginNo = boardService.getLastOriginNo();
//			post.setOriginNo(lastOriginNo == null ? 1 : lastOriginNo + 1);
//		}
//
//		int code = boardService.createPost(post);
//
//		redirectAttributes.addFlashAttribute("ok", true);
//		redirectAttributes.addFlashAttribute("msg", "글 등록 성공");
//		return "redirect:/board/" + code;
//
//	}
//
//	@GetMapping("/{id}/update")
//	public ModelAndView updatePostView(@PathVariable("id") int code, HttpSession session,
//			RedirectAttributes redirectAttributes) {
//		UserVO user = (UserVO) session.getAttribute("user");
//		PostVO post = boardService.getPost(code);
//
//		ModelAndView mav = new ModelAndView();
//		if (post != null && user != null && post.getWriter().equals(user.getId())) {
//			mav.setViewName("board/updatePost");
//			mav.addObject("post", post);
//			String token = UUID.randomUUID().toString();
//			session.setAttribute("CSRF_TOKEN", token);
//		} else {
//			mav.setViewName("redirect:/");
//			redirectAttributes.addFlashAttribute("ok", false);
//			redirectAttributes.addFlashAttribute("msg", "접근 권한이 없습니다.");
//		}
//
//		return mav;
//	}
//
//	@PostMapping("/{id}/update")
//	public String updatePost(@PathVariable("id") int code, PostVO newPost, HttpSession session,
//			RedirectAttributes redirectAttributes) {
//		UserVO user = (UserVO) session.getAttribute("user");
//
//		if (newPost != null && user != null && newPost.getWriter().equals(user.getId())) {
//			newPost.setCode(code);
//			boardService.updatePost(newPost);
//
//			redirectAttributes.addFlashAttribute("ok", true);
//			redirectAttributes.addFlashAttribute("msg", "글 수정 성공");
//
//			return "redirect:/board/" + code;
//		}
//
//		redirectAttributes.addFlashAttribute("ok", false);
//		redirectAttributes.addFlashAttribute("msg", "접근 권한이 없습니다.");
//		return "redirect:/";
//	}
//
//	@PostMapping("/{id}/delete")
//	public String deletePost(@PathVariable("id") int code, HttpSession session, RedirectAttributes redirectAttributes) {
//		UserVO user = (UserVO) session.getAttribute("user");
//		PostVO post = boardService.getPost(code);
//
//		if (post != null && user != null && post.getWriter().equals(user.getId())) {
//			boardService.deletePost(code);
//
//			redirectAttributes.addFlashAttribute("ok", true);
//			redirectAttributes.addFlashAttribute("msg", "글 삭제 성공");
//			return "redirect:/board";
//		}
//
//		redirectAttributes.addFlashAttribute("ok", false);
//		redirectAttributes.addFlashAttribute("msg", "접근 권한이 없습니다.");
//		return "redirect:/";
//	}
}
