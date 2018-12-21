package com.aojing.redstore.goods.vo;

import lombok.Data;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/21 11:10
 */
@Data
public class CategoryVo {

    private String sellerId;
    private List<GoodsTypeVo> goodsTypeVoList;

}
