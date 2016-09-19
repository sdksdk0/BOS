package cn.tf.boscrm.service;

import java.util.List;

import cn.tf.boscrm.domain.Customer;

public interface CustomerService {
	//查询未关联的
	public List<Customer>  findNoAssoctionCustomer();

	//查询已关联的
	public List<Customer>  findHasAssoctionCustomer(String decidedzoneId);

	//关联到定区
	public void assignedCustomerToDecidedzone(String [] customerIds,String decidedzoneId);

	public String findDecideZoneIdByCustomerAddress(String address);
	
}
