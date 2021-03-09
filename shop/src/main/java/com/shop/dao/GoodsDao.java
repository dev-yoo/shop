package com.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.dto.GoodsDto;
import com.shop.vo.GoodsVo;

@Repository
public class GoodsDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<GoodsVo> selectGoodsListList(GoodsDto goodsDto){
		return sqlSession.selectList("Goods.selectGoodsList", goodsDto);
	}
}