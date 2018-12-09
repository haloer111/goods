package com.aojing.redstore.goods.output;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

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


    private List<MultipartFile> imgFileList;
    private List<MultipartFile> videoFileList;

}