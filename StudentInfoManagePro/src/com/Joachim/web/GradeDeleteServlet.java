package com.Joachim.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Joachim.dao.GradeDao;
import com.Joachim.dao.StudentDao;
import com.Joachim.model.Grade;
import com.Joachim.model.PageBean;
import com.Joachim.util.DbUtil;
import com.Joachim.util.JsonUtil;
import com.Joachim.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GradeDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DbUtil dbUtil=new DbUtil();
	private GradeDao gradeDao=new GradeDao();
	private StudentDao studentDao=new StudentDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delIds=request.getParameter("delIds");
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			JSONObject result=new JSONObject();
			String str[]=delIds.split(",");
			for(int i=0;i<str.length;i++){
				boolean f=studentDao.getStudentByGradeId(conn, str[i]);
				if(f){
					result.put("errorIndex", i);
					result.put("errorMsg", "班级下面有学生，不能删除！");
					ResponseUtil.write(response, result);
					return;
				}
			}
			int delNums=gradeDao.gradeDelete(conn, delIds);
		
			if (delNums>0) {
				result.put("success", "true");
				result.put("delNums", delNums);
				conn.commit();
			}else {
				result.put("errorMsg", "删除失败");
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
