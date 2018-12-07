package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GoodsInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    List<GoodsInfo> queryByGoodsInfo(GoodsInfo goodsInfo);
}