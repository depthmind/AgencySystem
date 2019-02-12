package com.agency.crm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.entity.Product;
import com.agency.crm.service.ProductService;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/miniApp")
public class ProductController extends BaseSimpleFormController {

	@Autowired
	private ProductService productService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findAllProduct.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findAllProduct(String productName, String isTop, String state, String category) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productName", productName);
		if (StringUtils.isNotBlank(isTop)) {
			int isTopInt = Integer.parseInt(isTop);
			map.put("isTop", isTopInt);
		}
		if (StringUtils.isNotBlank(state)) {
			int stateInt = Integer.parseInt(state);
			map.put("state", stateInt);
		}
		if (StringUtils.isNotBlank(category)) {
			int categoryInt = Integer.parseInt(category);
			map.put("category", categoryInt);
		}
		List<Product> productList = productService.findAllProduct(map);
		String products = JSONObject.toJSONString(productList);
		return products;
	}
}
