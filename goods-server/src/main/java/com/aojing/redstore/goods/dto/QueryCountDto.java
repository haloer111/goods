package com.aojing.redstore.goods.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/21 14:20
 */
@Getter
@Setter
public class QueryCountDto {
   private  List<String> goodsIdList;
   private  List<String> sellerIdList;
}
