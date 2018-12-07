package com.aojing.redstore.goods.util;

import com.aojing.redstore.goods.GoodsApplicationTests;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author gexiao
 * @date 2018/12/6 14:23
 */
public class KeyUtilTest extends GoodsApplicationTests {

    @Test
    public void createKey() {
        String key = KeyUtil.get32UUID();
        System.out.println("=========="+key);
    }
    //1683831544077678362
    //8561691544077698228
}