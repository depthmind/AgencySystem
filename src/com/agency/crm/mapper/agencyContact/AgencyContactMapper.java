package com.agency.crm.mapper.agencyContact;

import java.util.List;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.AgencyContact;

public interface AgencyContactMapper extends BaseMapper {

	int insertAgencyContact(AgencyContact agencyContact);
	
	List<AgencyContact> selectAgnecyContactByAgencyId(String agencyId);
	
	int countAgnecyContactByAgencyId(String agencyId);
	
	int updateAgencyContact(AgencyContact agencyContact);
	
	AgencyContact selectAgencyContactById(int id);
}
