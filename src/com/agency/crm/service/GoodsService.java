package com.agency.crm.service;

import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.AgencyContact;
import com.agency.crm.entity.Goods;
import com.agency.crm.entity.Product;
import com.agency.crm.mapper.goods.GoodsMapper;
import com.agency.crm.mapper.product.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getGoods(Map<String, Object> params){
        return goodsMapper.selectGoods(params);
    }

    public int saveGoods(Goods goods) {
    	int result = 0;
    	try {
    		result = goodsMapper.saveGoods(goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    public int saveProductByBatch(List<Product> list) {
    	int result = 0;
    	try {
    		result = goodsMapper.saveProductByBatch(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    public QueryResult<Goods> queryGoodsByAgencyId(String agencyId, PageHelper pageHelper, HttpServletRequest request) {

		QueryResult<Goods> pageResult = new QueryResult<Goods>();
		List<Goods> data = goodsMapper.selectGoodsByAgencyId(agencyId);
		long count = goodsMapper.countGoodsByAgencyId(agencyId);
		
		pageResult.setData(data);
		pageResult.setCountTotal(count);
		pageResult.setCountFiltered(count);
		
		return pageResult;
	}
}
