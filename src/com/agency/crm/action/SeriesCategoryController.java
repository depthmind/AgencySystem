/**
 * 
 */
package com.agency.crm.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.SeriesCategory;
import com.agency.crm.service.SeriesCategoryService;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/series")
public class SeriesCategoryController extends BaseSimpleFormController {

	@Autowired
	private SeriesCategoryService seriesCategoryService;
	
	@RequestMapping(value = "/addSeriesCategory.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json addSeriesCategory(SeriesCategory seriesCategory) {
		Json json = new Json();
		int result = 0;
		
		json.setSuccess(false);
		result = seriesCategoryService.saveSeriesCategory(seriesCategory);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/findSeriesCategory.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<SeriesCategory> findSeriesCategoryByBrandId(String brandId) {
		List<SeriesCategory> result = new ArrayList<SeriesCategory>();
		result = seriesCategoryService.findSeriesCategoryByBrandId(brandId);
		return result;
	}
}
