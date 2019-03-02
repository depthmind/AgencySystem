package com.agency.crm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.AgencyContact;
import com.agency.crm.entity.NearbyAgency;
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
		
		json.setSuccess(false);
		result = agencyBaseService.saveAgencyBase(agencyBase);
		if (result > 0) {
			json.setSuccess(true);
			agencyContact.setAgencyId(result + "");
			agencyContact.setContactName(agencyBase.getAgencyName());
			agencyContact.setMobilePhone(agencyBase.getMobilephone());
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
	public String findAgencyBase(String offset, String limit) {
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(offset) && StringUtils.isNotBlank(limit)) {
			int offsetInt = Integer.parseInt(offset);
			int limitInt = Integer.parseInt(limit);
			map.put("start", offsetInt);
			map.put("length", limitInt);
		}
		List<AgencyBase> list = new ArrayList<AgencyBase>();
		list = agencyBaseService.findAgencyBase(map);
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
	public String findAgencyByOpenId(String openId) {
		String result = "";
		AgencyBase agencyBase = new AgencyBase();
		agencyBase = agencyBaseService.findAgencyBaseByOpenId(openId);
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
	public String findAgencys(String name, String offset, String rows){
		name = "%" + name + "%";
		Map map = new HashMap();
		map.put("name", name);
		map.put("offset", Integer.parseInt(offset));
		map.put("rows", Integer.parseInt(rows));
		List<AgencyBase> agencyBaseList = agencyBaseService.findAgencyBaseByName(map);
		return JSONObject.toJSONString(agencyBaseList);
	}

}
