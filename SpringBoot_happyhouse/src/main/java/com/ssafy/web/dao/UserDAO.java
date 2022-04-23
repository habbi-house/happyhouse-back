package com.ssafy.web.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.web.vo.UserVO;

@Mapper
@Repository
public interface UserDAO {

	public void createUser(UserVO user) throws DataAccessException;

	public int checkId(UserVO user) throws DataAccessException;

	public UserVO getUser(Map<String, String> map) throws DataAccessException;

	public UserVO getUserByNo(int no) throws DataAccessException;

}
