package com.Joachim.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Joachim.dao.GradeDao;
import com.Joachim.model.Grade;
import com.Joachim.model.PageBean;
import com.Joachim.util.DbUtil;
import com.Joachim.util.JsonUtil;
import com.Joachim.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GradeListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DbUtil dbUtil=new DbUtil();
	private GradeDao gradeDao=new GradeDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String gradeName=request.getParameter("gradeName");
		if (gradeName==null) {
			gradeName="";
		}
		Grade grade=new Grade();
		grade.setGradeName(gradeName);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(gradeDao.gradeList(conn, pageBean,grade));
			int total=gradeDao.gradeCount(conn,grade);//获取返回记录的总行数
			result.put("rows", jsonArray);//每页的行数
			result.put("total", total);//总行数
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
