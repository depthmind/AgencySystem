package com.agency.crm.entity;

public class Goods {

    private long id;
    private long productId;
    private long agencyId;
    private String productName;
    private String agencyName;
    private String goodsName;
    private String goodsDescription;

    public Goods(long id, long productId, long agencyId, String productName, String agencyName, String goodsName, String goodsDescription) {
        this.id = id;
        this.productId = productId;
        this.agencyId = agencyId;
        this.productName = productName;
        this.agencyName = agencyName;
        this.goodsName = goodsName;
        this.goodsDescription = goodsDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Goods() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(long agencyId) {
        this.agencyId = agencyId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }
}
