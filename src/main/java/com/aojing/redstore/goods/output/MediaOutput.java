package com.aojing.redstore.goods.output;

import lombok.Data;

/**
 * @author gexiao
 * @date 2018/12/11 10:36
 */
@Data
public class MediaOutput {
    private Integer id;

    private String entityId;

    private String goodsId;

    private String menuId;

    private String hotSellId;

    private String nocount;
    private Integer type;
    private String absolutePath;
}
