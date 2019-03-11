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
import com.agency.crm.entity.EntityList;
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
	
	@RequestMapping(value="/findSecondLevelCategoryById.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONArray findSecondLevelCategoryById(String id) {
		List<EntityList> list = categoryService.findSecondLevelCategoryAsParameter(id);
		return JSONArray.fromObject(list);
	}
}
