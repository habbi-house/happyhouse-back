package com.ssafy.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.dao.UserDAO;
import com.ssafy.web.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;

	public void createUser(UserVO user) {
		userDAO.createUser(user);
	}

	public int checkId(UserVO user) {
		return userDAO.checkId(user);
	}

	public UserVO getUser(Map<String, String> map) {
		return userDAO.getUser(map);
	}

	public UserVO getUserByNo(int no) {
		return userDAO.getUserByNo(no);
	}

	public void updateUser(UserVO user) {
		userDAO.updateUser(user);
	}

	public void deleteUser(int no) {
		userDAO.deleteUser(no);
	}
}
