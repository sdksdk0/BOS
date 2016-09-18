package cn.tf.bos.web.action.bc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.tf.bos.domain.bc.Region;
import cn.tf.bos.domain.bc.Subarea;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.utils.FileUtils;
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
	
	public String delete(){
		String[] ids=subarea.getId().split(", "); 
		subareaService.delete(ids);	
		return "delete";
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
		
		// 将查询结果 保存到Session
		ServletActionContext.getRequest().getSession().setAttribute("pageResponseBean", pageResponseBean);
		return "findByPage";
	}
	
	
	public String exportFile() throws IOException{
		// 对文件名进行编码
		String downloadFileName = "分区数据.xls";
		// 获得用户使用浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("user-agent");

		// 对下载文件名编码
		downloadFileName = FileUtils.encodeDownloadFilename(downloadFileName, agent);
		// 将结果放入值栈
		ActionContext.getContext().put("downloadFileName", downloadFileName);

		return "exportFile";
	}
	
	
	//文件下载流
	public InputStream  getInputStream() throws IOException{
		PageResponseBean pageResponseBean=(PageResponseBean) ServletActionContext.getRequest().getSession().getAttribute("pageResponseBean");
		List<Subarea>  subareas=pageResponseBean.getRows();
		
		HSSFWorkbook  hssfWorkbook=new HSSFWorkbook();
		
		HSSFSheet sheet=hssfWorkbook.createSheet("分区数据");
		HSSFRow  headRow=sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("关键字");
		headRow.createCell(2).setCellValue("起始号");
		headRow.createCell(3).setCellValue("结束号");
		headRow.createCell(4).setCellValue("是否区分单双号号");
		headRow.createCell(5).setCellValue("位置信息");

		// 向excel写数据
		for (Subarea subarea : subareas) {
			// 每个分区一行
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getAddresskey());
			dataRow.createCell(2).setCellValue(subarea.getStartnum());
			dataRow.createCell(3).setCellValue(subarea.getEndnum());
			dataRow.createCell(4).setCellValue(subarea.getSingle());
			dataRow.createCell(5).setCellValue(subarea.getPosition());
		}
		
		// 将数据缓存到字节数组
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		hssfWorkbook.write(arrayOutputStream);
		arrayOutputStream.close();
		byte[] data = arrayOutputStream.toByteArray();

		// 再通过字节数组输入流读取数据
		return new ByteArrayInputStream(data);

	}
	
	
	//查询所有未关联定区的分区列表
	public String findnoassoriations(){
		List<Subarea>  subareas=subareaService.findnoassoriations();
		
		ActionContext.getContext().put("subareas", subareas);
		return "findnoassoriations";
	}
	
	//通过id查找
	public String findById(){
		
		String id=subarea.getDecidedzone().getId();
		List<Subarea>   subareas=subareaService.findById(id);
		
		ActionContext.getContext().put("subareas", subareas);
		
		return "findById";
	}
	
	
	
	

}
