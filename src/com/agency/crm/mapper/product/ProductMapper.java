package com.agency.crm.mapper.product;

import java.util.List;
import java.util.Map;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.Product;

public interface ProductMapper extends BaseMapper {

	List<Product> selectAllProduct(Map<String, Object> params);

	Product selectProductById(int id);
	
	int insertProduct(Product product);
}
