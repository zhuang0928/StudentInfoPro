package com.Joachim.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbUtil {
	String dbUrl="jdbc:mysql://localhost:3306/db_studentinfopro";
	String dbuserName="root";
	String dbPassword="123456";
	String jdbcName="com.mysql.jdbc.Driver";
	
	public  Connection getConn() throws Exception{
		Class.forName(jdbcName);
		Connection conn= DriverManager.getConnection(dbUrl, dbuserName, dbPassword);
		conn.setAutoCommit(false);//取消自动提交
		return conn;	
	}
	
	public Connection closeConn(Connection conn) throws Exception{
		if (conn!=null) {
			conn.close();
		}
		return conn;	
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接成功");
		}
	}
	

}
