package com.agency.crm.mapper.agencyContact;

import java.util.List;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.AgencyContact;

public interface AgencyContactMapper extends BaseMapper {

	int insertAgencyContact(AgencyContact agencyContact);
	
	List<AgencyContact> selectAgnecyContactByAgencyId(int agencyId);
	
	int countAgnecyContactByAgencyId(int agencyId);
}
