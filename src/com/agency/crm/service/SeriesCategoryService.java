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
import com.agency.crm.entity.SeriesCategory;
import com.agency.crm.mapper.seriesCategory.SeriesCategoryMapper;

/**
 * @author zyy
 *
 */
@Service
@Transactional(readOnly = false)
public class SeriesCategoryService extends BaseService {

	@Autowired
	private SeriesCategoryMapper mapper;
	
	public int saveSeriesCategory(SeriesCategory seriesCategory) {
		int result = 0;
		try {
			result = mapper.saveSeriesCategory(seriesCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<SeriesCategory> findSeriesCategoryByBrandId(String brandId) {
		List<SeriesCategory> result = new ArrayList<SeriesCategory>();
		try {
			result = mapper.selectSeriesCategoryByBrandId(brandId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
