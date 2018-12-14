package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.common.SearchHistoryAndAutoMatchs;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.mservice.GoodsMService;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsInfoService;
import com.aojing.redstore.goods.service.GoodsTypeService;
import com.aojing.redstore.goods.vo.GoodsInfoVo;
import com.aojing.redstore.goods.vo.GoodsSearchVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

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

    @Autowired
    private SearchHistoryAndAutoMatchs searchService;


    @PostMapping("/addGoods")
    public Result addGoods(GoodsDto goodsDto, HttpServletRequest request) {
        return goodsMService.addOrUpdateGoods(goodsDto, request);
    }

    @PostMapping("/queryBySelective")
    public Result queryBySelective(GoodsInfo goodsInfo) {
        return goodsInfoService.queryBySelective(goodsInfo);
    }

    @PostMapping("/update")
    public Result updateGoods(GoodsDto goodsDto, HttpServletRequest request) {
        return goodsMService.addOrUpdateGoods(goodsDto, request);
    }

    @GetMapping("/deleteGoodsFile")
    public Result deleteGoodsFile(Integer mediaId, String userId) {
        return goodsMService.delFile(mediaId, userId);
    }

    @GetMapping("/queryGoodsList")
    public Result<PageInfo> queryGoodsList(@RequestParam(defaultValue = "1") String categoryId,
                                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "1") int pageSize,
                                           @RequestParam(value = "test") String test
    ) {
        Result<PageInfo> goodsList = goodsMService.queryGoodsList(categoryId, pageNum, pageSize);
        return goodsList;
    }

    @GetMapping("serach")
    public Result<List> serach(@RequestParam("keyword") String keyword) {
        List<String> listKeyword = searchService.getDefaultAutoMatchs(keyword);
        return Result.createBySuccess(listKeyword);
    }

    @GetMapping("serachBykeyword")
    public Result<List<GoodsSearchVo>> serachBykeyword(@RequestParam("keyword") String keyword) {
        Result<List<GoodsSearchVo>> listKeyword = goodsMService.serachBykeyword(keyword);
        return listKeyword;
    }


}
