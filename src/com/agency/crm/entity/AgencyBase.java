package com.agency.crm.entity;

import java.util.Date;

import com.agency.crm.common.framework.BaseBean;

public class AgencyBase extends BaseBean<AgencyBase> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100806375368000043L;
	private Integer id;
	private String agencyName;
	private String sysUserName;
	private String sysUserPassword;
	private String mobilephone;
	private String email;
	private String openId;
	private Integer isCooperation;
	private String city;
	private String location;
	private Date createTime;
	private Date updateTime;
	private Integer isDel;
	private String latitude;
	private String longitude;
	private String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getSysUserName() {
		return sysUserName;
	}
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}
	public String getSysUserPassword() {
		return sysUserPassword;
	}
	public void setSysUserPassword(String sysUserPassword) {
		this.sysUserPassword = sysUserPassword;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getIsCooperation() {
		return isCooperation;
	}
	public void setIsCooperation(Integer isCooperation) {
		this.isCooperation = isCooperation;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
