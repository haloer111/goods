package com.aojing.redstore.goods.dto;

import lombok.Data;

/**
 * @author gexiao
 * @date 2018/12/10 10:07
 */
@Data
public class CommentDto {

    private String commenterId;

    private String goodsId;

    private String menuId;

    private String content;
    /**
     *
     */
    private String sellerId;

}
