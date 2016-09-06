package com.Joachim.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Joachim.dao.StudentDao;
import com.Joachim.util.DbUtil;
import com.Joachim.util.ResponseUtil;

import net.sf.json.JSONObject;

public class StudentDeleteServlet extends HttpServlet {
	
	private DbUtil dbUtil=new DbUtil();
	private StudentDao studentDao=new StudentDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delIds=request.getParameter("delIds");
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			JSONObject result=new JSONObject();
			int delNums=studentDao.studentDelete(conn, delIds);
			if (delNums>0) {
				result.put("success", true);
				result.put("delNums", delNums);
				conn.commit();
			}else {
				result.put("errorMsg", delNums);
				conn.rollback();
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
