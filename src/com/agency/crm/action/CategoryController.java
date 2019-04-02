/**
 * 
 */
package com.agency.crm.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.EntityList;
import com.agency.crm.entity.SecondLevelCategory;
import com.agency.crm.service.CategoryService;

import net.sf.json.JSONArray;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseSimpleFormController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/findOneLevelCategory.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONArray findOneLevelCategoryById() {
		List<EntityList> list = categoryService.findOneLevelCategoryAsParameter();
		return JSONArray.fromObject(list);
	}
	
	@RequestMapping(value="/findSecondLevelCategory.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONArray findSecondLevelCategoryById() {
		List<EntityList> list = categoryService.findSecondLevelCategoryAsParameter();
		return JSONArray.fromObject(list);
	}
	
	@RequestMapping(value="/saveSecondLevelCategory.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Json saveSecondLevelCategory(SecondLevelCategory secondLevelCategory) {
		Json json = new Json();
		int result = 0;
		json.setSuccess(false);
		
		result = categoryService.saveSecondLevelCategory(secondLevelCategory);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
}
