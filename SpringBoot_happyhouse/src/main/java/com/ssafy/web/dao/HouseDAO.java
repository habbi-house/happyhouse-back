package com.ssafy.web.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.web.vo.AddressVO;
import com.ssafy.web.vo.HouseDealVO;
import com.ssafy.web.vo.HouseInfoVO;
import com.ssafy.web.vo.WishInfoVO;

@Mapper
@Repository
public interface HouseDAO {

	List<AddressVO> getBaseAddress() throws DataAccessException;
	
	String getDongcode(AddressVO address) throws DataAccessException;

	List<HouseInfoVO> getAllHousesByDong(String dongCode) throws DataAccessException;

	List<HouseDealVO> getHouseDealList(BigInteger aptCode) throws DataAccessException;

	HouseInfoVO getHouseInfo(BigInteger aptCode);
	
	List<WishInfoVO> getWishInfo(List<BigInteger> wishlist);
	
}
