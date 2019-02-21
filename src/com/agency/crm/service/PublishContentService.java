package com.agency.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.AgencyBase;
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
	
}
