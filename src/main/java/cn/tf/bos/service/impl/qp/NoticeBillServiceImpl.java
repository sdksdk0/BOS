package cn.tf.bos.service.impl.qp;

import java.sql.Timestamp;
import java.util.List;

import cn.tf.bos.domain.bc.Decidedzone;
import cn.tf.bos.domain.bc.Subarea;
import cn.tf.bos.domain.qp.NoticeBill;
import cn.tf.bos.domain.qp.WorkBill;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.qp.NoticeBillService;

public class NoticeBillServiceImpl  extends  BaseService  implements NoticeBillService{

	@Override
	public void save(NoticeBill noticeBill) {
		noticebillDao.save(noticeBill);
		
		//自动分单
		//使用当前取件地址去查下crm系统中的定区编码
		String decidedZoneId=customerService.findDecideZoneIdByCustomerAddress(noticeBill.getPickaddress());
		if(decidedZoneId==null){
			//未查到
			//匹配分区,关键字
			String[]  addressArray=noticeBill.getPickaddress().split("/");
			if(addressArray.length>=2){
				String addresskey=addressArray[1];
				
				List<Subarea>  list=subareaDao.findByNameQuery("subarea.findByAddress",addresskey);
				if(list.size()==1 && list.get(0).getDecidedzone()!=null){
					Decidedzone   decidedzone=list.get(0).getDecidedzone();
					noticeBill.setStaff(decidedzone.getStaff());
					noticeBill.setOrdertype("自动");
					
					
					//写入到工单信息
					WorkBill  workBill=new WorkBill();
					workBill.setNoticeBill(noticeBill);
					workBill.setStaff(decidedzone.getStaff());
					workBill.setType("新");
					workBill.setPickstate("新单");
					workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
					workBill.setAttachbilltimes(0);
					workBill.setRemark(noticeBill.getRemark());
					workbillDao.save(workBill);
				}else{
					//人工调度
					noticeBill.setOrdertype("人工");
				}
			}else{
				//人工调度
				noticeBill.setOrdertype("人工");	
			}
			
		}else{
			//查到了
			Decidedzone   decidedzone=decidedzoneDao.findById(decidedZoneId);
			noticeBill.setStaff(decidedzone.getStaff());
			noticeBill.setOrdertype("自动");
			
			
			
			//写入到工单信息
			WorkBill  workBill=new WorkBill();
			workBill.setNoticeBill(noticeBill);
			workBill.setStaff(decidedzone.getStaff());
			workBill.setType("新");
			workBill.setPickstate("新单");
			workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workBill.setAttachbilltimes(0);
			workBill.setRemark(noticeBill.getRemark());
			workbillDao.save(workBill);
		}
	}

}
