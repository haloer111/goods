package com.aojing.redstore.goods.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @author gexiao
 * @date 2018/12/19 11:21
 */
@Data
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class DecreaseStockInput implements Serializable {
    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 商品数量
     */
    private int goodsCount;

    public DecreaseStockInput(String goodsId, int goodsCount) {
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
    }

    public DecreaseStockInput() {
    }
}
