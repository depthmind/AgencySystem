package com.agency.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.Product;
import com.agency.crm.mapper.agencyBase.AgencyBaseMapper;
import com.agency.crm.mapper.product.ProductMapper;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Transactional(readOnly = false)
public class AgencyBaseService extends BaseService {

	@Autowired
	private AgencyBaseMapper agencyBaseMapper;
	
	/**
	 * 保存代理商基础信息
	 * @param agencyBase
	 * @return
	 */
	public int saveAgencyBase(AgencyBase agencyBase) {
		int result = 0;
		try {
			agencyBaseMapper.insertAgencyBase(agencyBase);
			result = agencyBase.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @date 2019年2月20日 下午2:12:06
	 * @author LiuHan
	 * @TODO 查询所有代理商信息
	 */
	public List<AgencyBase> findAgencyBase(Map<String, Object> map) {
		List<AgencyBase> result = new ArrayList<AgencyBase>();
		try {
			result = agencyBaseMapper.selectAgencyBase(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @date 2019年2月26日 下午4:42:09
	 * @author LiuHan
	 * @TODO 根据小程序openId查询代理商信息
	 */
	public AgencyBase findAgencyBaseByOpenId(String openId) {
		AgencyBase result = new AgencyBase();
		try {
			result = agencyBaseMapper.selectAgencyBaseByOpenId(openId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 2019.2.26
	 * kongzheng
	 * 获取轮播图信息
	 */
	public List<AgencyBase> getAdAgencyBase(Map params) {
		return agencyBaseMapper.selectAdAgency(params);
	}


	/**
	 * 2019.2.27
	 * kongzheng
	 * 根据名称模糊查询agency
	 */
	public List<AgencyBase> findAgencyBaseByName(Map map) {
		return agencyBaseMapper.selectAgencyByName(map);
	}

}
