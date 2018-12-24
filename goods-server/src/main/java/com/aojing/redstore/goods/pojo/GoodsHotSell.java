package com.aojing.redstore.goods.pojo;


import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("goods_hot_sell_tb")
public class GoodsHotSell {
    private String id;

    private String goodsId;

    private String menuId;

    private Integer hotSetDesc;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public Integer getHotSetDesc() {
        return hotSetDesc;
    }

    public void setHotSetDesc(Integer hotSetDesc) {
        this.hotSetDesc = hotSetDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}