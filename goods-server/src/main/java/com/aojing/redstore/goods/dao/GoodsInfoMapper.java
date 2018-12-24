package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GoodsInfoMapper extends BaseMapper<GoodsInfo> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);



    //    manual
    List<GoodsInfo> queryByGoodsInfo(GoodsInfo goodsInfo);

    List<GoodsInfo> queryByIdList(@Param("goodsIdList") List<String> goodsIdList);

    List<GoodsInfo> serachBykeyword(String keyword);
}