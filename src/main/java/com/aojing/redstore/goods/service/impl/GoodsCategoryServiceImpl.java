package com.aojing.redstore.goods.service.impl;

import java.util.Date;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsCategoryMapper;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.pojo.GoodsCategory;
import com.aojing.redstore.goods.service.GoodsCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
