package com.agency.crm.action;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.Constants;
import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.framework.util.HttpUtil;
import com.agency.crm.entity.Customer;
import com.agency.crm.service.CustomerService;
import com.agency.crm.utils.WXUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/wechat")
public class InvokeWechatController extends BaseSimpleFormController {

	@Autowired 
	private CustomerService customerService;
	
	@RequestMapping(value="/getUnionId.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public String getUnionId(String jsCode, String iv, String encryptedData) {
		String result = "";
		Customer customer = new Customer();
		String path = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constants.APP_ID
				+ "&secret=" + Constants.APP_SECRET
				+ "&js_code=" + jsCode
				+ "&grant_type=authorization_code";
		try {
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			String responseMsg = HttpUtil.sendRequest(connection, "");
			JSONObject object = JSONObject.parseObject(responseMsg);
			String sessionKey = object.getString("session_key");
			JSONObject userInfo = WXUtils.getUserInfo(encryptedData.replaceAll("\\s", "+"), sessionKey.replaceAll("\\s", "+"), iv.replaceAll("\\s", "+"));
			result = JSONObject.toJSONString(userInfo);
			String openId = userInfo.getString("openId");
			customer = customerService.findCustomerByOpenId(openId);
			if (customer != null && customer.getId() > 0) {
				return result;
			}
			customer = new Customer();
			String country = userInfo.getString("country");
			String unionId = userInfo.getString("unionId");
			String gender = userInfo.getString("gender");
			String province = userInfo.getString("province");
			String city = userInfo.getString("city");
			String nickName = userInfo.getString("nickName");
			String language = userInfo.getString("language");
			customer.setCountry(country);
			customer.setUnionId(unionId);
			customer.setGender(gender);
			customer.setProvince(province);
			customer.setCity(city);
			customer.setOpenId(openId);
			customer.setNickName(nickName);
			customer.setLanguage(language);
			try {
				customerService.saveCustomer(customer);
			} catch (Exception e) {
				logger.error("保存用户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
