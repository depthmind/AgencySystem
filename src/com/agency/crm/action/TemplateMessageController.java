/**
 * 
 */
package com.agency.crm.action;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agency.crm.common.Constants;
import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.framework.util.HttpUtil;
import com.agency.crm.entity.MiniProgramFormId;
import com.agency.crm.service.MiniProgramFormIdService;
import com.agency.crm.utils.HttpsGetUtil;

import net.sf.json.JSONObject;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/template")
public class TemplateMessageController extends BaseSimpleFormController {
	
	@Autowired
	private MiniProgramFormIdService formIdService;

	@RequestMapping(value = "/sendMsg", produces = "application/json;charset=utf-8")
	private void sendMsg(String accessToken, String openId, String result, String reason) {
		String path = null;
		URL url = null;
		String formId = null;
		HttpURLConnection connection = null;
		MiniProgramFormId miniProgramFormId = null;
		
		formId = "8e227b77be123faaaea24542d1d8a64b";
		miniProgramFormId = formIdService.getFormIdByCreateTime(openId);
		if (null != miniProgramFormId) {
			formId = miniProgramFormId.getFormId();
		}
		
		String para = "{\"touser\":\"" + openId + "\",\"template_id\": \"" + Constants.WEIXIN_APP_TEMPLATE_ID_EXAMINE
				+ "\",\"page\": \"pages/index/index\",\"form_id\": \"" + formId
				+ "\",\"data\": {\"keyword1\": {\"value\": \"" + result
				+ "\",\"color\": \"#173177\"},\"keyword2\": {\"value\": \"" + reason
				+ "\",\"color\": \"#173177\"}},\"emphasis_keyword\": \" \"}";
		path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + getWeiXinAppAccessToken();
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
	
	/**
	 * 
	 * @date 2019年3月8日 下午4:56:35
	 * @author LiuHan
	 * @TODO 提交form表单时获取formid并保存，发送模板消息时用
	 */
	@RequestMapping(value = "saveFormIdForTemplate", produces = "application/json;charset=utf-8")
	public void saveFormIdForTemplate(HttpServletRequest request, HttpServletResponse response) {
		String formId = null;
		String openId = null;
		MiniProgramFormId miniProgramFormId = new MiniProgramFormId();
		formId = request.getParameter("formId");
		openId = request.getParameter("openId");
		miniProgramFormId.setFormId(formId);
		miniProgramFormId.setOpenId(openId);
		
		formIdService.saveMiniProgramFormId(miniProgramFormId);
	}
	
	public String getWeiXinAppAccessToken() {
		String path = null;
		
		path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + "wx3541709ab795812c"
				+ "&secret=" + "1bdfe690199c11581d46896f2f7113fe";
		String responseMsg = HttpsGetUtil.doHttpsGetJson(path);
		JSONObject responseMsgObj = JSONObject.fromObject(responseMsg);
		String accessToken = responseMsgObj.getString("access_token");
		return accessToken;
	}
}
