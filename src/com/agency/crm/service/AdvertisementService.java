/**
 * 
 */
package com.agency.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.AdvertisementRecommend;
import com.agency.crm.entity.AdvertisementRotation;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.mapper.advertisement.AdvertisementMapper;

/**
 * @author zyy
 *
 */
@Service
@Transactional(readOnly = false)
public class AdvertisementService extends BaseService {
	
	@Autowired
	private AdvertisementMapper mapper;
	
	public QueryResult<AdvertisementRotation> queryAdvertisementRotation(AdvertisementRotation advertisementRotation, PageHelper pageHelper, HttpServletRequest request) {
		QueryResult<AdvertisementRotation> pageResult = new QueryResult<AdvertisementRotation>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("start", pageHelper.getStart());
		map.put("length", pageHelper.getLength());
		List<AdvertisementRotation> data = mapper.selectAdvertisementRotationByParam(map);
		long count = 6L;
		
		pageResult.setData(data);
		pageResult.setCountTotal(count);
		pageResult.setCountFiltered(count);
		
		return pageResult;
	}
	
	public QueryResult<AdvertisementRecommend> queryAdvertisementRecommend(AdvertisementRecommend advertisementRecommend, PageHelper pageHelper, HttpServletRequest request) {
		QueryResult<AdvertisementRecommend> pageResult = new QueryResult<AdvertisementRecommend>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("start", pageHelper.getStart());
		map.put("length", pageHelper.getLength());
		List<AdvertisementRecommend> data = mapper.selectAdvertisementRecommendByParam(map);
		long count = 6L;
		
		pageResult.setData(data);
		pageResult.setCountTotal(count);
		pageResult.setCountFiltered(count);
		
		return pageResult;
	}
}
