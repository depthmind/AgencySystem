/**
 * 
 */
package com.agency.crm.mapper.withdrawalRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.agency.crm.entity.Customer;
import com.agency.crm.entity.MiniProgramFormId;
import com.agency.crm.entity.WithdrawalRecord;

/**
 * @author zyy
 *
 */
public interface WithdrawalRecordMapper {
	public Customer selectCustomerByOpenId(String openId);
	
	public Customer selectCustomerByUnionId(String unionId);
	
	public int saveWithdrawalRecord(WithdrawalRecord withdrawalRecord);
	
	List<WithdrawalRecord> selectWithdrawalRecordByStatus(Map<String, Object> map);
	
	List<WithdrawalRecord> queryWithdrawalRecord(Map<String, Object> map);
	
	WithdrawalRecord sumWithdrawalRecordByParam(Map<String, Object> map);
	
	long countWithdrawalRecord();
	
	int updateWithdrawalRecordByParam(Map<String, Object> map);
}
