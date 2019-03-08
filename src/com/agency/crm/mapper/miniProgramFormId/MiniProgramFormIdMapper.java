/**
 * 
 */
package com.agency.crm.mapper.miniProgramFormId;

import com.agency.crm.entity.MiniProgramFormId;

/**
 * @author zyy
 *
 */
public interface MiniProgramFormIdMapper {
	public int saveMiniProgramFormId(MiniProgramFormId miniProgramFormId);
	
	public MiniProgramFormId selectFormIdByCreateTime(String openId);
	
	public void deleteMiniProgramFormId(String formId);
}
