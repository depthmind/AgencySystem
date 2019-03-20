package com.agency.crm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.Constants;
import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.framework.util.JSONUtilS;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.EntityList;
import com.agency.crm.entity.Parameter;
import com.agency.crm.entity.PublishContent;
import com.agency.crm.service.AgencyBaseService;
import com.agency.crm.service.MiniProgramFormIdService;
import com.agency.crm.service.ParameterService;
import com.agency.crm.service.PublishContentService;
import com.agency.crm.utils.HttpsGetUtil;
import com.agency.crm.utils.SendTemplateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseSimpleFormController {

	@Autowired
	private PublishContentService publishContentService;
	@Autowired
	private AgencyBaseService agencyBaseService;
	@Autowired
	private MiniProgramFormIdService miniProgramFormIdService;
	@Autowired
	private ParameterService parameterService;

	@RequestMapping(value = "/publishContentList.html", produces = "application/json;charset=utf-8")
	public String publishContentListPage(Model model) {
		List<EntityList> categoryList = parameterService.getParameterInfo("publish.category");
		model.addAttribute("publishCategory", JSONArray.fromObject(categoryList));
		return "/manager/publishList";
	}

	@RequestMapping(value = "/publishContentList.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String publishContentList(HttpServletRequest request, HttpSession session, Model model, PublishContent publishContent,
			PageHelper page) {
		QueryResult<PublishContent> pageResult = publishContentService.queryPublishContent(publishContent, page, request);
		String result = JSONUtilS.object2json(pageResult);
		return result;
	}

	@RequestMapping(value = "/agencyList.html", produces = "application/json;charset=utf-8")
	public String agencyListPage() {
		return "/manager/agencyList";
	}

	@RequestMapping(value = "/agencyList.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String agencyList(HttpServletRequest request, HttpSession session, Model model, AgencyBase agencyBase,
			PageHelper page) {
		QueryResult<AgencyBase> pageResult = agencyBaseService.queryAgencyBase(agencyBase, page, request);
		String result = JSONUtilS.object2json(pageResult);
		return result;
	}
	
	@RequestMapping(value = "/updatePublishContentStatusById.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updatePublishContentStatusById(@RequestParam(required = true) Integer id, String status) {
		Json json = new Json();
		PublishContent publishContent = new PublishContent();
		json.setSuccess(false);
		int result = 0;
		String message = "";
		String reason = "";
		
		publishContent.setId(id);
		if ("2".equals(status)) {
			message = "审核通过";
			reason = "审核通过";
			publishContent.setStatus(Constants.STATUS_OF_PUBLISH_CONTENT_TWO);
		}
		if ("3".equals(status)) {
			message = "审核不通过";
			reason = "请检查提交内容";
			publishContent.setStatus(Constants.STATUS_OF_PUBLISH_CONTENT_THREE);
		}
		result = publishContentService.updatePublishContent(publishContent);
		if (result > 0) {
			publishContent = publishContentService.findPublishContentById(id);
			String openId = publishContent.getOpenId();
			sendTemplageMessage(openId, message, reason);
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/updateAgencyBaseStatusById.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateAgencyBaseStatusById(@RequestParam(required = true) Integer id, String status) {
		Json json = new Json();
		AgencyBase agencyBase = new AgencyBase();
		json.setSuccess(false);
		int result = 0;
		String message = "";
		String reason = "";
		
		agencyBase.setId(id);
		if ("2".equals(status)) {
			agencyBase.setStatus(Constants.STATUS_OF_AGENCY_BASE_TWO);
			message = "审核通过";
			reason = "审核通过";
		}
		if ("3".equals(status)) {
			agencyBase.setStatus(Constants.STATUS_OF_AGENCY_BASE_THREE);
			message = "审核不通过";
			reason = "请检查提交内容";
		}
		result = agencyBaseService.updateAgencyBase(agencyBase);
		if (result > 0) {
			agencyBase = agencyBaseService.findAgencyBaseById(id);
			String openId = agencyBase.getOpenId();
			sendTemplageMessage(openId, message, reason);
			json.setSuccess(true);
		}
		
		return json;
	}
	
	private void sendTemplageMessage(String openId, String message, String reason) {
		try {
			String formId = miniProgramFormIdService.getFormIdByCreateTime(openId).getFormId();
			SendTemplateUtil.sendMsg(getWeiXinAppAccessToken(), formId, openId, message, reason);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getWeiXinAppAccessToken() {
		String path = null;
		
		path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constants.APP_ID
				+ "&secret=" + Constants.APP_SECRET;
		String responseMsg = HttpsGetUtil.doHttpsGetJson(path);
		JSONObject responseMsgObj = JSONObject.fromObject(responseMsg);
		String accessToken = responseMsgObj.getString("access_token");
		return accessToken;
	}
}
