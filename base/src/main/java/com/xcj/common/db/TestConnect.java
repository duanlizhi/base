package com.xcj.common.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestConnect {

	/**
	 * 数据库最原始的连接数据库方法
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = DataSource.getConnection();
		PreparedStatement stmt = null;
		String sql="";
		ResultSet rs=null;
		List<String> list =new ArrayList<String>();
		try {
			sql=" SELECT 123 FROM DUAL ";
			 stmt =  conn.prepareStatement(sql);
			 rs = stmt.executeQuery();
			 while(rs.next()){
				 list.add(rs.getString(1)==null?"":rs.getString(1));
			 }
		} catch (Exception e) {
			e.printStackTrace();
			DataSource.closeDB(conn,stmt,rs);
		}finally{
			DataSource.closeDB(conn,stmt,rs);
		}
		System.out.println(list.get(0));
	}

}
