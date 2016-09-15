package cn.tf.bos.domain.bc;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


/**
 * Region entity. @author MyEclipse Persistence Tools
 */

public class Region  implements java.io.Serializable {


	private String id; // 编号 （assigned 委派）
	private String province; // 省份
	private String city; // 城市
	private String district; // 区域
	private String postcode; // 邮编
	private String shortcode; // 简码 （拼音缩写 ： 北京 BJ）
	private String citycode; // 城市编码 (拼音全拼 北京 beijing )

	private Set subareas = new HashSet(0); // 关联分区

	// Constructors

	/** default constructor */
	public Region() {
	}

	/** minimal constructor */
	public Region(String id) {
		this.id = id;
	}

	/** full constructor */
	public Region(String id, String province, String city, String district, String postcode, String shortcode, String citycode, Set subareas) {
		this.id = id;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortcode = shortcode;
		this.citycode = citycode;
		this.subareas = subareas;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShortcode() {
		return this.shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	@JSON(serialize = false)
	public Set getSubareas() {
		return this.subareas;
	}

	public void setSubareas(Set subareas) {
		this.subareas = subareas;
	}

	// 自定义方法
	public String getInfo() {
		// 返回省市区信息
		return province + "-" + city + "-" + district;
	}
}