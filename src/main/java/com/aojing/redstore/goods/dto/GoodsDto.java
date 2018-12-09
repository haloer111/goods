package com.aojing.redstore.goods.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/7 14:31
 */
@Data
public class GoodsDto {

    private String id;
    private String typeId;

    private String goodsCode;

    private BigDecimal price;

    private BigDecimal costPrice;

    private Integer salesPromotion;

    private String seller;

    private String site;

    private String brand;

    private String name;

    private String sellNumber;

    private Integer sellStatus;

    private String goodsDesc;

    private String tips;

    private String unit;

    private String instruction;

    private Date startTime;

    private Date endTime;

    private String value;

    private String precondition;

    private String detail;

    private List<File> imgFileList;
    private List<File> videoFileList;
}
