package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.vo.GoodsCategoryVo;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/09 下午 07:49
 */
public interface GoodsCategoryService {


    Result add(GoodsDto goodsDto);

    Result<List<GoodsCategoryVo>> selectCategoryListByParentId(String parentId);
}
