package com.ssafy.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.web.vo.PostVO;

@Mapper
@Repository
public interface BoardDAO {

	public List<PostVO> getAllPosts() throws DataAccessException;

	public PostVO getPost(int code) throws DataAccessException;

	public void createPost(PostVO post) throws DataAccessException;
	
	public Integer getLastOriginNo() throws DataAccessException;
	
	public Integer getLastGroupOrd(PostVO post) throws DataAccessException;
	
	public int getGroupLayerByOriginNo(int originNo) throws DataAccessException;

	public void updatePost(PostVO newPost) throws DataAccessException;

	public void deletePost(int code) throws DataAccessException;
	
}
