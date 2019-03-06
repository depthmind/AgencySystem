package com.agency.crm.action;

import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.Goods;
import com.agency.crm.service.GoodsService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/getGoods.do", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getGodos(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String productId,
            @RequestParam(required = false) String agencyId,
            @RequestParam(required = false) String goodsName,
            @RequestParam(required = false) String goodsDescription,
            @RequestParam(required = false) String isTop,
            @RequestParam(required = false) String isDel,
            @RequestParam(required = false) String offset,
            @RequestParam(required = false) String rows) {

        Map params = new HashMap();
        if (!StringUtils.isEmpty(id))
            params.put("id", Long.parseLong(id));
        if (!StringUtils.isEmpty(productId))
            params.put("productId", productId);
        if (!StringUtils.isEmpty(agencyId))
            params.put("agencyId", Long.parseLong(agencyId));
        if (!StringUtils.isEmpty(goodsName))
            params.put("goodsName", "%" + goodsName + "%");
        if (!StringUtils.isEmpty(goodsDescription))
            params.put("goodsDescription", goodsDescription);
        if (!StringUtils.isEmpty(isTop))
            params.put("isTop", Integer.parseInt(isTop));
        if (!StringUtils.isEmpty(isDel))
            params.put("isDel", Integer.parseInt(isDel));
        if (!StringUtils.isEmpty(offset))
            params.put("offset", Integer.parseInt(offset));
        if (!StringUtils.isEmpty(rows))
            params.put("rows", Integer.parseInt(rows));

        List<Goods> goodsList = goodsService.getGoods(params);
        return JSON.toJSONString(goodsList);
    }

    @RequestMapping(value = "/addGoodsAndProduct.html", produces = "application/json;charset=utf-8")
    public String addGoodsPage(Model model) {
    	
    	return "";
    }
    /**
     * 
     * @date 2019年3月6日 下午5:40:29
     * @author LiuHan
     * @TODO 新增产品
     */
    @RequestMapping(value = "/addGoodsAndProduct.do", produces = "application/json;charset=utf-8")
    public Json addGoods(Goods goods) {
    	Json json = new Json();
    	json.setSuccess(false);
    	int result = 0;
    	result = goodsService.saveGoods(goods);
    	if (result > 0) {
    		json.setSuccess(true);
    	}
    	return json;
    }
}
