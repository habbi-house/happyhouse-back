package com.ssafy.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.web.vo.HouseDealVO;

@Mapper
@Repository
public interface HouseDAO {

	List<HouseDealVO> getHouseDealList(String dong) throws DataAccessException;
	
}
