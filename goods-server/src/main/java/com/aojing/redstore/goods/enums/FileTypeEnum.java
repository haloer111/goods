package com.aojing.redstore.goods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author gexiao
 * @date 2018/12/7 16:53
 */
@Getter
@AllArgsConstructor
@ToString
public enum FileTypeEnum {
    /**
     * 背景图片
     */
    BG_IMG(10,"背景图片"),
    /**
     * 头像图标
     */
    ICON(20,"头像图标"),

    /**
     * 产品图
     */
    PRODUCT_IMG(30,"产品图"),
    /**
     * 详细图
     */
    DETAIL_IMG(40,"详细图"),
    /**
     * 略缩图
     */
    SLIGHTLY_THUMBNAIL(50,"略缩图"),


    /**
     * 主页背景视频
     */
    BG_VIDEO(60,"主页背景视频"),

    /**
     * 产品视频
     */
    PRODUCT_VIDEO(70,"产品视频"),


    ;

    private int code;
    private String value;


    public static FileTypeEnum codeOf(int code){
        for(FileTypeEnum orderStatusEnum : values()){
            if(orderStatusEnum.getCode() == code){
                return orderStatusEnum;
            }
        }
        throw new RuntimeException("没有找到对应的枚举");
    }

    public static void main(String[] args) {
        FileTypeEnum fileTypeEnum = FileTypeEnum.codeOf(11);
        System.out.println(fileTypeEnum);
    }
}
