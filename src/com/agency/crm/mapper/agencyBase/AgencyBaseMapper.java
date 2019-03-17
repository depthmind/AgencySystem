package com.agency.crm.mapper.agencyBase;

import java.util.List;
import java.util.Map;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.AgencyBase;

public interface AgencyBaseMapper extends BaseMapper {

	int insertAgencyBase(AgencyBase agencyBase);
	
	List<AgencyBase> selectAgencyBase(Map<String, Object> map);
	
	AgencyBase selectAgencyBaseByOpenId(Map<String, Object> map);

	List<AgencyBase> selectAdAgency(Map params);

	List<AgencyBase> selectAgencyByName(Map map);
	
	AgencyBase selectAgencyBaseById(int id);
	
	long countAgencyBase();
	
	int updateAgencyBase(AgencyBase agencyBase);
}
