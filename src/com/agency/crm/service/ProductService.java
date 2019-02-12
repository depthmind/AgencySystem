package com.agency.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.Product;
import com.agency.crm.mapper.product.ProductMapper;

@Service
@Transactional(readOnly = false)
public class ProductService extends BaseService {

	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> findAllProduct(Map<String, Object> params) {
		List<Product> productList = new ArrayList<Product>();
		try {
			productList = productMapper.selectAllProduct(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
}
