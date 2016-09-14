package cn.tf.bos.domain.bc;

import cn.tf.bos.domain.user.User;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Standard entity. @author MyEclipse Persistence Tools
 */

public class Standard  implements java.io.Serializable {


    // Fields    

     private String id;
     private User user;
     private String name;
     private Double minweight;
     private Double maxweight;
     private String deltag="0";
     private Timestamp updatetime;
     private Set staffs = new HashSet(0);


    // Constructors

    /** default constructor */
    public Standard() {
    }

	/** minimal constructor */
    public Standard(String id, Timestamp updatetime) {
        this.id = id;
        this.updatetime = updatetime;
    }
    
    /** full constructor */
    public Standard(String id, User user, String name, Double minweight, Double maxweight, String deltag, Timestamp updatetime, Set staffs) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.minweight = minweight;
        this.maxweight = maxweight;
        this.deltag = deltag;
        this.updatetime = updatetime;
        this.staffs = staffs;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Double getMinweight() {
        return this.minweight;
    }
    
    public void setMinweight(Double minweight) {
        this.minweight = minweight;
    }

    public Double getMaxweight() {
        return this.maxweight;
    }
    
    public void setMaxweight(Double maxweight) {
        this.maxweight = maxweight;
    }

    public String getDeltag() {
        return this.deltag;
    }
    
    public void setDeltag(String deltag) {
        this.deltag = deltag;
    }

    public Timestamp getUpdatetime() {
        return this.updatetime;
    }
    
    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public Set getStaffs() {
        return this.staffs;
    }
    
    public void setStaffs(Set staffs) {
        this.staffs = staffs;
    }
   








}