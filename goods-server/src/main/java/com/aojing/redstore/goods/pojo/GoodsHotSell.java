package com.aojing.redstore.goods.pojo;

import java.util.Date;

public class GoodsHotSell {
    private Integer id;

    private String hotSellId;

    private String goodsId;

    private String menuId;

    private Integer hotSetDesc;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotSellId() {
        return hotSellId;
    }

    public void setHotSellId(String hotSellId) {
        this.hotSellId = hotSellId == null ? null : hotSellId.trim();
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