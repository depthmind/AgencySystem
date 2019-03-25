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
public class AdvertisementRotation extends BaseBean<AdvertisementRotation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7449626042380924795L;

	private Integer id;
	
	private String advertisementType;
	
	private String promoteId;
	
	private String promoteType;
	
	private String isSold;
	
	private Date releaseDate;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer isDel;

	public String getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(String promoteId) {
		this.promoteId = promoteId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdvertisementType() {
		return advertisementType;
	}

	public void setAdvertisementType(String advertisementType) {
		this.advertisementType = advertisementType;
	}

	public String getPromoteType() {
		return promoteType;
	}

	public void setPromoteType(String promoteType) {
		this.promoteType = promoteType;
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
