package com.agency.crm.utils;

import net.sf.json.JSONObject;

public class AccessTokenUtils {

	public static String getWeiXinAppAccessToken() {
		String path = null;
		
		path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + "wx3541709ab795812c"
				+ "&secret=" + "1bdfe690199c11581d46896f2f7113fe";
		String responseMsg = HttpsGetUtil.doHttpsGetJson(path);
		JSONObject responseMsgObj = JSONObject.fromObject(responseMsg);
		String accessToken = responseMsgObj.getString("access_token");
		return accessToken;
	}
}
