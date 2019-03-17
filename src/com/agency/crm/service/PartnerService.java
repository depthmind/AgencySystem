package com.agency.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.Partner;
import com.agency.crm.mapper.partner.PartnerMapper;

@Service
@Transactional(readOnly = false)
public class PartnerService extends BaseService {

	@Autowired
	private PartnerMapper partnerMapper;
	
	/**
	 * 保存合伙人信息
	 * @param agencyBase
	 * @return
	 */
	public int savePartner(Partner partner) {
		int result = 0;
		try {
			partnerMapper.insertPartner(partner);
			result = partner.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
