package com.agency.crm.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.agency.crm.common.framework.BaseBean;

public class WithdrawalRecord extends BaseBean<WithdrawalRecord> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5633449551148148764L;

	private Integer id;
	
	private String openId;
	
	private BigDecimal amount;
	
	private BigDecimal applyAmount;
	
	private BigDecimal successAmount;
	
	private BigDecimal commission;
	
	private String name;
	
	private String wechat;
	
	private String status;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String searchStartTime;
	
	private String searchEndTime;
	
	private Integer isDel;
	
	private Map<String, Object> cmounts;

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

	public Map<String, Object> getCmounts() {
		return cmounts;
	}

	public void setCmounts(Map<String, Object> cmounts) {
		this.cmounts = cmounts;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public BigDecimal getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(BigDecimal successAmount) {
		this.successAmount = successAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
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
