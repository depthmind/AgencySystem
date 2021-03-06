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
	
	/**
	 * 根据传入参数作为条件查询产品信息
	 * @param params
	 * @return
	 */
	public List<Product> findAllProduct(Map<String, Object> params) {
		List<Product> productList = new ArrayList<Product>();
		try {
			productList = productMapper.selectAllProduct(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	/**
	 * 根据ID查询产品
	 * @param id
	 * @return
	 */
	public Product findProductById(int id) {
		Product product = new Product();
		try {
			product = productMapper.selectProductById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
	/**
	 * 保存产品信息
	 * @param product
	 * @return
	 */
	public int saveProduct(Product product) {
		int result = 0;
		try {
			result = productMapper.insertProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
