package com.cy.rms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * 封装数据常用操作
 * @author Administrator
 *
 */
public class DbUtil {

	/**
	 * 取得Connection
	 * @return
	 */
	public static Connection getConn() {
		
//		Connection conn = null;
//		try {
//			JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
//			Class.forName(jdbcConfig.getDriverName());
//			conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUserName(), jdbcConfig.getPassword());
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			//new DBCP....
			Context ctx = new InitialContext();
			//通过JNDI查找DataSource
		 	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/rms");
		 	conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationException("系统错误，请联系系统管理员");
		} 
		return conn;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		//System.out.println(DbUtil.getConn());
	}
}
