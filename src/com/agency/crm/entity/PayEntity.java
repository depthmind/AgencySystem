package com.agency.crm.entity;

import com.agency.crm.common.framework.BaseBean;

public class PayEntity extends BaseBean<PayEntity> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2689018739738194443L;

	private String timeStamp;
	
	private String nonceStr;
	
	private String prepayId;
	
	private String paySign;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
}
