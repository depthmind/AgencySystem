package com.agency.crm.mapper.parameter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.agency.crm.common.framework.BaseMapper;
import com.agency.crm.entity.EntityList;
import com.agency.crm.entity.Parameter;

public interface ParameterMapper extends BaseMapper {
	
	List<Parameter> queryParameter(Map<String, Object> params);
	
	long countParameter(Parameter Parameter);
	
	void saveParameter(Parameter Parameter);

	void updateParameter(Parameter Parameter);

	void deleteParameterById(int ParameterId);
	
	Parameter getParameterById(int ParameterId);
	
	List<EntityList> selectAllActivityName();
	
	List<Parameter> selectParameterByParaDomain(String paraDomain);
	
	String selectParaValue(@Param("chinese")String chinese,@Param("domain")String domain);
}