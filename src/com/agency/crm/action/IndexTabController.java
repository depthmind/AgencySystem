package com.agency.crm.action;

import com.agency.crm.entity.IndexTab;
import com.agency.crm.service.IndexTabService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexTabController {

    @Autowired
    private IndexTabService indexTabService;

    @RequestMapping(value = "/getIndexTabs.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getIndexTabs() {
        List<IndexTab> indexTabList = indexTabService.getIndexTabs();
        return JSONObject.toJSONString(indexTabList);
    }

}
