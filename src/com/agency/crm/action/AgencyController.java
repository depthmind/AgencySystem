package com.agency.crm.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.AgencyContact;
import com.agency.crm.service.AgencyBaseService;
import com.agency.crm.service.AgencyContactService;

@Controller
@RequestMapping("/agency")
public class AgencyController extends BaseSimpleFormController {

	@Autowired
	private AgencyBaseService agencyBaseService;
	@Autowired
	private AgencyContactService agencyContactService;
	
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
}
