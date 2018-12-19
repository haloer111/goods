package com.aojing.redstore.goods.common;


import lombok.Data;

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
     * 内容
     */
    private String content;
    /**
     * 卖家id
     */
    private String userId;
    /**
     * 卖家名称
     */
    private String userName;
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

    private double price;


}
