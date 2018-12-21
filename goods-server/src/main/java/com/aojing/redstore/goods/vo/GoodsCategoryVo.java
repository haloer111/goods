package com.aojing.redstore.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author gexiao
 * @date 2018/12/12 15:16
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCategoryVo implements Serializable {

    private String id;
    private String name;

}
