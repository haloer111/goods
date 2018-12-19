package com.aojing.redstore.goods.client;

import com.aojing.redstore.goods.common.DecreaseStockInput;
import com.aojing.redstore.goods.common.GoodsInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/19 10:17
 */
@FeignClient(name = "goods")
@RequestMapping("goods")
public interface GoodsClient {

    @PostMapping("queryGoodsByIds")
    List<GoodsInfoVo> queryGoodsByIds(@RequestParam("goodsIdList") List<String> goodsIdList);

    @PostMapping("decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
