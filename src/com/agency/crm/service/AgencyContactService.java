package com.agency.crm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.AgencyContact;
import com.agency.crm.entity.Parameter;
import com.agency.crm.mapper.agencyBase.AgencyBaseMapper;
import com.agency.crm.mapper.agencyContact.AgencyContactMapper;

@Service
@Transactional(readOnly = false)
public class AgencyContactService extends BaseService {

	@Autowired
	private AgencyContactMapper agencyContactMapper;
	
	/**
	 * 保存代理商基础信息
	 * @param agencyBase
	 * @return
	 */
	public int saveAgencyContact(AgencyContact agencyContact) {
		int result = 0;
		try {
			result = agencyContactMapper.insertAgencyContact(agencyContact);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param agencyId
	 * @return 查询代理商的所有员工
	 */
	public List<AgencyContact> findAgnecyContactByAgencyId(int agencyId) {
		List<AgencyContact> result = new ArrayList<AgencyContact>();
		try {
			result = agencyContactMapper.selectAgnecyContactByAgencyId(agencyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public QueryResult<AgencyContact> queryAgnecyContactByAgencyId(int agencyId, PageHelper pageHelper, HttpServletRequest request) {

		QueryResult<AgencyContact> pageResult = new QueryResult<AgencyContact>();
		List<AgencyContact> data = agencyContactMapper.selectAgnecyContactByAgencyId(agencyId);
		long count = agencyContactMapper.countAgnecyContactByAgencyId(agencyId);
		
		pageResult.setData(data);
		pageResult.setCountTotal(count);
		pageResult.setCountFiltered(count);
		
		return pageResult;
	}
}
