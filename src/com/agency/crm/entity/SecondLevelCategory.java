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
public class SecondLevelCategory extends BaseBean<SecondLevelCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6423798628206473341L;

	private Integer id;
	
	private String oneLevelCategoryId;
	
	private String category;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer isDel;

	public String getOneLevelCategoryId() {
		return oneLevelCategoryId;
	}

	public void setOneLevelCategoryId(String oneLevelCategoryId) {
		this.oneLevelCategoryId = oneLevelCategoryId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
