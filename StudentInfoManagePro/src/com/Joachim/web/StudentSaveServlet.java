package com.Joachim.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Joachim.dao.StudentDao;
import com.Joachim.model.Student;
import com.Joachim.util.DateUtil;
import com.Joachim.util.DbUtil;
import com.Joachim.util.ResponseUtil;
import com.Joachim.util.StringUtil;

import net.sf.json.JSONObject;

public class StudentSaveServlet extends HttpServlet {

	private DbUtil dbUtil = new DbUtil();
	private StudentDao studentDao = new StudentDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String gradeId = request.getParameter("gradeId");
		String email = request.getParameter("email");
		String stuDesc = request.getParameter("stuDesc");
		String stuId = request.getParameter("stuId");

		Student student = null;

		try {
			student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),
					Integer.parseInt(gradeId), email, stuDesc);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtil.isNotEmpty(stuId)) {
			student.setStuId(Integer.parseInt(stuId));
		}
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			if (StringUtil.isNotEmpty(stuId)) {
				saveNums = studentDao.studentModify(conn, student);
			} else {
				saveNums = studentDao.studentAdd(conn, student);
			}
			if (saveNums > 0) {
				result.put("success", "true");
				conn.commit();
			} else {
				result.put("success", "true");
				result.put("errorMsg", "±£¥Ê ß∞‹");
				conn.rollback();
			}
			ResponseUtil.write(response, result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}