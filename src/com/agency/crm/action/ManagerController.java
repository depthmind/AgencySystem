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
import com.agency.crm.entity.Parameter;
import com.agency.crm.entity.PublishContent;
import com.agency.crm.service.AgencyBaseService;
import com.agency.crm.service.PublishContentService;

@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseSimpleFormController {

	@Autowired
	private PublishContentService publishContentService;
	@Autowired
	private AgencyBaseService agencyBaseService;

	@RequestMapping(value = "/publishContentList.html", produces = "application/json;charset=utf-8")
	public String publishContentListPage() {
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
		
		publishContent.setId(id);
		if ("2".equals(status)) {
			publishContent.setStatus(Constants.STATUS_OF_PUBLISH_CONTENT_TWO);
		}
		if ("3".equals(status)) {
			publishContent.setStatus(Constants.STATUS_OF_PUBLISH_CONTENT_THREE);
		}
		result = publishContentService.updatePublishContent(publishContent);
		if (result > 0) {
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
		
		agencyBase.setId(id);
		if ("2".equals(status)) {
			agencyBase.setStatus(Constants.STATUS_OF_AGENCY_BASE_TWO);
		}
		if ("3".equals(status)) {
			agencyBase.setStatus(Constants.STATUS_OF_AGENCY_BASE_THREE);
		}
		result = agencyBaseService.updateAgencyBase(agencyBase);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
}
