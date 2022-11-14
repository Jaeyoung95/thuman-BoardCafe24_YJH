package com.company.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnection {
	private static final String ORACLE_USER="system";
	private static final String ORACLE_PWD="1234";
	private static final String ORACLE_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ORACLE_DRIVER="oracle.jdbc.OracleDriver";
	
	private static final String MYSQL_CAFE24_USER="thuman0503";
	private static final String MYSQL_CAFE24_PWD="human!123";
	private static final String MYSQL_CAFE24_URL="jdbc:mysql://umj7-023.cafe24.com/thuman0503";
	// war배포 후 게시 직전에 jdbc:mysql://localhost/thuman0503 로 변경.
	private static final String MYSQL_CAFE24_DRIVER="com.mysql.jdbc.Driver";
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn;
		Class.forName(MYSQL_CAFE24_DRIVER);
		
		conn=DriverManager.getConnection(MYSQL_CAFE24_URL, MYSQL_CAFE24_USER, MYSQL_CAFE24_PWD);
		
		return conn;
	}
	public static void close(ResultSet rs,PreparedStatement stmt,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement stmt,Connection conn) {
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
