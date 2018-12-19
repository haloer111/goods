package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.service.GoodsLikeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gexiao
 * @date 2018/12/10 10:13
 */
@RestController
public class GoodsLikerCtrl {

    @Autowired
    private GoodsLikeInfoService goodsLikeInfoService;

    @GetMapping("/giveLike")
    public Result<String> giveLike(String likerId, String goodsId) {

        return goodsLikeInfoService.giveLike(likerId, goodsId);
    }

    @GetMapping("/count")
    public Result<Integer> count(String goodsId) {
        return goodsLikeInfoService.queryLikeInfoCount(goodsId);
    }
}
