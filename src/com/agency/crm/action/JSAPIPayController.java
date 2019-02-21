package com.agency.crm.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.Constants;
import com.agency.crm.entity.PayEntity;
import com.agency.crm.utils.WXPay;
import com.agency.crm.utils.WXPayConfigImpl;
import com.agency.crm.utils.WXPayUtil;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/pay")
public class JSAPIPayController {
	@RequestMapping(value = "/jsapiPay")
	@ResponseBody
	public String jsapiPay(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, String> reqData = new HashMap<String, String>();
		Map<String, String> respData = new HashMap<String, String>();
		WXPayConfigImpl config = new WXPayConfigImpl();
		String tradeNo = request.getParameter("tradeNo");
		String timestamp = String.valueOf((new Date()).getTime() / 1000L);;
		float totalFee = Float.valueOf(request.getParameter("totalFee"));
		System.out.println("totalFee="+totalFee);
		String displayFee = String.valueOf(totalFee);
		String fee = String.valueOf(totalFee * 100);
		int index = fee.indexOf(".");
		String sendFee = String.valueOf(totalFee * 100).substring(0, index);
		reqData.put("attach", "支付测试");
		reqData.put("body", "公众号H5支付测试");
		reqData.put("detail", "支付测试");
		//reqData.put("notify_url", "http://www.tourmade.com");
		reqData.put("out_trade_no", tradeNo);
		reqData.put("spbill_create_ip", "113.47.252.94");
		reqData.put("total_fee", sendFee);
//		reqData.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://pay.qq.com\",\"wap_name\": \"腾讯充值\"}}");
		reqData.put("trade_type", Constants.TRADE_TYPE_SAPI);
		String prepayId = "";
		String sign = "";
		try {
			WXPay wxPay = new WXPay(config, Constants.NOTIFY_URL);
			respData = wxPay.unifiedOrder(reqData);
			prepayId = "prepay_id=" + respData.get("prepay_id");
			sign = respData.get("sign");
			System.out.println("prepayId=" + prepayId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PayEntity payEntity = new PayEntity();
		payEntity.setTimeStamp(timestamp);
		payEntity.setNonceStr(WXPayUtil.generateUUID());
		payEntity.setPrepayId(prepayId);
		payEntity.setPaySign(sign);
		String result = JSONObject.toJSONString(payEntity);
		return result;
	}
}
