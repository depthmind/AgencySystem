/**
 * 
 */
package com.agency.crm.entity;

import java.util.Date;

import com.agency.crm.common.framework.BaseBean;

/**
 * @author zyy
 *
 */
public class AdvertisementRecommend extends BaseBean<AdvertisementRecommend> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8098026503637621163L;

	private Integer id;
	
	private String goodsId;
	
	private String isSold;
	
	private Date releaseDate;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer isDel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getIsSold() {
		return isSold;
	}

	public void setIsSold(String isSold) {
		this.isSold = isSold;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}
