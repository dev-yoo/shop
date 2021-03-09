package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.GoodsDto;
import com.shop.service.GoodsService;
import com.shop.vo.GoodsVo;

@RestController
public class GoodsController {

	@Autowired
	GoodsService goodsService;
	
	@GetMapping("/goods/count")
	public int goodsCount(@ModelAttribute GoodsDto goodsDto) {
		return 0;
	}
	
	@GetMapping("/goods/list")
	public List<GoodsVo> goodsList(@ModelAttribute GoodsDto goodsDto) {
		return goodsService.selectGoodsList(goodsDto);
	}
	
	@PatchMapping("/goods/{goodsId}")
	public int goodsUpdate(@PathVariable String goodsId) {
		return 0;
	}
	
	@DeleteMapping("/goods/{goodsId}")
	public int goodsDelete(@PathVariable String goodsId) {
		return 0;
	}
}
