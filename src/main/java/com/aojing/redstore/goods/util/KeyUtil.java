package com.aojing.redstore.goods.util;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author gexiao
 * @date 2018/12/6 14:20
 */
public class KeyUtil {
    private static final int ABS_NUMBER = 100000;
    private static String result;

    // 生成策略6位随机数加当前毫秒数
    public static String createKey() {
        result = RandomUtils.nextInt(1, 899999) + ABS_NUMBER + String.valueOf(System.currentTimeMillis());
        return result;
    }
}
