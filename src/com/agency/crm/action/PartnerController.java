package com.agency.crm.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.Partner;
import com.agency.crm.service.PartnerService;

@Controller
@RequestMapping("/partner")
public class PartnerController extends BaseSimpleFormController {

	@Autowired
	private PartnerService partnerService;
	
	@RequestMapping(value="/savePartner.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Json savePartner(Partner partner) {
		Json json = new Json();
		json.setSuccess(false);
		int result = 0;
		result = partnerService.savePartner(partner);
		
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
}
