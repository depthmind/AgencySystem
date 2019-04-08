package com.agency.crm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.Parameter;
import com.agency.crm.entity.Product;
import com.agency.crm.entity.PublishContent;
import com.agency.crm.mapper.agencyBase.AgencyBaseMapper;
import com.agency.crm.mapper.product.ProductMapper;
import com.agency.crm.mapper.publishContent.PublishContentMapper;

@Service
@Transactional(readOnly = false)
public class PublishContentService extends BaseService {

	@Autowired
	private PublishContentMapper publishContentMapper;
	
	/**
	 * 保存发布信息
	 * @param publishContent
	 * @return
	 */
	public int savePublishContent(PublishContent publishContent) {
		int result = 0;
		try {
			result = publishContentMapper.insertPublishContent(publishContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public List<PublishContent> getPublishContent(Map<String, Object> map) {
		return publishContentMapper.selectPublishContent(map);
	}

	/**
	 * 
	 * @date 2019年2月28日 下午4:04:42
	 * @author LiuHan
	 * @TODO 根据id查询发布内容详情
	 */
	public PublishContent findPublishContentById(int id) {
		PublishContent result = new PublishContent();
		try {
			result = publishContentMapper.selectPublishContentById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<PublishContent> findPublishContentByStatus(String status) {
		List<PublishContent> result = new ArrayList<PublishContent>();
		try {
			result = publishContentMapper.selectPublishContentByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<PublishContent> findPublishContentByParam(Map<String, Object> map) {
		List<PublishContent> result = new ArrayList<PublishContent>();
		try {
			result = publishContentMapper.selectPublishContentByParam(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<PublishContent> findPublishContentByParamNoLimit(Map<String, Object> map) {
		List<PublishContent> result = new ArrayList<PublishContent>();
		try {
			result = publishContentMapper.selectPublishContentByParamNoLimit(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public QueryResult<PublishContent> queryPublishContent(PublishContent publishContent, PageHelper pageHelper, HttpServletRequest request) {

		QueryResult<PublishContent> pageResult = new QueryResult<PublishContent>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("start", pageHelper.getStart());
		
		map.put("length", pageHelper.getLength());
		
		String status = publishContent.getStatus();
		String category = publishContent.getCategory();
		if (StringUtils.isNotBlank(publishContent.getMobilephone())) {
			map.put("mobilephone", publishContent.getMobilephone());
		}
		if (StringUtils.isNotBlank(publishContent.getContactName())) {
			map.put("contactName", publishContent.getContactName());
		}
		if (StringUtils.isNotBlank(publishContent.getCity())) {
			map.put("city", publishContent.getCity());
		}
		if (StringUtils.isNotBlank(publishContent.getDescription())) {
			map.put("description", "%" + publishContent.getDescription() + "%");
		}
		if (StringUtils.isNotBlank(status)) {
			map.put("status", status);
		}
		if (StringUtils.isNotBlank(category)) {
			map.put("category", category);
		}
		if (StringUtils.isNotBlank(publishContent.getSearchStartTime())) {
			map.put("searchStartTime", publishContent.getSearchStartTime() + " 00:00:00");
		}
		if (StringUtils.isNotBlank(publishContent.getSearchEndTime())) {
			map.put("searchEndTime", publishContent.getSearchEndTime() + " 00:00:00");
		}
		
		List<PublishContent> data = publishContentMapper.selectPublishContentByParam(map);
		long count = publishContentMapper.countPublishContentByParam(map);
		
		pageResult.setData(data);
		pageResult.setCountTotal(count);
		pageResult.setCountFiltered(count);
		
		return pageResult;
	}
	
	public int updatePublishContent(PublishContent publishContent) {
		int result = 0;
		try {
			result = publishContentMapper.updatePublishContent(publishContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
