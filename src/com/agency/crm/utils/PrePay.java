package com.agency.crm.utils;

import com.agency.crm.common.framework.util.HttpRequestUtil;

public class PrePay {
	public void generateOrder() {
		final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String responseMsg = null;
		String param = "<xml>"+
		   "<appid>wxf8a42683e79ade7e</appid>"+
		   "<attach>支付测试</attach>"+
		   "<body>JSAPI支付测试</body>"+
		   "<detail>支付测试</detail>"+
		   "<mch_id>1320308701</mch_id>"+
		   "<nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>"+
		   "<notify_url>http://www.tourmade.com</notify_url>"+
		   "<out_trade_no>1415659990</out_trade_no>"+
		   "<spbill_create_ip>113.47.252.94</spbill_create_ip>"+
		   "<total_fee>1</total_fee>"+
		   "<trade_type>JSAPI</trade_type>"+
		   "<sign>61CCC3D6D58C8B0CAD25A2C3033AF93B</sign>"+
   			"</xml>";
		responseMsg = HttpRequestUtil.sendPost(url, param);
		System.out.println("responseMsg="+responseMsg);
	}
}
