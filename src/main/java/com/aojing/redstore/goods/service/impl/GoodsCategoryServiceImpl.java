package com.aojing.redstore.goods.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsCategoryMapper;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.pojo.GoodsCategory;
import com.aojing.redstore.goods.service.GoodsCategoryService;
import com.aojing.redstore.goods.vo.GoodsCategoryVo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author gexiao
 * @date 2018/12/09 下午 07:50
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public Result add(GoodsDto goodsDto) {
        GoodsCategory goodsCategory = new GoodsCategory();

        BeanUtils.copyProperties(goodsDto, goodsCategory);
        int result = goodsCategoryMapper.insertSelective(goodsCategory);
        if (result > 0) {
            return Result.createBySuccess("新增类目成功");
        }
        return Result.createByErrorMessage("新增类目失败");
    }

    @Override
    public Result<List<GoodsCategoryVo>> selectCategoryListByParentId(String parentId) {
        List<GoodsCategory> GoodsCategoryList = goodsCategoryMapper.selectByParentId(parentId);
        if (!CollectionUtils.isEmpty(GoodsCategoryList)) {
            List<GoodsCategoryVo> categoryVoList =
                    GoodsCategoryList.stream().map(e -> new GoodsCategoryVo(e.getId(), e.getName())).collect(Collectors.toList());
            return Result.createBySuccess(categoryVoList);
        }
        return Result.createByErrorMessage("查询类目失败");
    }
}
