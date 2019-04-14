/**
 * 
 */
package com.agency.crm.action;

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
import com.agency.crm.entity.Customer;
import com.agency.crm.entity.EntityList;
import com.agency.crm.service.CustomerService;

import net.sf.json.JSONArray;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseSimpleFormController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customerList.html", produces = "application/json;charset=utf-8")
	public String customerListPage(Model model) {
		return "/customer/list";
	}

	@RequestMapping(value = "/customerList.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String customerList(HttpServletRequest request, HttpSession session, Model model, Customer customer,
			PageHelper page) {
		QueryResult<Customer> pageResult = customerService.queryCustomer(customer, page, request);
		String result = JSONUtilS.object2json(pageResult);
		return result;
	}
	

}
