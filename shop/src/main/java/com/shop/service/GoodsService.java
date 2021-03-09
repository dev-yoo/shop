package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.GoodsDao;
import com.shop.dto.GoodsDto;
import com.shop.vo.GoodsVo;

@Service
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	public List<GoodsVo> selectGoodsList(GoodsDto goodsDto) {
		return goodsDao.selectGoodsListList(goodsDto);
	}

}