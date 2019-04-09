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
public class SeriesCategory extends BaseBean<SeriesCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1363667526543970709L;

	private Integer id;
	
	private String brandId;
	
	private String categoryName;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer isDel;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
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
