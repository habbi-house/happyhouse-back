package com.ssafy.web.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.dao.HouseDAO;
import com.ssafy.web.vo.AddressVO;
import com.ssafy.web.vo.HouseDealVO;
import com.ssafy.web.vo.HouseInfoVO;
import com.ssafy.web.vo.WishInfoVO;
import com.ssafy.web.vo.WishVO;

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

	public List<HouseDealVO> getHouseDealList(BigInteger aptCode) {
		return houseDAO.getHouseDealList(aptCode);
	}

	public HouseInfoVO getHouseInfo(BigInteger aptCode) {
		return houseDAO.getHouseInfo(aptCode);
	}

	public List<WishInfoVO> getWishInfo(List<BigInteger> wishlist) {
		return houseDAO.getWishInfo(wishlist);
	}
}
