package com.agency.crm.utils;

import java.io.InputStream;

import com.agency.crm.common.Constants;

public class WXPayConfigImpl extends WXPayConfig {

	public final String appId = Constants.APP_ID;
	public final String mchId = Constants.MCH_ID;
	public final String key = Constants.KEY;
	@Override
	String getAppID() {
		return this.appId;
	}

	@Override
	String getMchID() {
		return this.mchId;
	}

	@Override
	String getKey() {
		return this.key;
	}

	@Override
	InputStream getCertStream() {
		return null;
	}

	@Override
	IWXPayDomainImpl getWXPayDomain() {
		IWXPayDomainImpl domainInfo = new IWXPayDomainImpl();
		return domainInfo;
	}
	
	/**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpConnectTimeoutMs() {
        return 6*1000;
    }

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpReadTimeoutMs() {
        return 8*1000;
    }

    /**
     * 是否自动上报。
     * 若要关闭自动上报，子类中实现该函数返回 false 即可。
     *
     * @return
     */
    public boolean shouldAutoReport() {
        return true;
    }

    /**
     * 进行健康上报的线程的数量
     *
     * @return
     */
    public int getReportWorkerNum() {
        return 6;
    }


    /**
     * 健康上报缓存消息的最大数量。会有线程去独立上报
     * 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
     *
     * @return
     */
    public int getReportQueueMaxSize() {
        return 10000;
    }

    /**
     * 批量上报，一次最多上报多个数据
     *
     * @return
     */
    public int getReportBatchSize() {
        return 10;
    }


}
