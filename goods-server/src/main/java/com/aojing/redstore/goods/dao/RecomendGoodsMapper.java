package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.RecomendGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecomendGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecomendGoods record);

    int insertSelective(RecomendGoods record);

    RecomendGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecomendGoods record);

    int updateByPrimaryKey(RecomendGoods record);
}