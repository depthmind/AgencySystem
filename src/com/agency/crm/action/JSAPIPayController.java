package com.agency.crm.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.Constants;
import com.agency.crm.entity.PayEntity;
import com.agency.crm.utils.HttpsGetUtil;
import com.agency.crm.utils.WXPay;
import com.agency.crm.utils.WXPayConfigImpl;
import com.agency.crm.utils.WXPayUtil;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/pay")
public class JSAPIPayController {
//	@RequestMapping(value = "/jsapiPay")
//	@ResponseBody
//	public String jsapiPay(HttpServletRequest request, HttpServletResponse response, Model model) {
//		String tradeNo = request.getParameter("tradeNo");
//		String timestamp = String.valueOf((new Date()).getTime() / 1000L);;
//		float totalFee = Float.valueOf(request.getParameter("totalFee"));
//		System.out.println("totalFee="+totalFee);
//		String displayFee = String.valueOf(totalFee);
//		String fee = String.valueOf(totalFee * 100);
//		int index = fee.indexOf(".");
//		String sendFee = String.valueOf(totalFee * 100).substring(0, index);
//		String ip = getIpAddress(request);
//		if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
//			ip = ip.substring(0, ip.indexOf(","));
//		}
//		reqData.put("appid", Constants.APP_ID);
//		reqData.put("attach", "tourmade");
//		reqData.put("body", "公众号H5支付测试");
//		reqData.put("detail", "支付测试");
//		reqData.put("total_fee", sendFee);
//		reqData.put("openid", "o-MzH5QEtFKbIWltxWk1xK4gceBE");
//		reqData.put("out_trade_no", tradeNo);
//		reqData.put("spbill_create_ip", ip);
//		reqData.put("total_fee", sendFee);
//		reqData.put("trade_type", Constants.TRADE_TYPE_JSAPI);
//		String prepayId = "";
//		String sign = "";
//		try {
//			WXPay wxPay = new WXPay(config, Constants.NOTIFY_URL);
//			respData = wxPay.unifiedOrder(reqData);
//			prepayId = "prepay_id=" + respData.get("prepay_id");
//			sign = respData.get("sign");
//			System.out.println("prepayId=" + prepayId);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		PayEntity payEntity = new PayEntity();
//		payEntity.setTimeStamp(timestamp);
//		payEntity.setNonceStr(WXPayUtil.generateUUID());
//		payEntity.setPrepayId(prepayId);
//		payEntity.setPaySign(sign);
//		String result = JSONObject.toJSONString(payEntity);
//		return result;
//	}
	
	@RequestMapping(value = "/jsapiPay")
	@ResponseBody
	public String jsapiPay(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, String> reqData = new HashMap<String, String>();
		Map<String, String> respData = new HashMap<String, String>();
		WXPayConfigImpl config = new WXPayConfigImpl();
		String prepayId = "";
		String sign = "";
		String nonce_str = "AJHJFHJKU";
		
		String tradeNo = request.getParameter("tradeNo");
		String openId = request.getParameter("openId");
		System.out.println("tradeNo=" + tradeNo);
		tradeNo = (tradeNo + "-" + WXPayUtil.generateUUID()).substring(0, 32);
		
		//String openId = request.getParameter("openId");
		System.out.println("openId=" + openId);
		
		String destinationTmp = request.getParameter("destination");
		System.out.println("destinationTmp=" + destinationTmp);
		String destination = destinationTmp != null ? destinationTmp : "";
		
		String timestamp = String.valueOf((new Date()).getTime() / 1000L);
		
		float totalFee = Float.valueOf(request.getParameter("totalFee"));
		System.out.println("totalFee=" + totalFee);
		String displayFee = String.valueOf(totalFee);
		int index = String.valueOf(totalFee * 100).indexOf(".");
		String sendFee = String.valueOf(totalFee * 100).substring(0, index);
		String ip = getIpAddress(request);
		if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
			ip = ip.substring(0, ip.indexOf(","));
		}
		
