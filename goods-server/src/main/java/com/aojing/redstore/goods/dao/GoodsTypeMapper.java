package com.aojing.redstore.goods.dao;

import com.aojing.redstore.goods.pojo.GoodsExamineInfo;
import com.aojing.redstore.goods.pojo.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsTypeMapper  extends BaseMapper<GoodsType> {
    int deleteByPrimaryKey(String id);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);

    //manual
    List<String> queryGoodsIdByTypeId(String categoryId);

    List<GoodsType> queryAllCategory(@Param("typeId") String typeId);
}