package com.agency.crm.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.Partner;
import com.agency.crm.entity.WithdrawalRecord;
import com.agency.crm.service.PartnerService;
import com.agency.crm.service.WithdrawalRecordService;

@Controller
@RequestMapping("/withdrawal")
public class WithdrawalRecordController extends BaseSimpleFormController {

	@Autowired
	private WithdrawalRecordService withdrawalRecordService;
	@Autowired
	private PartnerService partnerService;
	
	@RequestMapping(value="/add.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Json saveWithdrawalRecord(WithdrawalRecord withdrawalRecord) {
		Json json =new Json();
		json.setSuccess(false);
		int result = 0;
		
		result = withdrawalRecordService.saveWithdrawalRecord(withdrawalRecord);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value="/findWithdrawalRecord.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public List<WithdrawalRecord> findWithdrawalRecordByStatus1(String status, String openId) {
		List<WithdrawalRecord> result =new ArrayList<WithdrawalRecord>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("status", status);
		map.put("openId", openId);
		result = withdrawalRecordService.findWithdrawalRecordByStatus(map);
		
		return result;
	}
	
	@RequestMapping(value="/sumWithdrawalRecord.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public WithdrawalRecord sumWithdrawalRecordByParam(String openId) {
		WithdrawalRecord result = new WithdrawalRecord();
		Partner partner = new Partner();
		WithdrawalRecord withdrawalRecord =new WithdrawalRecord();
		Map<String, Object> map = new HashMap<String, Object>();
		
		partner = partnerService.findPartnerByOpenId(openId);
		map.put("status", '1');
		map.put("openId", openId);
		result = withdrawalRecordService.sumWithdrawalRecordByParam(map);
		
		withdrawalRecord.setApplyAmount(result.getApplyAmount());
		result = new WithdrawalRecord();
		map.put("status", '2');
		result = withdrawalRecordService.sumWithdrawalRecordByParam(map);
		withdrawalRecord.setSuccessAmount(result.getApplyAmount());
		withdrawalRecord.setAmount(partner.getCommission());
		
		return withdrawalRecord;
	}
	
	@RequestMapping(value="/del.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Json updateStatusById(@RequestParam(required = true) Integer id, String status) {
		Json json = new Json();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", id);
		map.put("isDel", 1);
		json.setSuccess(false);
		int result = 0;
		
		result = withdrawalRecordService.updateWithdrawalRecordByParam(map);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
}
