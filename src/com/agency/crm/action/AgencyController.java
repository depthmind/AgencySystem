package com.agency.crm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.agency.crm.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.framework.util.JSONUtilS;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.AgencyContact;
import com.agency.crm.entity.NearbyAgency;
import com.agency.crm.entity.Parameter;
import com.agency.crm.service.AgencyBaseService;
import com.agency.crm.service.AgencyContactService;
import com.agency.crm.service.NearbyAgencyService;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/agency")
public class AgencyController extends BaseSimpleFormController {

	@Autowired
	private AgencyBaseService agencyBaseService;
	@Autowired
	private AgencyContactService agencyContactService;
	@Autowired
	private NearbyAgencyService nearbyAgencyService;

	@RequestMapping(value = "/saveAgencyBase.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json saveAgencyBase(AgencyBase agencyBase) {
		int result = 0;
		Json json = new Json();
		AgencyContact agencyContact = new AgencyContact();
System.out.println("agencyBase=" + agencyBase.toString());
		json.setSuccess(false);
		result = agencyBaseService.saveAgencyBase(agencyBase);
		if (result > 0) {
			json.setSuccess(true);
			agencyContact.setAgencyId(result + "");
			agencyContact.setContactName(agencyBase.getAgencyName());
			agencyContact.setMobilephone(agencyBase.getMobilephone());
			agencyContact.setAddress(agencyBase.getAddress());
			agencyContact.setCity(agencyBase.getCity());
			agencyContact.setLocation(agencyBase.getLocation());
			agencyContact.setLatitude(agencyBase.getLatitude());
			agencyContact.setLongitude(agencyBase.getLongitude());
			agencyContact.setSysUserName(agencyBase.getSysUserName());
			agencyContact.setSysUserPassword(agencyBase.getSysUserPassword());
			agencyContactService.saveAgencyContact(agencyContact);
		}
		return json;
	}

