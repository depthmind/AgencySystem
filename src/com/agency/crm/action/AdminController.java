package com.agency.crm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.framework.util.JSONUtilS;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.PlayerRecharge;
import com.agency.crm.entity.User;
import com.agency.crm.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseSimpleFormController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index.html", produces = "application/json;charset=utf-8")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		return "/admin/index";
	}
	/**
	 * 封禁用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/disable.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json disable(String id) {
		Json json = new Json();
		int userId;
		if (StringUtils.isBlank(id)) {
			json.setSuccess(false);
			json.setMsg("用户不存在");
			return json;
		} else {
			userId = Integer.parseInt(id);
		}
		User user = new User();
		user.setInvalid(0);
		user.setUserId(userId);
		int result = userService.updateUser(user);
		if (result > 0) {
			json.setSuccess(true);
		}
		return json;
	}
	
	/**
	 * 解除封禁
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/enable.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json enable(String id) {
		Json json = new Json();
		int userId;
		if (StringUtils.isBlank(id)) {
			json.setSuccess(false);
			json.setMsg("用户不存在");
			return json;
		} else {
			userId = Integer.parseInt(id);
		}
		User user = new User();
		user.setInvalid(1);
		user.setUserId(userId);
		int result = userService.updateUser(user);
		if (result > 0) {
			json.setSuccess(true);
		}
		return json;
	}
}
