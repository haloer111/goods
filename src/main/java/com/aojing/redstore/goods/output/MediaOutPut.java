package com.aojing.redstore.goods.output;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Data
public class MediaOutPut {
    private Integer id;

    private String entityId;

    private String goodsId;

    private String menuId;

    private String hotSellId;

    private String absolutePath;

    private String relativePath;

    private Integer type;

    private String name;

    private String nocount;

    private String lllustrate;

    private String leadingOfficical;

    private String location;

    private String remark;

    private MultipartFile imgFile;
    private MultipartFile videoFile;

}