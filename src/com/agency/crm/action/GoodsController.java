package com.agency.crm.action;

import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.Goods;
import com.agency.crm.entity.Product;
import com.agency.crm.service.GoodsService;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
            @RequestParam(required = false) String rows,
            @RequestParam(required = false) String province,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String area) {

        Map params = new HashMap();
        if (StringUtils.isNotBlank(id))
            params.put("id", Long.parseLong(id));
        if (StringUtils.isNotBlank(productId))
            params.put("productId", productId);
        if (StringUtils.isNotBlank(agencyId))
            params.put("agencyId", Long.parseLong(agencyId));
        if (StringUtils.isNotBlank(goodsName))
            params.put("goodsName", "%" + goodsName + "%");
        if (StringUtils.isNotBlank(goodsDescription))
            params.put("goodsDescription", goodsDescription);
        if (StringUtils.isNotBlank(isTop))
            params.put("isTop", Integer.parseInt(isTop));
        if (StringUtils.isNotBlank(isDel))
            params.put("isDel", Integer.parseInt(isDel));
        if (StringUtils.isNotBlank(offset))
            params.put("offset", Integer.parseInt(offset));
        if (StringUtils.isNotBlank(rows))
            params.put("rows", Integer.parseInt(rows));
        if (StringUtils.isNotBlank(province))
            params.put("province", province);
        if (StringUtils.isNotBlank(city))
            params.put("city", city);
        if (StringUtils.isNotBlank(area))
            params.put("area", area);

        List<Goods> goodsList = goodsService.getGoods(params);
        return JSON.toJSONString(goodsList);
    }

    @RequestMapping(value = "/addGoodsAndProduct.html", produces = "application/json;charset=utf-8")
    public String addGoodsPage(Model model) {
    	
    	return "/goods/add";
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
    @RequestMapping(value = "/addGoodsAndProduct.do", produces = "application/json;charset=utf-8")
	@ResponseBody
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam("goodsPic") MultipartFile[] files,
            HttpServletRequest request) {
    	Goods goods = new Goods();
        List<String> list = new ArrayList<String>();
        String goodsName = request.getParameter("goodsName");
        String goodsCode = request.getParameter("goodsCode");
        goods.setGoodsName(goodsName);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }
        //写着测试，删了就可以
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        return "ok";//跳转的页面
    }
    
    /**
     * 
     * @date 2019年3月7日 下午5:14:56
     * @author LiuHan
     * @TODO 保存录入的规格信息
     */
    @RequestMapping(value = "/saveProduct.do", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Json saveProduct(HttpServletRequest request, String equations) {
    	Json json = new Json();
    	Product product = null;
    	List<Product> productList = new ArrayList<Product>();
    	JSONArray jsonArray = JSONArray.fromObject(equations);
    	for(int i = 0; i < jsonArray.size(); i++){
    		product = new Product();
    		JSONObject object = jsonArray.getJSONObject(i);
    		System.out.println(object.getString("productName"));
    		product.setPrice(new BigDecimal(object.getString("price")));
    		product.setProductName(object.getString("productName"));
    		product.setStock(object.getInt("stock"));
    		productList.add(product);
    	}
    	int result = goodsService.saveProductByBatch(productList);
    	json.setSuccess(false);
    	System.out.print(equations);
    	return json;
    }
    
    /**
     * 
     * @param request
     * @param file
     * @param list
     * @return
     */
    private List<String> saveFile(HttpServletRequest request,
            MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
                String filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + "upload/" + file.getOriginalFilename();
                list.add(file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
