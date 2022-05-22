package com.ssafy.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.dao.HouseDAO;
import com.ssafy.web.vo.AddressVO;
import com.ssafy.web.vo.HouseDealVO;
import com.ssafy.web.vo.HouseInfoVO;

@Service
public class HouseService {
	@Autowired
	HouseDAO houseDAO;

	public List<HouseInfoVO> getAllHousesByDong(String dongCode) {
		return houseDAO.getAllHousesByDong(dongCode);
	}

	public List<AddressVO> getBaseAddress() {
		return houseDAO.getBaseAddress();
	}

	public List<HouseDealVO> getHouseDealList(String aptCode) {
		return houseDAO.getHouseDealList(aptCode);
	}

}
