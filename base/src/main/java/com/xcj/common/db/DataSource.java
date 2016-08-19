package com.xcj.common.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**																																																																																																																																																																																																																																																														
 * 数据库连接池控制�?																																																																																																																																																																																																																																																														
 * @author  																																																																																																																																																																																																																																																														
 *																																																																																																																																																																																																																																																														
 */
public class DataSource {

	private DataSource() {

	}

	public static Connection getConnection() {
		ConnectPool cp = ConnectPool.getInstance();
		Connection conn = cp.getConnection(cp.getName(), cp.getName()
				+ "caller");

		return conn;
	}

	public static boolean closeDB(Connection conn) {

		return closeDB(conn, null, null);
	}

	public static boolean closeDB(Connection conn, Statement pamStatement,
			ResultSet rs) {
		boolean flag = true;
		try {

			if (rs != null)
				rs.close();
			if (pamStatement != null)
				pamStatement.close();

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			ConnectPool.getInstance().freeConnection(conn);

		}
		return flag;
	}

	public static void releaseDB() {
		ConnectPool.getInstance().release();
	}

	public static void main(String[] arg) throws SQLException {
		Connection conn = DataSource.getConnection();
		System.out.println(conn.toString());
		System.out.println(conn.getAutoCommit());
		DataSource.closeDB(conn, null, null);
	}
}