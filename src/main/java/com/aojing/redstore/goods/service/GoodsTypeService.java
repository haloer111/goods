package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.pojo.GoodsType;

import java.util.List;
import java.util.Set;

/**
 * @author gexiao
 * @date 2018/12/4 10:24
 */
public interface GoodsTypeService {
    /**
     * 增加类目
     *
     * @param goodsType
     * @return
     */
    Result addType(GoodsDto goodsDto);

    /**
     * 删除商品类目关系信息 根据商品id,类型id
     * @param goodsId
     * @param typeId
     * @return
     */
   // public Result removeGoodsFromType();


    /**
     * 增加商品类目关系 根据商品id,类型id
     * @param goodsId
     * @param typeId
     * @return
     */
   // public Result addGoodsInType();

    /**
     * 删除类目
     *
     * @param goodsTypeId
     * @return
     */
    @Deprecated
    public Result delType(Integer goodsTypeId);

    /**
     * 更新类目
     *
     * @param goodsType
     * @return
     */
    public Result updateType(GoodsType goodsType);


    /**
     * 查询商品id根据类目id
     * @param categoryId 类目id
     * @return
     */
    public Result<List<String>> queryGoodsIdByTypeId(String categoryId);

}
