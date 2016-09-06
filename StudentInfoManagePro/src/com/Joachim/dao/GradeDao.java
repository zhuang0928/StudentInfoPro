package com.Joachim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Joachim.model.Grade;
import com.Joachim.model.PageBean;
import com.Joachim.util.StringUtil;

public class GradeDao {
	// 班级列表以及页码
	public ResultSet gradeList(Connection conn, PageBean pageBean,Grade grade) throws SQLException {
		StringBuffer sb = new StringBuffer("select * from t_grade");
		if (grade!=null&&StringUtil.isNotEmpty(grade.getGradeName())) {
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");	
		}
		if (pageBean != null) {
			/* Select * Form table Limit start,size; */
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	public int gradeCount(Connection conn,Grade grade) throws SQLException {
		// total是记录行数的别名
		//String sql = "select count(*) as total from t_grade";
		StringBuffer sb=new StringBuffer("select count(*) as total from t_grade");
		if (StringUtil.isNotEmpty(grade.getGradeName())) {
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");	
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	/*
	 * delete from tableName where field in (1,3,5);
	 * 批量删除，使用in;
	 */
	public int gradeDelete(Connection conn,String delIds) throws SQLException{
		String sql="delete from t_grade where id in("+delIds+")";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		return pstmt.executeUpdate();
		
	}
	
	/*
	 * 信息修改
	 */
	public int gradeModify(Connection conn,Grade grade)  throws SQLException{
		String sql="update t_grade set gradeName=?,gradeDesc=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		pstmt.setInt(3, grade.getId());
		return pstmt.executeUpdate();
		
	}
	
	/*
	 * 信息添加
	 */
	public int gradeAdd(Connection conn,Grade grade) throws SQLException{
		String sql="insert into t_grade values(null,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		return pstmt.executeUpdate();
		
	}
	

}
