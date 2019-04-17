package com.agency.crm.mapper.goods;

import com.agency.crm.entity.Goods;
import com.agency.crm.entity.Product;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {

    //查询所有商品--分页
    List<Goods> selectGoods(Map<String, Object> params);

    int saveGoods(Goods goods);
    
    int saveProductByBatch(List<Product> list);
    
    List<Goods> selectGoodsByAgencyId(String agencyId);
    
    int countGoodsByAgencyId(String agencyId);
    
    Goods selectGoodsByGoodsId(int id);
    
    List<Goods> selectGoodsByOpenId(String openId);
    
    List<Goods> selectGoodsByBrandCategory(Map<String, Object> params);
    
    int updateGoods(Goods goods);
}
