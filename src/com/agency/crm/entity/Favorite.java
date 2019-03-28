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
public class Favorite extends BaseBean<Favorite> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4461252801883767653L;

	private Integer id;
	
	private String openId;
	
	private String unionId;
	
	private String favoriteId;
	
	private String type;
	
	private String status;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer isDel;

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
