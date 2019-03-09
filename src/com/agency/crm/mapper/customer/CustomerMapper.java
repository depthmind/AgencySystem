/**
 * 
 */
package com.agency.crm.mapper.customer;

import com.agency.crm.entity.Customer;
import com.agency.crm.entity.MiniProgramFormId;

/**
 * @author zyy
 *
 */
public interface CustomerMapper {
	public Customer selectCustomerByOpenId(String openId);
	
	public Customer selectCustomerByUnionId(String unionId);
	
	public int saveCustomer(Customer customer);
}
