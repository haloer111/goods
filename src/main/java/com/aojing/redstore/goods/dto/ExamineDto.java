package com.aojing.redstore.goods.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author gexiao
 * @date 2018/12/10 10:20
 */
@Data
public class ExamineDto {

    private Integer id;

    private String goodsId;

    private String menuId;

    private Integer examineResult;

    private String examiner;
}