	@RequestMapping(value = "/findAgencyBase.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findAgencyBase(String offset, String limit, @RequestParam(required = false) String currentLat,
			@RequestParam(required = false) String currentLon, @RequestParam(required = false) String city,
			@RequestParam(required = false) String province, @RequestParam(required = false) String area) {
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(offset) && StringUtils.isNotBlank(limit)) {
			int offsetInt = Integer.parseInt(offset);
			int limitInt = Integer.parseInt(limit);
			map.put("start", offsetInt);
			map.put("length", limitInt);
			map.put("city", city);
			map.put("area", area);
			map.put("province", province);
		}
		List<AgencyBase> list = new ArrayList<AgencyBase>();
		list = agencyBaseService.findAgencyBase(map);
		if (StringUtils.isNotBlank(currentLat) && StringUtils.isNotBlank(currentLon) && list.size() > 0) {
			for (AgencyBase agencyBase : list) {
				if (StringUtils.isNotBlank(agencyBase.getLatitude())
						&& StringUtils.isNotBlank(agencyBase.getLongitude())) {
					agencyBase.setDistance(String.valueOf(MapUtils.GetDistance(Double.parseDouble(currentLon),
							Double.parseDouble(currentLat), Double.parseDouble(agencyBase.getLongitude()),
							Double.parseDouble(agencyBase.getLatitude()))) + "千米");
				}
			}
		}
		result = JSONObject.toJSONString(list);
		return result;
	}

	/**
	 * 新增附近二批功能
	 */
	@RequestMapping(value = "/saveNearbyAgency.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json saveNearbyAgency(NearbyAgency nearbyAgency) {
		Json json = new Json();
		json.setSuccess(false);
		int result = nearbyAgencyService.saveNearbyAgency(nearbyAgency);
		if (result > 0) {
			json.setSuccess(true);
		}
		return json;
	}

	@RequestMapping(value = "/findAgencyByOpenId.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findAgencyByOpenId(String openId, String type) {
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openId", openId);
		if (StringUtils.isNotBlank(type)) {
			map.put("type", type);
		}
		AgencyBase agencyBase = new AgencyBase();
		agencyBase = agencyBaseService.findAgencyBaseByOpenId(map);
		result = JSONObject.toJSONString(agencyBase);
		return result;
	}

	@RequestMapping(value = "/findAdAgency.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findAdAgency(String offset, String rows) {
		Map<String, Object> params = new HashMap<>();
		params.put("offset", Integer.parseInt(offset));
		params.put("rows", Integer.parseInt(rows));
		List<AgencyBase> agencyBaseList = agencyBaseService.getAdAgencyBase(params);
		return JSONObject.toJSONString(agencyBaseList);
	}

	@RequestMapping(value = "/findAgencyByName.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findAgencys(String name, String offset, String rows, @RequestParam(required = false) String city,
			@RequestParam(required = false) String province, @RequestParam(required = false) String area) {
		name = "%" + name + "%";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("province", province);
		map.put("city", city);
		map.put("area", area);
		map.put("offset", Integer.parseInt(offset));
		map.put("rows", Integer.parseInt(rows));
		List<AgencyBase> agencyBaseList = agencyBaseService.findAgencyBaseByName(map);
		return JSONObject.toJSONString(agencyBaseList);
	}

	/**
	 * 用于查询代理商详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findAgencyById.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findAgencyById(@RequestParam(value = "id") Integer id) {
		String result = "";
		AgencyBase agencyBase = new AgencyBase();
		agencyBase = agencyBaseService.findAgencyBaseById(id);
		result = JSONObject.toJSONString(agencyBase);
		return result;
	}

	/**
	 * 新增员工
	 * 
	 * @param agencyId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addEmployee.html", produces = "application/json;charset=UTF-8")
	public String addEmployeePage(String agencyId, Model model) {
		model.addAttribute("agencyId", agencyId);
		return "/agency/addEmployee";
	}

	/**
	 * 新增员工
	 * 
	 * @param agencyContact
	 * @return
	 */
	@RequestMapping(value = "/addEmployee.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Json addEmployee(AgencyContact agencyContact) {
		Json json = new Json();
		int result = 0;
		json.setSuccess(false);

		result = agencyContactService.saveAgencyContact(agencyContact);
		if (result > 0) {
			json.setSuccess(true);
			json.setText(result + "");
		}
		return json;
	}

	@RequestMapping(value = "/employeeList.html", produces = "application/json;charset=UTF-8")
	public String employeeList(String agencyId, Model model) {
		model.addAttribute("agencyId", agencyId);
		return "/agency/employeeList";
	}

	/**
	 * 查询所有员工
	 * 
	 * @param agencyContact
	 * @return
	 */
	@RequestMapping(value = "/findAllEmployee.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findAllEmployee(String agencyId, HttpServletRequest request,
			HttpSession session, Model model, PageHelper page) {
		String result = "";

		QueryResult<AgencyContact> pageResult = agencyContactService.queryAgnecyContactByAgencyId(agencyId, page,
				request);
		result = JSONUtilS.object2json(pageResult);
		
		return result;
	}
	
	@RequestMapping(value = "/findAgnecyContactByAgencyId.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<AgencyContact> findAgnecyContactByAgencyId(String agencyId) {

		List<AgencyContact> list = agencyContactService.findAgnecyContactByAgencyId(agencyId);
		
		return list;
	}
	
	@RequestMapping(value = "/updateAgencyBaseByOpenId.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Json updateAgencyBaseByOpenId(AgencyBase agencyBase) {
		Json json = new Json();
		json.setSuccess(false);
		int result = 0;
		
		result = agencyBaseService.updateAgencyBaseByOpenId(agencyBase);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/deleteAgencyBase.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Json deleteAgencyBase(@RequestParam(required = true) Integer id) {
		Json json = new Json();
		json.setSuccess(false);
		int result = 0;
		AgencyBase agencyBase = new AgencyBase();
		agencyBase.setId(id);
		agencyBase.setIsDel(1);
		agencyBase.setIsCooperation(0);
		
		result = agencyBaseService.updateAgencyBase(agencyBase);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/deleteAgencyContact.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Json deleteAgencyContact(AgencyContact agencyContact) {
		Json json = new Json();
		json.setSuccess(false);
		int result = 0;
		
		agencyContact.setIsDel(1);
		result = agencyContactService.updateAgencyContact(agencyContact);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/updateAgencyContact.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Json updateAgencyContact(AgencyContact agencyContact) {
		Json json = new Json();
		json.setSuccess(false);
		int result = 0;
		
		result = agencyContactService.updateAgencyContact(agencyContact);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/findAgencyContactById.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AgencyContact findAgencyContactById(@RequestParam(required = true) Integer id) {
		AgencyContact agencyContact = new AgencyContact();
		
		agencyContact = agencyContactService.findAgencyContactById(id);
		
		return agencyContact;
	}
}
