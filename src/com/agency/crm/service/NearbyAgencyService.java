package com.agency.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.NearbyAgency;
import com.agency.crm.entity.Product;
import com.agency.crm.mapper.agencyBase.AgencyBaseMapper;
import com.agency.crm.mapper.nearbyAgency.NearbyAgencyMapper;
import com.agency.crm.mapper.product.ProductMapper;

@Service
@Transactional(readOnly = false)
public class NearbyAgencyService extends BaseService {

	@Autowired
	private NearbyAgencyMapper nearbyAgencyMapper;
	
	/**
	 * 保存代理商基础信息
	 * @param nearbyAgency
	 * @return
	 */
	public int saveNearbyAgency(NearbyAgency nearbyAgency) {
		int result = 0;
		try {
			result = nearbyAgencyMapper.insertNearbyAgency(nearbyAgency);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
