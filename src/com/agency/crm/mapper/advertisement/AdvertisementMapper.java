package com.agency.crm.mapper.advertisement;

import java.util.List;
import java.util.Map;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.AdvertisementRotation;

public interface AdvertisementMapper extends BaseMapper {

	List<AdvertisementRotation> selectAdvertisementRotationByParam(Map<String, Object> map);
}
