package com.ssafy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.dao.BoardDAO;
import com.ssafy.web.vo.PostVO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;

	public List<PostVO> getAllPosts() {
		return boardDAO.getAllPosts();
	}

	public PostVO getPost(int code) {
		return boardDAO.getPost(code);
	}

	public Integer getLastOriginNo() {
		return boardDAO.getLastOriginNo();
	}
	
	public int createPost(PostVO post) {
		boardDAO.createPost(post);
		return post.getCode();
	}

	public void createReplyPost(PostVO post) {
		boardDAO.createPost(post);
	}
	
	public int getLastGroupOrd(PostVO post) {
		System.out.println("Service");
		System.out.println(post.getOriginNo());
		System.out.println(post.getGroupLayer());
		Integer ord = boardDAO.getLastGroupOrd();
		System.out.println(ord);
		return ord;
	}
	
	public int getGroupLayerByOriginNo(int originNo) {
		return boardDAO.getGroupLayerByOriginNo(originNo);
	}

}
