package com.agency.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.AgencyContact;
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
}
