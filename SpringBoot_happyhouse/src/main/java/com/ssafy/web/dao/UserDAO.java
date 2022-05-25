package com.ssafy.web.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.web.vo.TokenVO;
import com.ssafy.web.vo.UserVO;
import com.ssafy.web.vo.WishVO;

@Mapper
@Repository
public interface UserDAO {

	public void createUser(UserVO user) throws DataAccessException;

	public int checkEmail(UserVO user) throws DataAccessException;

	public UserVO getUser(Map<String, String> map) throws DataAccessException;

	public UserVO getUserByNo(int no) throws DataAccessException;

	public void updateUser(UserVO user) throws DataAccessException;

	public void deleteUser(int no) throws DataAccessException;
	
	public TokenVO getToken(int no) throws DataAccessException;
	
	public int addToken(TokenVO tokenVO) throws DataAccessException;
	
	public void deleteToken(int no) throws DataAccessException;
	
	public void addWish(WishVO wishVO) throws DataAccessException;
	
	public void deleteWish(WishVO wishVO) throws DataAccessException;
	
	public List<BigInteger> getWishlist(String email) throws DataAccessException;

}
