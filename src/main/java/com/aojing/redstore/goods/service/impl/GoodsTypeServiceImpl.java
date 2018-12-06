package com.aojing.redstore.goods.service.impl;

import java.util.Date;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsInfoMapper;
import com.aojing.redstore.goods.dao.GoodsTypeMapper;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.pojo.GoodsType;
import com.aojing.redstore.goods.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author gexiao
 * @date 2018/12/4 10:25
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public Result addType(GoodsType goodsType) {
        if (goodsType == null) {
            return Result.createByErrorMessage("新增或更新商品类目参数不正确");
        }
        //新增
        int result = goodsTypeMapper.insertSelective(goodsType);
        if (result > 0) {
            return Result.createBySuccess("新增商品类目成功");
        }
        return Result.createByErrorMessage("新增商品类目失败");

    }


    public Result updateType(GoodsType goodsType) {
        if (goodsType == null) {
            return Result.createByErrorMessage("新增或更新商品类目参数不正确");
        }
        //更新
        if (goodsType.getId() != null) {
            int result = goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
            if (result > 0) {
                return Result.createBySuccess("更新商品类目成功");
            }
            return Result.createByErrorMessage("更新商品类目失败");
        }
        return Result.createByErrorMessage("更新商品类目失败");
    }

    public Result delType(Integer goodsTypeId) {
        if (goodsTypeId == null) {
            return Result.createByErrorMessage("删除商品参数不正确");
        }
        int result = goodsTypeMapper.deleteByPrimaryKey(goodsTypeId);
        if (result > 0) {
            return Result.createBySuccess("删除商品类目成功");
        }
        return Result.createByErrorMessage("删除商品类目失败");
    }




}
