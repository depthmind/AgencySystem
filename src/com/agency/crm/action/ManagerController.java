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
import com.agency.crm.entity.AdvertisementRotation;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.EntityList;
import com.agency.crm.entity.Parameter;
import com.agency.crm.entity.PublishContent;
import com.agency.crm.service.AdvertisementService;
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
	@Autowired
	private AdvertisementService advertisementService;

	/**
	 * 
	 * @date 2019年3月25日 下午3:59:49
	 * @author LiuHan
	 * @TODO 帖子列表
	 */
	@RequestMapping(value = "/publishContentList.html", produces = "application/json;charset=utf-8")
	public String publishContentListPage(Model model) {
		List<EntityList> categoryList = parameterService.getParameterInfo("publish.category");
		List<EntityList> statusList = parameterService.getParameterInfo("publish.status");
		model.addAttribute("publishCategory", JSONArray.fromObject(categoryList));
		model.addAttribute("publishStatus", JSONArray.fromObject(statusList));
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
	
	/**
	 * 
	 * @date 2019年3月25日 下午4:00:04
	 * @author LiuHan
	 * @TODO 后台新增帖子
	 */
	@RequestMapping(value = "/addPublish.html", produces = "application/json;charset=utf-8")
	public String addPublishPage(Model model) {
		List<EntityList> categoryList = parameterService.getParameterInfo("publish.category");
		model.addAttribute("publishCategory", JSONArray.fromObject(categoryList));
		return "/manager/addPublish";
	}
	
	@RequestMapping(value = "/addPublish.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String addPublish(PublishContent publishContent) {
		int result = publishContentService.savePublishContent(publishContent);
		return "/manager/publishList";
	}

	/**
	 * 
	 * @date 2019年3月25日 下午4:00:15
	 * @author LiuHan
	 * @TODO 商家列表
	 */
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
	
	/**
	 * 
	 * @date 2019年3月25日 下午4:01:56
	 * @author LiuHan
	 * @TODO 广告位列表
	 */
	@RequestMapping(value = "/advertisementListOfRotation.html", produces = "application/json;charset=utf-8")
	public String advertisementListPageOfRotation() {
		return "/manager/advertisementList";
	}
	
	@RequestMapping(value = "/advertisementListOfRotation.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String advertisementListOfRotation(HttpServletRequest request, HttpSession session, Model model, AdvertisementRotation advertisementRotation,
			PageHelper page) {
		QueryResult<AdvertisementRotation> pageResult = advertisementService.queryAdvertisementRotation(advertisementRotation, page, request);
		String result = JSONUtilS.object2json(pageResult);
		return result;
	}
	
	@RequestMapping(value = "/signinTermList.html", produces = "application/json;charset=utf-8")
	public String signinTermListPage() {
		return "/manager/signinTermList";
	}
	
	@RequestMapping(value = "/signinTermList.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String advertisementListOfRotation(HttpServletRequest request, HttpSession session, Model model, Parameter parameter,
			PageHelper page) {
		parameter.setDomain("agency.validPeriod");
		QueryResult<Parameter> pageResult = parameterService.queryParameter(parameter, page, request);
		String result = JSONUtilS.object2json(pageResult);
		return result;
	}
	
	@RequestMapping(value = "/editSigninTerm.html", produces = "application/json;charset=utf-8")
	public String editSigninTermPage(@RequestParam(required = true) Integer id, Model model) {
		Parameter parameter = new Parameter();
		parameter = parameterService.getParameterById(id);
		model.addAttribute("parameter", parameter);
		return "/manager/signinTermEdit";
	}
	
	@RequestMapping(value = "/editSigninTerm.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json editSigninTerm(HttpServletRequest request, HttpSession session, Parameter parameter) {
		Json json = new Json();
		json.setSuccess(false);
		int result = parameterService.updateParameter(parameter);
		if (result > 0) {
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
