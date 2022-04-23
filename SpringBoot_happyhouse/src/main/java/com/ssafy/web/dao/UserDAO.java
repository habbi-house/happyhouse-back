package com.ssafy.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.web.vo.UserVO;

@Mapper
@Repository
public interface UserDAO {

	public void createUser(UserVO user) throws DataAccessException;

}
