package com.agency.crm.entity;

import com.agency.crm.common.framework.BaseBean;

public class UnionIdEntity extends BaseBean<UnionIdEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8345089817521713665L;

	private String openid;
	
	private String unionid;
	
	private String session_key;
	
	private String errcode;
	
	private String errmsg;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