		try {
			reqData.put("appid", Constants.APP_ID);
			reqData.put("mch_id", Constants.MCH_ID);
			reqData.put("nonce_str", nonce_str);
			//reqData.put("sign", nonce_str);
			reqData.put("attach", "tourmade");
			reqData.put("body", "body");
			reqData.put("openid", openId);
			reqData.put("out_trade_no", tradeNo);
			reqData.put("spbill_create_ip", ip);
			reqData.put("total_fee", sendFee);
			reqData.put("trade_type", Constants.TRADE_TYPE_JSAPI);
			reqData.put("notify_url", Constants.NOTIFY_URL);
			String beforeSign = WXPayUtil.generateSignedXml(reqData, Constants.KEY);
			Map<String, String> m = WXPayUtil.xmlToMap(beforeSign);
			WXPay wxPay = new WXPay(config, Constants.NOTIFY_URL);
			respData = wxPay.unifiedOrder(m);
			prepayId = "prepay_id=" + respData.get("prepay_id");
			String tmp = "appId=" + Constants.APP_ID + "&nonceStr=" + "KJDJFK" + "&package=" + prepayId
					+ "&signType=" + Constants.SIGN_TYPE_MD5 + "&timeStamp=" + timestamp + "&key=" + Constants.KEY;
			/*Map<String, String> m = new HashMap<String, String>();
			m.put("appId", Constants.APP_ID);
			m.put("nonceStr", nonce_str);
			m.put("package", prepayId);
			m.put("signType", Constants.SIGN_TYPE_MD5);
			m.put("timeStamp", timestamp);
			String a = WXPayUtil.generateSignedXml(m, Constants.KEY);
			System.out.println(a);*/
			sign = WXPayUtil.MD5("appId=" + Constants.APP_ID + "&nonceStr=" + nonce_str + "&package=" + prepayId
					+ "&signType=" + Constants.SIGN_TYPE_MD5 + "&timeStamp=" + timestamp + "&key=" + Constants.KEY).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PayEntity payEntity = new PayEntity();
		payEntity.setTimeStamp(timestamp);
		payEntity.setNonceStr(nonce_str);
		payEntity.setPrepayId(prepayId);
		payEntity.setPaySign(sign);
		String result = JSONObject.toJSONString(payEntity);
		return result;
	}
	
	public static String getIpAddress(HttpServletRequest request) {  
		 String ip = request.getHeader("x-forwarded-for");  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			 System.out.println("Proxy-Client-IP");
			 ip = request.getHeader("Proxy-Client-IP");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			 System.out.println("WL-Proxy-Client-IP");
		 	ip = request.getHeader("WL-Proxy-Client-IP");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			 System.out.println("HTTP_CLIENT_IP");
		 	ip = request.getHeader("HTTP_CLIENT_IP");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			 System.out.println("HTTP_X_FORWARDED_FOR");
		 	ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			 System.out.println("getRemoteAddr");
		 	ip = request.getRemoteAddr();  
		 }  
		 return ip;  
	 }
	
	@RequestMapping(value = "/openIdForWeiXinApp.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String openIdForWeiXinApp(String js_code) {
		String openId = "";
		String appid = "";
		String secret = "";
		String get_access_token_url = "https://api.weixin.qq.com/sns/jscode2session?"
                + "appid="
                + "wx0ca338b9cfb6df43"
                + "&secret="
                + "9f5ca652ce47f3949c93e22aa1ecfb0e"
                + "&js_code=CODE&grant_type=authorization_code";

        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		System.out.println("js_code---" + js_code);
        String code = js_code;//req.getParameter("code");

        get_access_token_url = get_access_token_url.replace("CODE", code);

        String json = HttpsGetUtil.doHttpsGetJson(get_access_token_url);
        System.out.println("json---" + json);

        JSONObject jsonObject = JSONObject.parseObject(json);
        String openid = jsonObject.getString("openid");
		return openid;
    }
}
