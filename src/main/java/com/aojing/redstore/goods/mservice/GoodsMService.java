package com.aojing.redstore.goods.mservice;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gexiao
 * @date 2018/12/09 下午 04:51
 */
public interface GoodsMService {

    Result addOrUpdateGoods(GoodsDto goodsDto, HttpServletRequest request);

    public Result delFile(Integer mediaId, String userId);

    Result<PageInfo> queryGoodsList(String categoryId, int pageNum, int pageSize);
}
