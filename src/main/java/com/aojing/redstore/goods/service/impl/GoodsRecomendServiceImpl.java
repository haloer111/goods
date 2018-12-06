package com.aojing.redstore.goods.service.impl;
import java.util.Date;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsRecomendMapper;
import com.aojing.redstore.goods.pojo.GoodsRecomend;
import com.aojing.redstore.goods.service.GoodsRecomendService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gexiao
 * @date 2018/12/6 10:01
 */
@Service
public class GoodsRecomendServiceImpl implements GoodsRecomendService {

    @Autowired
    private GoodsRecomendMapper GoodsRecomendMapper;

    //    Disrecomend[解除推荐]
    Result Disrecomend(Integer id) {
        if (id == null ) {
            return Result.createByErrorMessage("解除推荐,参数不正确");
        }
        int result = GoodsRecomendMapper.deleteByPrimaryKey(id);
        if (result > 0) {
            return Result.createBySuccessMessage("解除推荐,成功");

        }
        return Result.createByErrorMessage("解除推荐,失败");
    }


    //    Recomend[推荐商品]
    Result Recomend(String  goodsId, String sellerId, String recomender) {
        if (goodsId == null && sellerId == null && StringUtils.isNotBlank(recomender)) {
            return Result.createByErrorMessage("推荐商品,参数不正确");
        }

        GoodsRecomend GoodsRecomend = new GoodsRecomend();
        GoodsRecomend.setSellerId(sellerId);
        GoodsRecomend.setGoodsId(goodsId);
        //todo recomender类型不明确
        //GoodsRecomend.setRecomender(recomender);
        GoodsRecomend.setCreateTime(new Date());
        GoodsRecomend.setUpdateTime(new Date());

        int result = GoodsRecomendMapper.insertSelective(GoodsRecomend);
        if (result > 0) {
            return Result.createBySuccessMessage("推荐商品,成功");

        }
        return Result.createByErrorMessage("推荐商品,失败");
    }

}
