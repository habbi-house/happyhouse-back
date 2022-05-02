package com.ssafy.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.web.service.HouseService;
import com.ssafy.web.vo.HouseDealVO;

@Controller
@RequestMapping("/search")
public class HouseController {
	
	@Autowired
	HouseService houseService;
	
	@GetMapping("")
	public ModelAndView search() {
		return new ModelAndView("search");
	}
	
	@PostMapping("")
	@ResponseBody
	public List<HouseDealVO> search(String dong){
		List<HouseDealVO> list = houseService.getHouseDealList(dong);
		return list;
	}
	
	@PostMapping("/index")
	public ModelAndView search(@RequestParam Map<String, String> areaInfo) {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("areaInfo", areaInfo);
		mav.addObject("list", houseService.getHouseDealList(areaInfo.get("dong")));
		return mav;
	}
}
