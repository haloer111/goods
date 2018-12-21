package com.aojing.redstore.goods.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/20 14:13
 */
@Data
public class CommentVo {

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 评论点赞数
     */
    private int giveLikeCount;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String icon;
    /**
     * 评论
     */
    private String content;

    /**
     * 评论时间
     */
    private Date commentTime;

}

