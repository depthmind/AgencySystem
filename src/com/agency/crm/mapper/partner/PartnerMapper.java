package com.agency.crm.mapper.partner;

import java.util.List;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.Partner;

public interface PartnerMapper extends BaseMapper {

	int insertPartner(Partner partner);
	
	Partner selectPartnerByOpenId(String openId);
	
	List<Partner> selectPartnerByIntroducer(String openId);
}
