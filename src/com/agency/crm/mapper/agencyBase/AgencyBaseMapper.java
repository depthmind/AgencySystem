package com.agency.crm.mapper.agencyBase;

import java.util.List;
import java.util.Map;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.AgencyBase;

public interface AgencyBaseMapper extends BaseMapper {

	int insertAgencyBase(AgencyBase agencyBase);
	
	List<AgencyBase> selectAgencyBase(Map<String, Object> map);
	
	AgencyBase selectAgencyBaseByOpenId(String openId);

	List<AgencyBase> selectAdAgency();

	List<AgencyBase> selectAgencyByName(Map map);
}
