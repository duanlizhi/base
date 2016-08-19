package com.xcj.common.top;

import java.util.ArrayList;
import java.util.List;


public class MeauParam {
	public static final String Index="a,首页,'',0;";  
	public static final String SYS="b,系统管理  ,'',0;";  
	public static final String DOMAIN="c,域管理,/admin/domain/domainList,b;";  
	public static final String TAG="d,标签管理,/admin/tag/tagList,b;";  
	public static final String ACCOUNT="e,账户管理,/admin/admin/adminList,b;";  
	public static final String COURSE="f,课题卷,'',0;";  
	public static final String COURSELIB="g,课程库,/admin/course/courseWareList,f;";  
	public static final String QUESTIONLIB="h,题库,/admin/questionLibaray/questionLibarayList,f;";  
	public static final String DATA="i,数据统计 ,'',0;";  
	public static final String KNOWLEDGE="j,知识体系,/admin/knowledgePoint/knowledgePointList,0;";  
	public static final String ABOUTSYS="k,关于系统 ,'',0;";
	public static final String TESTS="l,测评库,/admin/tests/index,f;";
	public static final String WX="w,微信,/admin/wx/wxactreglist,0;";
	public static List<Meau> getMeauList(){
		//拼接常量
		StringBuffer sb=new StringBuffer();
		sb.append(Index);
		sb.append(SYS);
		sb.append(DOMAIN);
		sb.append(TAG);
		sb.append(ACCOUNT);
		sb.append(COURSE);
		sb.append(COURSELIB);
		sb.append(QUESTIONLIB);
		sb.append(KNOWLEDGE);
		sb.append(ABOUTSYS);
		sb.append(TESTS);
		sb.append(WX);
		String str=sb.toString().substring(0, sb.toString().length()-1);
		String[] split = str.split(";");
		//一级菜单
		List<Meau> list=new ArrayList<Meau>();
		for (int i = 0; i < split.length; i++) {
			if(split[i].split(",")[3].equals("0")){
				Meau meau= new Meau();
				meau.setId(split[i].split(",")[0]);
				meau.setName(split[i].split(",")[1]);
				meau.setUrl(split[i].split(",")[2]);
				meau.setParentId(split[i].split(",")[3]);
				list.add(meau);
			}
			//二级菜单
			for (Meau meau : list) {
				List<Meau> list2 = meau.getList();
				if(meau.getId().equals(split[i].split(",")[3])){
					Meau meau2= new Meau();
					meau2.setId(split[i].split(",")[0]);
					meau2.setName(split[i].split(",")[1]);
					meau2.setUrl(split[i].split(",")[2]);
					meau2.setParentId(split[i].split(",")[3]);
					list2.add(meau2);
				}
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		List<Meau> meauList = getMeauList();
		for (Meau meau : meauList) {
			System.out.println(meau.getName());
			List<Meau> list = meau.getList();
			for (Meau meau2 : list) {
				System.out.println("++++"+meau2.getName());
			}
		}
	}
}
