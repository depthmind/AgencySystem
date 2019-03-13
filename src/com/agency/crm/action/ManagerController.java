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
import com.agency.crm.entity.Parameter;
import com.agency.crm.entity.PublishContent;
import com.agency.crm.service.PublishContentService;

@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseSimpleFormController {

	@Autowired
	private PublishContentService publishContentService;

	@RequestMapping(value = "/publishContentList.html", produces = "application/json;chatset=utf-8")
	public String publishContentListPage() {
		return "/manager/list";
	}

	@RequestMapping(value = "/publishContentList.do", produces = "application/json;chatset=utf-8")
	@ResponseBody
	public String publishContentList(HttpServletRequest request, HttpSession session, Model model, PublishContent publishContent,
			PageHelper page) {
		QueryResult<PublishContent> pageResult = publishContentService.queryPublishContent(publishContent, page, request);
		String result = JSONUtilS.object2json(pageResult);
		return result;
	}

}
