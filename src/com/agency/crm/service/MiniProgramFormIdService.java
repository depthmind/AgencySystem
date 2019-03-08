/**
 * 
 */
package com.agency.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.MiniProgramFormId;
import com.agency.crm.mapper.miniProgramFormId.MiniProgramFormIdMapper;

/**
 * @author zyy
 *
 */
@Service
@Transactional(readOnly = false)
public class MiniProgramFormIdService extends BaseService {

	@Autowired
	private MiniProgramFormIdMapper mapper;
	
	public int saveMiniProgramFormId(MiniProgramFormId miniProgramFormId) {
		int result = 0;
		try {
			mapper.saveMiniProgramFormId(miniProgramFormId);
		} catch (Exception e) {
			logger.error("MiniProgramFormIdService.saveFormId()--------miniProgramFormId=" + miniProgramFormId + "\n" + e.getMessage());
		}
		return result;
	}
	
	public MiniProgramFormId getFormIdByCreateTime(String openId) {
		MiniProgramFormId result = new MiniProgramFormId();
		try {
			result = mapper.selectFormIdByCreateTime(openId);
		} catch (Exception e) {
			logger.error("MiniProgramFormIdService.getFormIdByCreateTime()" + "\n" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * @date 2018年3月13日 上午11:27:36
	 * @author liuhan
	 * @todo formId用完一次就删除
	 */
	public void deleteMiniProgramFormId(String formId) {
		try {
			mapper.deleteMiniProgramFormId(formId);
		} catch (Exception e) {
			logger.error("MiniProgramFormIdService.deleteMiniProgramFormId()" + "\n" + e.getMessage());
		}
	}
}
