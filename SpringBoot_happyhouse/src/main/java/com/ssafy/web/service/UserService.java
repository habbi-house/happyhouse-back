package com.ssafy.web.service;

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

}
