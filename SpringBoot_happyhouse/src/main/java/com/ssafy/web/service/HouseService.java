package com.ssafy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.dao.HouseDAO;
import com.ssafy.web.vo.HouseDealVO;

@Service
public class HouseService {
	@Autowired
	HouseDAO houseDAO;

	public List<HouseDealVO> getHouseDealList(String code) {
		return houseDAO.getHouseDealList(code);
	}

}
