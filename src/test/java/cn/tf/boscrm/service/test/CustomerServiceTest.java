package cn.tf.boscrm.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tf.boscrm.domain.Customer;
import cn.tf.boscrm.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CustomerServiceTest {
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void test1(){
		List<Customer>  customers=customerService.findNoAssoctionCustomer();
		System.out.println(customers);
	}
	
	@Test
	public void test2(){
		List<Customer>  customers=customerService.findHasAssoctionCustomer("a11");
		System.out.println(customers);
	}
	
	@Test
	public void test3(){
		
		String[]  customerIds={"1"};
		String decidedzoneId="1";
		customerService.assignedCustomerToDecidedzone(customerIds, decidedzoneId);
	}
	
	@Test
	public void test4(){
		String deciedZoneId=customerService.findDecideZoneIdByCustomerAddress("湖南衡阳珠晖区");
		System.out.println(deciedZoneId);
	}
	

}
