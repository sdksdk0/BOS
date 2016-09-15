package cn.tf.bos.web.action.bc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.tf.bos.domain.bc.Region;
import cn.tf.bos.domain.bc.Subarea;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.utils.PinYin4jUtils;
import cn.tf.bos.web.action.BaseAction;



//标准管理
public class SubareaAction extends BaseAction  implements ModelDriven<Subarea>{
	
private Subarea subarea=new Subarea();
	
	public Subarea getModel() {
		return subarea;
	}
	
	public String saveOrUpdate(){
		subareaService.saveOrUpdate(subarea);
		return "saveOrUpdate";
	}
	
	//条件分页查询
	public String findByPage(){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Subarea.class);
		
		//使用qbc添加查询条件
		if(subarea.getAddresskey()!=null && subarea.getAddresskey().trim().length()>0){
			//添加关键字条件
			detachedCriteria.add(Restrictions.like("addresskey", subarea.getAddresskey()+"%"));	
		}
		if(subarea.getDecidedzone()!=null && subarea.getDecidedzone().getId()!=null && subarea.getDecidedzone().getId().trim().length()>0){
			detachedCriteria.add(Restrictions.eq("decideZone", subarea.getDecidedzone()));
		}
		
		if (subarea.getRegion() != null) {
			// 表关联，QBC解决方案 --- 别名
			detachedCriteria.createAlias("region", "r");
			if (subarea.getRegion().getProvince() != null && subarea.getRegion().getProvince().trim().length() > 0) {
				detachedCriteria.add(Restrictions.like("r.province", "%" + subarea.getRegion().getProvince() + "%"));
			}
			if (subarea.getRegion().getCity() != null && subarea.getRegion().getCity().trim().length() > 0) {
				detachedCriteria.add(Restrictions.like("r.city", "%" + subarea.getRegion().getCity() + "%"));
			}
			if (subarea.getRegion().getDistrict() != null && subarea.getRegion().getDistrict().trim().length() > 0) {
				detachedCriteria.add(Restrictions.like("r.district", "%" + subarea.getRegion().getDistrict() + "%"));
			}
		}		
		
		
		PageRequestBean pageRequestBean=initPageRequestBean(detachedCriteria);
		
		PageResponseBean pageResponseBean=subareaService.findByPage(pageRequestBean);
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		
		return "findByPage";
	}
	

}
