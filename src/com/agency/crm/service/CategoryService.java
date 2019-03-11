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
import com.agency.crm.entity.EntityList;
import com.agency.crm.entity.OneLevelCategory;
import com.agency.crm.entity.SecondLevelCategory;
import com.agency.crm.mapper.category.CategoryMapper;

/**
 * @author zyy
 *
 */
@Service
@Transactional(readOnly = false)
public class CategoryService extends BaseService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * 
	 * @date 2019年3月11日 上午11:56:48
	 * @author LiuHan
	 * @TODO 查询所有一级分类
	 */
	public List<OneLevelCategory> findAllOneLevelCategory() {
		List<OneLevelCategory> result = new ArrayList<OneLevelCategory>();
		try {
			result = categoryMapper.selectAllOneLevelCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @date 2019年3月11日 上午11:57:01
	 * @author LiuHan
	 * @TODO 根据一级分类id查询所有二级分类
	 */
	public List<SecondLevelCategory> findSecondLevelCategoryByOneLevelCategoryId(String oneLevelCategoryId) {
		List<SecondLevelCategory> result = new ArrayList<SecondLevelCategory>();
		try {
			result = categoryMapper.selectSecondLevelCategoryByOneLevelCategoryId(oneLevelCategoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<EntityList> findOneLevelCategoryAsParameter() {
		List<EntityList> result = new ArrayList<EntityList>();
		try {
			result = categoryMapper.selectOneLevelCategoryAsParameter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<EntityList> findSecondLevelCategoryAsParameter(String oneLevelCategoryId) {
		List<EntityList> result = new ArrayList<EntityList>();
		try {
			result = categoryMapper.selectSecondLevelCategoryAsParameter(oneLevelCategoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
