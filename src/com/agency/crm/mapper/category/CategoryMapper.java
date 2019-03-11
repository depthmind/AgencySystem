package com.agency.crm.mapper.category;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.EntityList;
import com.agency.crm.entity.Menu;
import com.agency.crm.entity.OneLevelCategory;
import com.agency.crm.entity.Role;
import com.agency.crm.entity.RoleMixMenu;
import com.agency.crm.entity.SecondLevelCategory;

public interface CategoryMapper extends BaseMapper {
	List<OneLevelCategory> selectAllOneLevelCategory();
	
	List<SecondLevelCategory> selectSecondLevelCategoryByOneLevelCategoryId(String oneLevelCategoryId);
	
	List<EntityList> selectOneLevelCategoryAsParameter();
	
	List<EntityList> selectSecondLevelCategoryAsParameter(String oneLevelCategoryId);
}
