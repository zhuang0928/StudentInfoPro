package com.Joachim.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Joachim.dao.GradeDao;
import com.Joachim.model.Grade;
import com.Joachim.util.DbUtil;
import com.Joachim.util.ResponseUtil;
import com.Joachim.util.StringUtil;

import net.sf.json.JSONObject;

public class GradeSaveServlet extends HttpServlet {

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
			request.setCharacterEncoding("utf-8");
			String gradeName=request.getParameter("gradeName");
			String gradeDesc=request.getParameter("gradeDesc");
			String id=request.getParameter("id");
			Grade grade=new Grade(gradeName,gradeDesc);
			if (StringUtil.isNotEmpty(id)) {
				grade.setId(Integer.parseInt(id));
			}
			Connection conn=null;
			try {
				conn=dbUtil.getConn();
				int saveNums=0;
				JSONObject result=new JSONObject();
				if (StringUtil.isNotEmpty(id)) {
					saveNums=gradeDao.gradeModify(conn, grade);
				}else{
					saveNums=gradeDao.gradeAdd(conn, grade);
				}
				
				if (saveNums>0) {
					result.put("success", "true");
					conn.commit();
				}else {
					result.put("success", "true");
					result.put("errorMsg", "±£¥Ê ß∞‹");
					conn.rollback();
				}
				ResponseUtil.write(response, result);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
					try {
						dbUtil.closeConn(conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
	}
			
}
