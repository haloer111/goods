package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.ServerResponse;
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
    ServerResponse addType(GoodsType goodsType);

    /**
     * 删除商品类目关系信息 根据商品id,类型id
     * @param goodsId
     * @param typeId
     * @return
     */
    public ServerResponse RemoveGoodsFromType(Integer goodsId, Integer typeId);


    /**
     * 增加商品类目关系 根据商品id,类型id
     * @param goodsId
     * @param typeId
     * @return
     */
    public ServerResponse AddGoodsInType(Integer goodsId, Integer typeId);

    /**
     * 删除类目
     *
     * @param goodsTypeId
     * @return
     */
    @Deprecated
    public ServerResponse delType(Integer goodsTypeId);

    /**
     * 更新类目
     *
     * @param goodsType
     * @return
     */
    public ServerResponse updateType(GoodsType goodsType);

    /**
     * 查询全部商品-类目
     * @return
     */
    public ServerResponse<Set<GoodsType>> queryAllType();
}
