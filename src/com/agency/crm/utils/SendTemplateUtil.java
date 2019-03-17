/**
 * 
 */
package com.agency.crm.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.agency.crm.common.Constants;
import com.agency.crm.common.framework.util.HttpRequestUtil;
import com.agency.crm.common.framework.util.HttpUtil;
import com.agency.crm.entity.MiniProgramFormId;

import net.sf.json.JSONObject;

/**
 * @author zyy
 *
 */
public class SendTemplateUtil {
	public static void sendMsg(String accessToken, String formId, String openId, String result, String reason) {
		String path = null;
		URL url = null;
		HttpURLConnection connection = null;
		
		String para = "{\"touser\":\"" + openId + "\",\"template_id\": \"" + Constants.WEIXIN_APP_TEMPLATE_ID_EXAMINE
				+ "\",\"page\": \"pages/index/index\",\"form_id\": \"" + formId
				+ "\",\"data\": {\"keyword1\": {\"value\": \"" + result
				+ "\",\"color\": \"#173177\"},\"keyword2\": {\"value\": \"" + reason
				+ "\",\"color\": \"#173177\"}},\"emphasis_keyword\": \" \"}";
		path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken;
		System.out.println("para===" + para);
		try {
			url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			
			String msg = HttpUtil.sendRequest(connection, para);
			System.out.println("发送模板消息返回---" + msg);
			//miniProgramFormIdService.deleteMiniProgramFormId(formId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}
}
