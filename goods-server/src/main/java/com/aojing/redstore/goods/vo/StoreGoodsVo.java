package com.aojing.redstore.goods.vo;

import com.aojing.redstore.goods.common.GoodsInfoVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * 店铺商品vo
 * @author gexiao
 * @date 2018/12/21 14:01
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreGoodsVo {

    /**
     * 商铺id
     */
    private String sellerId;
    /**
     * 商铺头像
     */
    private String icon;
    /**
     * 商铺名字
     */
    private String sellerName;
    /**
     * 点赞数
     */
    private int giveLikeCount;
    /**
     * 评论数
     */
    private int commentCount;
    /**
     * 略缩图
     */
    private List<String> slightlyThumbnail;

    /**
     * 商品数量
     */
    private int goodsInfoCount;

    /**
     * 定位位置
     */
    private String position;

    /**
     * 店家简介
     */
    private String content;

    /**
     * 背景视频
     */
    private List<String> bgVideo;

    /**
     * 商品id集合
     */
    private List<String> goodsIdList;
}
