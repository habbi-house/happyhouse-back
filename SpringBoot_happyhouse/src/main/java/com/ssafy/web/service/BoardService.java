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
		Integer ord = boardDAO.getLastGroupOrd(post);
		return ord;
	}
	
	public int getGroupLayerByOriginNo(int originNo) {
		return boardDAO.getGroupLayerByOriginNo(originNo);
	}

	public void updatePost(PostVO newPost) {
		boardDAO.updatePost(newPost);
	}

	public void deletePost(int code) {
		boardDAO.deletePost(code);
	}

}
