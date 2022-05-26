package com.ssafy.web.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.service.HouseService;
import com.ssafy.web.vo.AddressVO;
import com.ssafy.web.vo.HouseDealVO;
import com.ssafy.web.vo.HouseInfoVO;
import com.ssafy.web.vo.WishInfoVO;
import com.ssafy.web.vo.WishVO;

@RestController
@RequestMapping("/search")
@CrossOrigin("http://localhost:8080")
public class HouseController {
	
	@Autowired
	HouseService houseService;
	
	@GetMapping("")
	public ResponseEntity<List<AddressVO>> getBaseAddress() {
		List<AddressVO> address = houseService.getBaseAddress();
		return new ResponseEntity<List<AddressVO>>(address, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<List<HouseInfoVO>> getAllHousesByDong(@RequestBody String dongCode) {
		List<HouseInfoVO> houseInfoList = houseService.getAllHousesByDong(dongCode);
		return new ResponseEntity<List<HouseInfoVO>>(houseInfoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<HouseDealVO>> getAllHouseDeals(@PathVariable("id") BigInteger aptCode) {
		List<HouseDealVO> houseDealList = houseService.getHouseDealList(aptCode);
		return new ResponseEntity<List<HouseDealVO>>(houseDealList, HttpStatus.OK);
	}
	
	@GetMapping("/info/{id}")
	public ResponseEntity<HouseInfoVO> getHouseInfo(@PathVariable("id") String aptCode) {
		System.out.println(aptCode);
		HouseInfoVO houseInfoVO = houseService.getHouseInfo(new BigInteger(aptCode));
		
		return new ResponseEntity<HouseInfoVO>(houseInfoVO, HttpStatus.OK);
	}
	
	@PostMapping("/wishinfo")
	public ResponseEntity<List<WishInfoVO>> getHouseInfo(@RequestBody List<BigInteger> wishlist) {
		List<WishInfoVO> wishInfo = new ArrayList<>();
		if(wishlist.size() != 0) {
			wishInfo = houseService.getWishInfo(wishlist);
			return new ResponseEntity<List<WishInfoVO>>(wishInfo, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<WishInfoVO>>(wishInfo, HttpStatus.ACCEPTED);
			
	}
	
//	@PostMapping("/index")
//	public ModelAndView search(@RequestParam Map<String, String> areaInfo) {
//		ModelAndView mav = new ModelAndView("search");
//		mav.addObject("areaInfo", areaInfo);
//		mav.addObject("list", houseService.getHouseDealList(areaInfo.get("dong")));
//		return mav;
//	}
}
