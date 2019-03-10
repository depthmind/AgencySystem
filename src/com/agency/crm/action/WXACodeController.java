package com.agency.crm.action;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.framework.util.HttpRequestUtil;
import com.agency.crm.common.framework.util.HttpUtil;
import com.agency.crm.utils.AccessTokenUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/wxaCode")
public class WXACodeController extends BaseSimpleFormController {

	@RequestMapping(value="/createWXACode", produces="application/json;charset=utf-8")
	public void createWXACode(HttpServletRequest request, String path) {
		URL url = null;
		HttpURLConnection connection = null;
		
		String remote = "https://api.weixin.qq.com/wxa/getwxacode?access_token=" + AccessTokenUtils.getWeiXinAppAccessToken()
			+ "&path=" + path;
		try {
			url = new URL(remote);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			JSONObject paramJson = new JSONObject();
            paramJson.put("scene", "a=1234567890");
            paramJson.put("page", "pages/index/index");
            paramJson.put("width", 430);
            paramJson.put("auto_color", true);
			String msg = HttpUtil.sendRequest(connection, JSONObject.toJSONString(paramJson));
			System.out.println("生成小程序码---" + msg);
			//miniProgramFormIdService.deleteMiniProgramFormId(formId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}
}
