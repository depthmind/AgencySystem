package com.agency.crm.entity;

import java.math.BigDecimal;

public class Goods {

    private long id;
    private long productId;
    private long agencyId;
    private String openId;
    private String province;
    private String city;
    private String area;
    private String productName;
    private String agencyName;
    private String agencyLogo;
    private String goodsName;
    private String goodsDescription;
    private BigDecimal price;
    private Integer stock;
    private String goodsPic;
    private String thumbnail;
    private Integer isTop;
    private String oneLevelCategory;
    private String secondLevelCategory;
    private String brandCategory;
    private String seriesCategory;
    private Integer online;
    private Integer isDel;
    private Integer favoriteId;

    public Integer getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getBrandCategory() {
		return brandCategory;
	}

	public void setBrandCategory(String brandCategory) {
		this.brandCategory = brandCategory;
	}

	public String getSeriesCategory() {
		return seriesCategory;
	}

	public void setSeriesCategory(String seriesCategory) {
		this.seriesCategory = seriesCategory;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOneLevelCategory() {
		return oneLevelCategory;
	}

	public void setOneLevelCategory(String oneLevelCategory) {
		this.oneLevelCategory = oneLevelCategory;
	}

	public String getSecondLevelCategory() {
		return secondLevelCategory;
	}

	public void setSecondLevelCategory(String secondLevelCategory) {
		this.secondLevelCategory = secondLevelCategory;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

    public String getAgencyLogo() {
        return agencyLogo;
    }

    public void setAgencyLogo(String agencyLogo) {
        this.agencyLogo = agencyLogo;
    }

    public Goods(long id, long productId, long agencyId, String productName, String agencyName, String goodsName, String goodsDescription) {
        this.id = id;
        this.productId = productId;
        this.agencyId = agencyId;
        this.productName = productName;
        this.agencyName = agencyName;
        this.goodsName = goodsName;
        this.goodsDescription = goodsDescription;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
