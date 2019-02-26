package com.agency.crm.service;

import com.agency.crm.entity.Goods;
import com.agency.crm.mapper.goods.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getGoods(Map<String, Object> params){
        return goodsMapper.selectGoods(params);
    }

}
