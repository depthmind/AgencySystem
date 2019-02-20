package com.agency.crm.mapper.nearbyAgency;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.NearbyAgency;

public interface NearbyAgencyMapper extends BaseMapper {

	int insertNearbyAgency(NearbyAgency nearbyAgency);
	
}
