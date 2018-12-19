package com.aojing.redstore.goods.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/12 10:29
 */
@Getter
@Setter
public class FileTypeForm {
    Integer type;
    MultipartFile[] fileList;
}
