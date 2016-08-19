package com.xcj.test.lt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import com.xcj.common.db.DataSource;

public class LL {

	public static void main(String[] args) throws Exception {
		//String filePath = "C:\\Users\\sujian\\Desktop\\晨风机器人词库整合\\晨风机器人词库整合\\";
		String filePath="C:\\xx";
		File file = new File(filePath);
		String[] filelist = file.list();
		for (int i = 0; i < filelist.length; i++) {
			File readfile = new File(filePath + "\\" + filelist[i]);
			if (!readfile.isDirectory()) {
				System.out.println("name=" + readfile.getName());
				if (readfile.isFile() && readfile.exists()) { //判断文件是否存在
					InputStreamReader read = new InputStreamReader(
							new FileInputStream(readfile), "UTF-8");//考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					String str="";
					while ((lineTxt = bufferedReader.readLine()) != null) {
						//System.out.println(lineTxt);
						str=str+lineTxt.replaceAll("#", "").replaceAll("&", "");
						if(lineTxt.equals("")){
							//System.out.println("---------------------分隔 -------------");
							str=str+"#";
						}else{
							str=str+"&";
							//System.out.println("$$");
						}
					}
					//System.out.println(str);
					String szzz[]=str.split("#");
					Connection conn = DataSource.getConnection();
					 conn.setAutoCommit(false); // 设置手动提交
					Statement  stmt = null;
					stmt =  conn.createStatement();
					for (int j = 0; j < szzz.length; j++) {
					   String []fs =szzz[j].toString().split("&");
						if(!fs[0].trim().equals("")){
							String s1=fs[0];
							String s2=fs[1];
							String sql="INSERT INTO keyvalue(autokey,autovalue) values('"+s1+"','"+s2+"')";
							stmt.addBatch(sql);
							if(j%10000==0){
								System.out.println(j);
								stmt.executeBatch(); 
								  conn.commit();  // 提交
								stmt.clearBatch();
							}
						}
					}
					stmt.executeBatch();
					 conn.commit();  // 提交
					read.close();
					System.out.println("jieshu结束");
				}
			}
		}

	}
}
