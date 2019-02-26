package com.agency.crm.service;

import com.agency.crm.entity.IndexTab;
import com.agency.crm.mapper.indexTab.IndexTabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexTabService {

    @Autowired
    private IndexTabMapper indexTabMapper;

    public List<IndexTab> getIndexTabs(){
        return indexTabMapper.selectIndexTabs();
    }


}
