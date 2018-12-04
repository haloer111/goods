package com.aojing.redstore.goods.service.impl;

import java.util.Date;

import com.aojing.redstore.goods.common.ServerResponse;
import com.aojing.redstore.goods.dao.GoodsInfoMapper;
import com.aojing.redstore.goods.dao.GoodsTypeInfoMapper;
import com.aojing.redstore.goods.dao.GoodsTypeMapper;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.pojo.GoodsType;
import com.aojing.redstore.goods.pojo.GoodsTypeInfo;
import com.aojing.redstore.goods.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
    private GoodsTypeInfoMapper goodsTypeInfoMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public ServerResponse addType(GoodsType goodsType) {
        if (goodsType == null) {
            return ServerResponse.createByErrorMessage("新增或更新商品类目参数不正确");
        }
        //新增
        int result = goodsTypeMapper.insertSelective(goodsType);
        if (result > 0) {
            return ServerResponse.createBySuccess("新增商品类目成功");
        }
        return ServerResponse.createByErrorMessage("新增商品类目失败");

    }


    public ServerResponse updateType(GoodsType goodsType) {
        if (goodsType == null) {
            return ServerResponse.createByErrorMessage("新增或更新商品类目参数不正确");
        }
        //更新
        if (goodsType.getId() != null) {
            int result = goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
            if (result > 0) {
                return ServerResponse.createBySuccess("更新商品类目成功");
            }
            return ServerResponse.createByErrorMessage("更新商品类目失败");
        }
        return ServerResponse.createByErrorMessage("更新商品类目失败");
    }

    public ServerResponse delType(Integer goodsTypeId) {
        if (goodsTypeId == null) {
            return ServerResponse.createByErrorMessage("删除商品参数不正确");
        }
        int result = goodsTypeMapper.deleteByPrimaryKey(goodsTypeId);
        if (result > 0) {
            return ServerResponse.createBySuccess("删除商品类目成功");
        }
        return ServerResponse.createByErrorMessage("删除商品类目失败");
    }


    public ServerResponse<Set<GoodsType>> queryAllType() {
        GoodsTypeInfo goodsTypeInfo = new GoodsTypeInfo();
        Set<GoodsType> result = goodsTypeMapper.queryTypeSet();
        if (result.size() > 0) {
            return ServerResponse.createBySuccess(result);
        }
        return ServerResponse.createByErrorMessage("查询商品类目失败");
    }

    public ServerResponse AddGoodsInType(Integer goodsId, Integer typeId) {
        if (typeId == null || goodsId == null) {
            return ServerResponse.createByErrorMessage("增加商品类目关系参数错误");
        }
        //todo 此方法应该存在goodstypeinfoService中
        //1. 查询goodsType判断是否存在typeid,商品表中是否存在
        int resultType = goodsTypeMapper.selectByTypeId(typeId);
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId);
        //2. 新增商品类目表
        if (resultType > 0 && goodsInfo != null) {
            GoodsTypeInfo goodsTypeInfo = new GoodsTypeInfo();
            goodsTypeInfo.setGoodsId(goodsId);
            goodsTypeInfo.setTypeId(typeId);
            goodsTypeInfo.setCreateTime(new Date());
            goodsTypeInfo.setUpdateTime(new Date());
            int addResult = goodsTypeInfoMapper.insertSelective(goodsTypeInfo);
            if (addResult > 0) {
                return ServerResponse.createBySuccess("增加商品类目关系成功");
            }
        }
        return ServerResponse.createByErrorMessage("查询该类目不存在");
    }

    public ServerResponse RemoveGoodsFromType(Integer goodsId, Integer typeId) {
        if (typeId == null || goodsId == null) {
            return ServerResponse.createByErrorMessage("删除商品类目关系参数错误");
        }
        //1. 查询goodsType判断是否存在typeid
        int result = goodsTypeInfoMapper.deleteByTypeId(goodsId, typeId);
        //2. 新增商品类目表
        if (result > 0) {
            return ServerResponse.createBySuccess("删除商品类目关系成功");
        }
        return ServerResponse.createByErrorMessage("删除商品类目关系失败");
    }


}
