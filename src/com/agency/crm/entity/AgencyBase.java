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
	private String contactName;
	private String sysUserName;
	private String sysUserPassword;
	private String mobilephone;
	private String email;
	private String openId;
	private Integer isCooperation;
	private String city;
	private String province;
	private String location;
	private Date createTime;
	private Date updateTime;
	private Integer isDel;
	private String latitude;
	private String longitude;
	private String address;
	private String wechatImagePath;
	private String logoImagePath;
	private String licence1ImagePath;
	private String licence2ImagePath;
	private Date expiryDate;
	private String validPeriod;
	private String isAd;
	private String area;
	private String distance;
	private String status;
	private String type;
	private String searchStartTime;
	private String searchEndTime;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getSearchStartTime() {
		return searchStartTime;
	}

	public void setSearchStartTime(String searchStartTime) {
		this.searchStartTime = searchStartTime;
	}

	public String getSearchEndTime() {
		return searchEndTime;
	}

	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
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

	public String getDistance() {
		return distance;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getIsAd() {
		return isAd;
	}

	public void setIsAd(String isAd) {
		this.isAd = isAd;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
	}
	public String getLicence1ImagePath() {
		return licence1ImagePath;
	}
	public void setLicence1ImagePath(String licence1ImagePath) {
		this.licence1ImagePath = licence1ImagePath;
	}
	public String getLicence2ImagePath() {
		return licence2ImagePath;
	}
	public void setLicence2ImagePath(String licence2ImagePath) {
		this.licence2ImagePath = licence2ImagePath;
	}
	public String getWechatImagePath() {
		return wechatImagePath;
	}
	public void setWechatImagePath(String wechatImagePath) {
		this.wechatImagePath = wechatImagePath;
	}
	public String getLogoImagePath() {
		return logoImagePath;
	}
	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
	}
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
