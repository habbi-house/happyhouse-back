package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ssafy.web.service.BoardService;
import com.ssafy.web.service.JwtService;
import com.ssafy.web.vo.PostVO;

@RestController
@RequestMapping("/board")
@CrossOrigin("http://localhost:8080")
public class BoardController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@Autowired
	private JwtService jwtService;
	
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

	@PostMapping("/create")
	public ResponseEntity<String> createPost(@RequestBody PostVO post) {
		if(post.getEmail().equals(jwtService.getMemberEmail())) {
			int originNo = post.getOriginNo();
			
			if (originNo > 0) { // 답글 작성하기
				int groupOrd = boardService.getLastGroupOrd(post);
				post.setGroupOrd(groupOrd + 1);
				post.setGroupLayer(post.getGroupLayer() + 1);
			} else { // 원글 작성하기
				// OriginNo 값 저장
				Integer lastOriginNo = boardService.getLastOriginNo();
				post.setOriginNo(lastOriginNo == null ? 1 : lastOriginNo + 1);
			}
			
			int code = boardService.createPost(post);
			
			return new ResponseEntity<String>("글 작성 성공", HttpStatus.OK);			
		} else {
			return new ResponseEntity<String>("정상적이지 않은 요청입니다.", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updatePost(@RequestBody PostVO newPost) {
		if(newPost.getEmail().equals(jwtService.getMemberEmail())) {
			boardService.updatePost(newPost);
		
			return new ResponseEntity<String>("글 수정 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("정상적이지 않은 요청입니다.", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deletePost(@RequestBody int code) {
		PostVO post = boardService.getPost(code);
		if(post.getEmail().equals(jwtService.getMemberEmail())) {
			boardService.deletePost(code);
		
			return new ResponseEntity<String>("글 삭제 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("정상적이지 않은 요청입니다.", HttpStatus.UNAUTHORIZED);
		}
	}
	
}
