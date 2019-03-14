package com.agency.crm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.framework.util.JSONUtilS;
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
}
