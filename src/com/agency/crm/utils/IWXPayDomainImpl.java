package com.agency.crm.utils;

public class IWXPayDomainImpl implements IWXPayDomain {

	@Override
	public void report(String domain, long elapsedTimeMillis, Exception ex) {
		// TODO Auto-generated method stub

	}

	@Override
	public DomainInfo getDomain(WXPayConfigImpl config) {
		final String domain = "api.mch.weixin.qq.com";
		DomainInfo domainInfo = new DomainInfo(domain, true);
		return domainInfo;
	}
}
