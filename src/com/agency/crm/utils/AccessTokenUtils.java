package com.agency.crm.utils;

import com.agency.crm.common.Constants;

import net.sf.json.JSONObject;

public class AccessTokenUtils {

	public static String getWeiXinAppAccessToken() {
		String path = null;
		
		path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constants.APP_ID
				+ "&secret=" + Constants.APP_SECRET;
		String responseMsg = HttpsGetUtil.doHttpsGetJson(path);
		JSONObject responseMsgObj = JSONObject.fromObject(responseMsg);
		String accessToken = responseMsgObj.getString("access_token");
		return accessToken;
	}
}
