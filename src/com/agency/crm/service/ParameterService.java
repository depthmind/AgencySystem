package com.agency.crm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.common.framework.bean.QueryResult;
import com.agency.crm.common.model.base.value.baseconfig.PageHelper;
import com.agency.crm.entity.EntityList;
import com.agency.crm.entity.Parameter;
import com.agency.crm.mapper.parameter.ParameterMapper;

@Service
@Transactional(readOnly = false)
public class ParameterService extends BaseService {

	@Autowired
	private ParameterMapper parameterMapper;

	/**
	 * 查询系统参数数据，分页展示
	 * 
	 * @param parameter
	 * @param ph
	 * @param request
	 * @return
	 */
	public QueryResult<Parameter> queryParameter(Parameter parameter, PageHelper pageHelper, HttpServletRequest request) {

		QueryResult<Parameter> pageResult = new QueryResult<Parameter>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("start", pageHelper.getStart());
		
		map.put("length", pageHelper.getLength());
		if(parameter.getDomain()!=null){
			map.put("pdomain", parameter.getDomain());
		}
		
		if(parameter.getValue()!=null){
			map.put("pvalue", parameter.getValue());
		}
		
		if(parameter.getChinese()!=null){
			map.put("chinese", parameter.getChinese());
		}
		
		if(parameter.getEnglish()!=null){
			map.put("english", parameter.getEnglish());
		}
		List<Parameter> data = parameterMapper.queryParameter(map);
		long count = parameterMapper.countParameter(parameter);
		
		pageResult.setData(data);
		pageResult.setCountTotal(count);
		pageResult.setCountFiltered(count);
		
		return pageResult;
	}

	/**
	 * 新增系统参数
	 * 
	 * @param parameter
	 * @return
	 */
	public int saveParameter(Parameter parameter) {

		try {
			parameterMapper.saveParameter(parameter);
		} catch (Exception e) {
			logger.error("ParameterService.saveParameter() --> " + parameter + "-->" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return parameter.getParameterId();
	}

	/**
	 * 根据主键获取系统参数信息
	 * 
	 * @param id
	 * @return
	 */
	public Parameter getParameterById(int parameterId) {
		Parameter parameter = null;
		try {
			parameter = parameterMapper.getParameterById(parameterId);
		} catch (Exception e) {
			logger.error("ParameterService.getParameterById() --> " + parameterId + "-->" + e.getMessage());
			parameter = null;
		}
		return parameter;
	}

	/**
	 * 更新系统参数信息(不修改密码)
	 * 
	 * @param parameter
	 * @return
	 */
	public int updateParameter(Parameter parameter) {
		int result = 0;
		try {
			result = parameterMapper.updateParameter(parameter);
		} catch (Exception e) {
			logger.error("ParameterService.updateParameter() --> " + parameter + "-->" + e.getMessage());
		}
		return result;
	}

	/**
	 * 删除系统参数（假删除）
	 * 
	 * @param parameterId
	 * @return
	 */
	public void deleteParameterById(int parameterId) {
		try {
			parameterMapper.deleteParameterById(parameterId);
		} catch (Exception e) {
			logger.error("ParameterService.deleteParameterById() --> " + parameterId + "-->" + e.getMessage());
		}
	}

	//查询所有活动名称
	public List<EntityList> selectAllActivityName(){
		try {
			return parameterMapper.selectAllActivityName();
		} catch (Exception e) {
			logger.error("parameterService.selectAllActivityName()-->" + e.getMessage());
		}
	   return null;
	}
	
	/**
	 * 
	 * @date 2018年6月7日 上午11:33:52
	 * @author liuhan
	 * @todo 通过para_domain查询参数
	 */
	public List<Parameter> findParameterByParaDomain(String paraDomain) {
		List<Parameter> list = new ArrayList<Parameter>();
		try {
			list = parameterMapper.selectParameterByParaDomain(paraDomain);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	/**
	 *
	 * @Description: 根据名称和作用域查询对应的值
	 * @author : syl
	 * @date 2018年7月3日
	 */
	public String getParaValue(String chinese,String domain){
		if(StringUtils.isNotBlank(chinese)){
			return parameterMapper.selectParaValue(chinese, domain);
		}
		return null;
	}
	
	public List<EntityList> getParameterInfo(String domain) {
		List<EntityList> result = null;
		try {
			result = parameterMapper.getParameterInfo(domain);
		} catch (Exception e) {
			logger.error("AgencyService.getParameterInfo() --> " + domain + "-->" + e.getMessage());
			result = null;
		}
		return result;
	}
	
	public Parameter findSingleParameterByDomain(String domain) {
		Parameter parameter = new Parameter();
		try {
			parameter = parameterMapper.selectSingleParameterByDomain(domain);
		} catch (Exception e) {
			logger.error("ParameterService.findSingleParameterByDomain() --> " + domain + "-->" + e.getMessage());
		}
		
		return parameter;
	}
}
