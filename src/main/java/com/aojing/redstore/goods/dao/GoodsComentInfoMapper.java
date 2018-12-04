package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsComentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsComentInfo record);

    int insertSelective(GoodsComentInfo record);

    GoodsComentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsComentInfo record);

    int updateByPrimaryKey(GoodsComentInfo record);
}