package com.aojing.redstore.goods.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsSetMenu {
    private Integer id;

    private String goodSetId;

    private String name;

    private String typeId;

    private String instruction;

    private Integer enableCount;

    private String unit;

    private Integer sellStatus;

    private String goodsSetDesc;

    private BigDecimal salesPrice;

    private BigDecimal originalPrice;

    private String goodsCode;

    private BigDecimal costPrice;

    private Date createTime;

    private Date updateTime;

    private Date startTime;

    private Date endTime;

    private String precondition;

    private Integer sellCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodSetId() {
        return goodSetId;
    }

    public void setGoodSetId(String goodSetId) {
        this.goodSetId = goodSetId == null ? null : goodSetId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction == null ? null : instruction.trim();
    }

    public Integer getEnableCount() {
        return enableCount;
    }

    public void setEnableCount(Integer enableCount) {
        this.enableCount = enableCount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public String getGoodsSetDesc() {
        return goodsSetDesc;
    }

    public void setGoodsSetDesc(String goodsSetDesc) {
        this.goodsSetDesc = goodsSetDesc == null ? null : goodsSetDesc.trim();
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition == null ? null : precondition.trim();
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }
}