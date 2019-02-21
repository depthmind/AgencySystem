package com.agency.crm.mapper.publishContent;

import java.util.List;
import java.util.Map;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.PublishContent;

public interface PublishContentMapper extends BaseMapper {

	int insertPublishContent(PublishContent publishContent);
	
	List<AgencyBase> selectAgencyBase(Map<String, Object> map);
}
