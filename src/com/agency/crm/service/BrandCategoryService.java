/**
 * 
 */
package com.agency.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.BrandCategory;
import com.agency.crm.mapper.brandCategory.BrandCategoryMapper;

/**
 * @author zyy
 *
 */
@Service
@Transactional(readOnly = false)
public class BrandCategoryService extends BaseService {

	@Autowired
	private BrandCategoryMapper mapper;
	
	public int saveBrandCategory(BrandCategory brandCategory) {
		int result = 0;
		try {
			result = mapper.saveBrandCategory(brandCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<BrandCategory> findBrandCategoryByOpenId(String openId) {
		List<BrandCategory> result = new ArrayList<BrandCategory>();
		try {
			result = mapper.selectBrandCategoryByOpenId(openId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public BrandCategory findBrandCategoryByBrandId(int id) {
		BrandCategory result = new BrandCategory();
		try {
			result = mapper.selectBrandCategoryByBrandId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<BrandCategory> findBrandCategoryByAgencyId(String agencyId) {
		List<BrandCategory> result = new ArrayList<BrandCategory>();
		try {
			result = mapper.selectBrandCategoryByAgencyId(agencyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteBrandCategoryById(int id) {
		int result = 0;
		try {
			result = mapper.deleteBrandCategoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateBrandCategory(BrandCategory brandCategory) {
		int result = 0;
		try {
			result = mapper.updateBrandCategory(brandCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
