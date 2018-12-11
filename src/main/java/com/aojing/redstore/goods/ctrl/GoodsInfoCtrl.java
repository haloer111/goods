package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.mservice.GoodsMService;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsInfoService;
import com.aojing.redstore.goods.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gexiao
 * @date 2018/12/4 18:09
 */
@RestController
@RequestMapping("/goods")
public class GoodsInfoCtrl {

    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private GoodsMService goodsMService;

    @PostMapping("/addGoods")
    public Result addGoods(GoodsDto goodsDto ,HttpServletRequest request) {
        return goodsMService.addOrUpdateGoods(goodsDto,request);
    }
    @PostMapping("/queryBySelective")
    public Result queryBySelective(GoodsInfo goodsInfo) {
        return goodsInfoService.queryBySelective(goodsInfo);
    }

    @PostMapping("/update")
    public Result updateGoods(GoodsDto goodsDto,HttpServletRequest request) {
        return goodsMService.addOrUpdateGoods(goodsDto, request);
    }

    @GetMapping("/deleteGoodsFile")
    public Result deleteGoodsFile(Integer mediaId, String userId) {
        return goodsMService.delFile(mediaId,userId);
    }

    @GetMapping("/test")
    public Result  test() {
        return Result.createBySuccess("test");
    }

}
