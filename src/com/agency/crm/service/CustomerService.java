package com.agency.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.Customer;
import com.agency.crm.mapper.customer.CustomerMapper;

@Service
@Transactional(readOnly = false)
public class CustomerService extends BaseService {

	@Autowired
	private CustomerMapper customerMapper;
	
	public Customer findCustomerByOpenId(String openId){
		Customer customer = new Customer();
		try {
			customer = customerMapper.selectCustomerByOpenId(openId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public Customer findCustomerByUnionId(String unionId){
		Customer customer = new Customer();
		try {
			customer = customerMapper.selectCustomerByUnionId(unionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public int saveCustomer(Customer customer) {
		int result = 0;
		try {
			result = customerMapper.saveCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
