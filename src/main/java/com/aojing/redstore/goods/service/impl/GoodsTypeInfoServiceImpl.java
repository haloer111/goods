package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsTypeInfoMapper;
import com.aojing.redstore.goods.pojo.GoodsTypeInfo;
import com.aojing.redstore.goods.service.GoodsTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/4 15:36
 */
@Service
public class GoodsTypeInfoServiceImpl implements GoodsTypeInfoService {

    @Autowired
    private GoodsTypeInfoMapper goodsTypeInfoMapper;

    @Override
    public Result<List<GoodsTypeInfo>> queryGoodsTypeInfoList() {
        List<GoodsTypeInfo> result = goodsTypeInfoMapper.queryTypeList();
        if (!result.isEmpty()) {
            return Result.createBySuccess(result);
        }
        return Result.createByErrorMessage("查询商品类目关系为空");

    }
}
