/**
 * 
 */
package com.xcj.common.excel;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xcj.common.entity.UserExcel;

/** 
 * <b>function:</b> 
 * @project base_frame 
 * @package com.xcj.common.excel  
 * @fileName com.xcj.*
 * @createDate May 26, 2014 11:03:30 AM 
 * @author su_jian 
 */
public class TestExcel {
	
	
	public static void main(String[] args) {
		String filePath="c:/学员模版.xls";
		Integer sheetIndex=0;
		PoiExcelHelper helper = getPoiExcelHelper(filePath);
		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex);
		List<UserExcel> list=new ArrayList<UserExcel>();
		for (int i = 2; i < dataList.size(); i++) {
			    ArrayList<String> ss=dataList.get(i);
				UserExcel user=new UserExcel();
				user.setPartnerName(ss.get(0));
				user.setAreaName(ss.get(1));
				user.setAreaContant(ss.get(2));
				user.setAreatel(ss.get(3));
				user.setFoursName(ss.get(4));
				user.setAddress(ss.get(5));
				user.setFoursContant(ss.get(6));
				user.setFoursTel(ss.get(7));
				user.setUseGradeName(ss.get(8));
				user.setUserName(ss.get(9));
				user.setIdCard(ss.get(10));
				user.setTelphone(ss.get(11));
				user.setEmail(ss.get(12));
				list.add(user);
		}
		
		/*for (int i = 0; i < list.size(); i++) {
			UserExcel usr=list.get(i);
			if(i==0){
				
			}else{
				UserExcel user=list.get(i-1);
				if(usr.getPartnerName().equals("")){
					usr.setPartnerName(user.getPartnerName());
				}
				if(usr.getAreaName().equals("")){
					usr.setAreaName(user.getAreaName());
				}
				if(usr.getAreaContant().equals("")){
					usr.setAreaContant(user.getAreaContant());
				}
				if(usr.getAreatel().equals("")){
					usr.setAreatel(user.getAreatel());
				}
				if(usr.getFoursName().equals("")){
					usr.setFoursName(user.getFoursName());
				}
				if(usr.getAddress().equals("")){
					usr.setAddress(user.getAddress());
				}
				if(usr.getFoursContant().equals("")){
					usr.setFoursContant(user.getFoursContant());
				}
				if(usr.getFoursTel().equals("")){
					usr.setFoursTel(user.getFoursTel());
				}
			}
		}*/
		System.out.println(JSON.toJSONString(list,true));
		
		
	}
	
	// 获取Excel处理类
	 private static PoiExcelHelper getPoiExcelHelper(String filePath) {
		PoiExcelHelper helper;
		if(filePath.indexOf(".xlsx")!=-1) {
			 helper = new PoiExcel2k3Helper();//TODO 本来是07现在改为03
		}else {
			helper = new PoiExcel2k3Helper();
		}
		return helper;
	} 
	

}
