package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.common.SearchHistoryAndAutoMatchs;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.mservice.GoodsMService;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsInfoService;
import com.aojing.redstore.goods.service.GoodsTypeService;
import com.aojing.redstore.goods.common.GoodsInfoVo;
import com.aojing.redstore.goods.vo.GoodsSearchVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/4 18:09
 */
@RestController
@RequestMapping("/api/goods")
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
    public List<GoodsInfo> queryBySelective(GoodsInfo goodsInfo) {
        return goodsInfoService.queryBySelective(goodsInfo);
    }

    @PostMapping("/update")
    public Result updateGoods(GoodsDto goodsDto, HttpServletRequest request) {
        return goodsMService.addOrUpdateGoods(goodsDto, request);
    }

    @GetMapping("/deleteGoodsFile")
    public Result deleteGoodsFile(String mediaId, String userId) {
        return goodsMService.delFile(mediaId, userId);
    }

    @GetMapping("/queryGoodsList")
    public Result<PageInfo> queryGoodsList(@RequestParam(defaultValue = "1") String categoryId,
                                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "1") int pageSize
    ) {
        Result<PageInfo> goodsList = goodsMService.queryGoodsList(categoryId, pageNum, pageSize);
        return goodsList;
    }

    @GetMapping("serach")
    public Result<List> serach(@RequestParam("keyword") String keyword) {
        List<String> listKeyword = searchService.getDefaultAutoMatchs(keyword.trim());
        return Result.createBySuccess(listKeyword);
    }

    @GetMapping("serachBykeyword")
    public Result<List<GoodsSearchVo>> serachBykeyword(@RequestParam("keyword") String keyword) {
        Result<List<GoodsSearchVo>> listKeyword = goodsMService.serachBykeyword(keyword);
        return listKeyword;
    }

    @GetMapping("getLatestRecord")
    public Result<List<String>> getLatestRecord(@RequestParam("userId") String userId) {
        List<String> list = searchService.getLatestRecord(userId);
        return Result.createBySuccess(list);
    }


    @PostMapping("/queryGoods")
    public Result<List<GoodsInfoVo>> queryGoodsByIds(@RequestBody List<String> goodsIdList) {
        return Result.createBySuccess(goodsMService.assembleGoodsVoList(goodsIdList));
    }

    @GetMapping("/queryStoreGoods")
    public Result<PageInfo> queryStoreGoods(@RequestParam(value = "categoryId", defaultValue = "") String categoryId,
                                            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "1") int pageSize) {
        return goodsMService.queryStoreGoodsList(categoryId, pageNum, pageSize);
    }

    @GetMapping("/queryHotGoods")
    public Result<PageInfo> queryHotGoods(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return goodsMService.queryHotGoodsList( pageNum, pageSize);
    }
}
