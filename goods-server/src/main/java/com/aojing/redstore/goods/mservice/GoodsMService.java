package com.aojing.redstore.goods.mservice;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.common.GoodsInfoVo;
import com.aojing.redstore.goods.vo.GoodsSearchVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/09 下午 04:51
 */
public interface GoodsMService {

    Result addOrUpdateGoods(GoodsDto goodsDto, HttpServletRequest request);

    public Result delFile(Integer mediaId, String userId);

    Result<PageInfo> queryGoodsList(String categoryId, int pageNum, int pageSize);

    Result<List<GoodsSearchVo>> serachBykeyword(String keyword);

    public List<GoodsInfoVo> assembleGoodsVoList(List<String> goodsIdList);
}
