package com.ssafy.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.web.service.HouseService;
import com.ssafy.web.vo.AddressVO;
import com.ssafy.web.vo.HouseDealVO;
import com.ssafy.web.vo.HouseInfoVO;

@RestController
@RequestMapping("/search")
@CrossOrigin("*")
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
	
	@GetMapping("/:id")
	public ResponseEntity<List<HouseDealVO>> searchHouseDealList(@RequestBody String aptCode) {
		List<HouseDealVO> houseDealList = houseService.getHouseDealList(aptCode);
		return new ResponseEntity<List<HouseDealVO>>(houseDealList, HttpStatus.OK);
	}
	
	
//	@GetMapping("")
//	public ModelAndView search() {
//		return new ModelAndView("search");
//	}
//	
//	@PostMapping("")
//	@ResponseBody
//	public List<HouseDealVO> search(String dong){
//		List<HouseDealVO> list = houseService.getHouseDealList(dong);
//		return list;
//	}
	
//	@PostMapping("/index")
//	public ModelAndView search(@RequestParam Map<String, String> areaInfo) {
//		ModelAndView mav = new ModelAndView("search");
//		mav.addObject("areaInfo", areaInfo);
//		mav.addObject("list", houseService.getHouseDealList(areaInfo.get("dong")));
//		return mav;
//	}
}
