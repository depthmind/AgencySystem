package com.agency.crm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.Customer;
import com.agency.crm.entity.WithdrawalRecord;
import com.agency.crm.mapper.customer.CustomerMapper;
import com.agency.crm.mapper.withdrawalRecord.WithdrawalRecordMapper;

@Service
@Transactional(readOnly = false)
public class WithdrawalRecordService extends BaseService {

	@Autowired
	private WithdrawalRecordMapper withdrawalRecordMapper;
	
	public int saveWithdrawalRecord(WithdrawalRecord withdrawalRecord) {
		int result = 0;
		try {
			result = withdrawalRecordMapper.saveWithdrawalRecord(withdrawalRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<WithdrawalRecord> findWithdrawalRecordByStatus(Map<String, Object> map) {
		List<WithdrawalRecord> result = new ArrayList<WithdrawalRecord>();
		try {
			result = withdrawalRecordMapper.selectWithdrawalRecordByStatus(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public WithdrawalRecord sumWithdrawalRecordByParam(Map<String, Object> map) {
		WithdrawalRecord result = new WithdrawalRecord();
		try {
			result = withdrawalRecordMapper.sumWithdrawalRecordByParam(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public QueryResult<WithdrawalRecord> queryWithdrawalRecord(WithdrawalRecord withdrawalRecord, PageHelper pageHelper, HttpServletRequest request) {

		QueryResult<WithdrawalRecord> pageResult = new QueryResult<WithdrawalRecord>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("start", pageHelper.getStart());
		map.put("length", pageHelper.getLength());
		
		if (StringUtils.isNotBlank(withdrawalRecord.getWechat())) {
			map.put("wechat", withdrawalRecord.getWechat());
		}
		if (StringUtils.isNotBlank(withdrawalRecord.getStatus())) {
			map.put("status", withdrawalRecord.getStatus());
		}
		if (StringUtils.isNotBlank(withdrawalRecord.getSearchStartTime())) {
			map.put("searchStartTime", withdrawalRecord.getSearchStartTime());
		}
		if (StringUtils.isNotBlank(withdrawalRecord.getSearchEndTime())) {
			map.put("searchEndTime", withdrawalRecord.getSearchEndTime());
		}

		List<WithdrawalRecord> data = withdrawalRecordMapper.queryWithdrawalRecord(map);
		long count = withdrawalRecordMapper.countWithdrawalRecord();

		pageResult.setData(data);
		pageResult.setCountTotal(count);
		pageResult.setCountFiltered(count);

		return pageResult;
	}
	
	public int updateWithdrawalRecordByParam(Map<String, Object> map) {
		int result = 0;
		try {
			result = withdrawalRecordMapper.updateWithdrawalRecordByParam(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
