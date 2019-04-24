/**
 * 
 */
package com.agency.crm.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.BrandCategory;
import com.agency.crm.service.BrandCategoryService;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/brand")
public class BrandCategoryController extends BaseSimpleFormController {

	@Autowired
	private BrandCategoryService brandCategoryService;
	
	@RequestMapping(value = "/addBrandCategory.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json addBrandCategory(BrandCategory brandCategory) {
		Json json = new Json();
		int result = 0;
		
		json.setSuccess(false);
		result = brandCategoryService.saveBrandCategory(brandCategory);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	} 
	
	@RequestMapping(value = "/findBrandCategory.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<BrandCategory> findBrandCategoryByOpenId(String openId) {
		List<BrandCategory> result = new ArrayList<BrandCategory>();
		result = brandCategoryService.findBrandCategoryByOpenId(openId);
		return result;
	}
	
	@RequestMapping(value = "/findBrandCategoryById.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public BrandCategory findBrandCategoryById(@RequestParam(required = true) Integer id) {
		BrandCategory result = new BrandCategory();
		result = brandCategoryService.findBrandCategoryByBrandId(id);
		return result;
	}
	
	@RequestMapping(value = "/findBrandCategoryByAgencyId.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<BrandCategory> findBrandCategoryByAgencyId(String agencyId) {
		List<BrandCategory> result = new ArrayList<BrandCategory>();
		result = brandCategoryService.findBrandCategoryByAgencyId(agencyId);
		return result;
	}
	
	@RequestMapping(value = "/deleteBrandCategoryById.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json deleteBrandCategoryById(@RequestParam(required = true) Integer id) {
		Json json = new Json();
		json.setSuccess(false);
		int result = 0;
		
		result = brandCategoryService.deleteBrandCategoryById(id);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/updateBrandCategory.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateBrandCategory(BrandCategory brandCategory) {
		Json json = new Json();
		json.setSuccess(false);
		int result = 0;
		
		result = brandCategoryService.updateBrandCategory(brandCategory);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
}
