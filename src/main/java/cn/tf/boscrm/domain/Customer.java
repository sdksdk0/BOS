package cn.tf.boscrm.domain;

import java.io.Serializable;

/**
 * 客户信息
 * 
 */
public class Customer implements Serializable {
	private String id;
	private String name;
	private String telephone;
	private String address;

	private String decidedzoneId;// 关联定区

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDecidedzoneId() {
		return decidedzoneId;
	}

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", telephone="
				+ telephone + ", address=" + address + ", decidedzoneId="
				+ decidedzoneId + "]";
	}
	
	
	

}
