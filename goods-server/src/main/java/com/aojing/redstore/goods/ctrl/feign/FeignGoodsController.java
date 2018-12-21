package com.aojing.redstore.goods.ctrl.feign;

import com.aojing.redstore.goods.common.DecreaseStockInput;
import com.aojing.redstore.goods.common.GoodsInfoVo;
import com.aojing.redstore.goods.mservice.GoodsMService;
import com.aojing.redstore.goods.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/20 15:42
 */
@RestController
@RequestMapping("/goods")
public class FeignGoodsController {

    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    private GoodsMService goodsMService;

    @PostMapping("decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        goodsInfoService.decreaseStock(decreaseStockInputList);
    }

    @PostMapping("queryGoodsByIds")
    public List<GoodsInfoVo> queryGoodsByIds(@RequestParam("goodsIdList") List<String> goodsIdList){
        return goodsMService.assembleGoodsVoList(goodsIdList);
    }
}
