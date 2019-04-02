package com.agency.crm.mapper.category;

import java.util.List;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.EntityList;
import com.agency.crm.entity.OneLevelCategory;
import com.agency.crm.entity.SecondLevelCategory;

public interface CategoryMapper extends BaseMapper {
	List<OneLevelCategory> selectAllOneLevelCategory();
	
	List<SecondLevelCategory> selectSecondLevelCategoryByOneLevelCategoryId(String oneLevelCategoryId);
	
	List<EntityList> selectOneLevelCategoryAsParameter();
	
	List<EntityList> selectSecondLevelCategoryAsParameter();
	
	int saveSecondLevelCategory(SecondLevelCategory secondLevelCategory);
}
