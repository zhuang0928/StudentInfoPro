package com.Joachim.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Joachim.dao.StudentDao;
import com.Joachim.model.PageBean;
import com.Joachim.model.Student;
import com.Joachim.util.DbUtil;
import com.Joachim.util.JsonUtil;
import com.Joachim.util.ResponseUtil;
import com.Joachim.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentListServlet extends HttpServlet {

	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String stuNo=request.getParameter("stuNo");
		String stuName=request.getParameter("stuName");
		String sex=request.getParameter("sex");
		String bbirthday=request.getParameter("bbirthday");
		String ebirthday=request.getParameter("ebirthday");
		String gradeId=request.getParameter("gradeId");
		
		Student student=new Student();
		if(stuNo!=null){
			student.setStuNo(stuNo);
			student.setStuName(stuName);
			student.setSex(sex);
			if(StringUtil.isNotEmpty(gradeId)){
				student.setGradeId(Integer.parseInt(gradeId));
			}
		}
		
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(studentDao.studentList(conn, pageBean,student,bbirthday,ebirthday));
			int total=studentDao.studentCount(conn,student,bbirthday,ebirthday);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}