package com.agency.crm.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public Partner findPartnerByOpenId(String openId) {
		Partner partner = new Partner();
		try {
			partner = partnerMapper.selectPartnerByOpenId(openId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partner;
	}
	
	public List<Partner> findPartnerByIntroducer(String openId) {
		List<Partner> partners = new ArrayList<Partner>();
		try {
			partners = partnerMapper.selectPartnerByIntroducer(openId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partners;
	}
}
