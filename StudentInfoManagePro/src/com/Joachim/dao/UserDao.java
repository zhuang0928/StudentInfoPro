package com.Joachim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Joachim.model.User;


public class UserDao {
	
	/*
	 * ÓÃ»§µÇÂ¼
	 */
	public User login(Connection conn,User user) throws Exception{
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());	
		User resultUser=null;
		ResultSet rs=pstmt.executeQuery();
		while (rs.next()) {
			resultUser=new User();
			resultUser.setUserName(rs.getString("userName"));	
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
		
	}
}
