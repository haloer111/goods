package com.aojing.redstore.goods.common;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author gexiao
 * @date 2018/12/11 17:57
 */
@Data
public class GoodsInfoVo {
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 点赞数
     */
    private int likeCount;
    /**
     * 评论条数
     */
    private int commentCount;
    /**
     * 内容
     */
    private String content;
    /**
     * 卖家id
     */
    private String sellerId;
    /**
     * 店铺id
     */
    private String storeId;
    /**
     * 卖家头像
     */
    private String icon;
    /**
     * 背景图片
     */
    private String bgImg;
    /**
     * 产品图
     */
    private List<String> productImg;
    /**
     * 详细图
     */
    private List<String> detailImg;
    /**
     * 略缩图
     */
    private List<String> slightlyThumbnail;

    /**
     * 主页背景视频
     */
    private List<String> bgVideo;

    /**
     * 产品视频
     */
    private List<String> productVideo;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 温馨提示
     */
    private String tips;

    /**
     * 月售
     */
    private int monthlySales;

    /**
     * 店名
     */
    private String storeName;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 价格提示
     */
    private String priceTip;
}
