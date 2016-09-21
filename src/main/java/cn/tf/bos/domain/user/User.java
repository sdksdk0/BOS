package cn.tf.bos.domain.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.tf.bos.domain.auth.Role;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private String id;
     private String username;
     private String password;
     private String gender;
     private Date birthday;
     private Double salary;
     private String station;
     private String telephone;
     private String remark;
     private Set standards = new HashSet(0);
     
     private Role role;


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String id) {
        this.id = id;
    }
    

   
    public User(String id, String username, String password, String gender,
			Date birthday, Double salary, String station, String telephone,
			String remark, Set standards, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.birthday = birthday;
		this.salary = salary;
		this.station = station;
		this.telephone = telephone;
		this.remark = remark;
		this.standards = standards;
		this.role = role;
	}

	// Property accessors

    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return this.salary;
    }
    
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getStation() {
        return this.station;
    }
    
    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getStandards() {
        return this.standards;
    }
    
    public void setStandards(Set standards) {
        this.standards = standards;
    }
   








}