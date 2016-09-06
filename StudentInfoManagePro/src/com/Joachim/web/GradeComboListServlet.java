package com.Joachim.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Joachim.dao.GradeDao;
import com.Joachim.util.DbUtil;
import com.Joachim.util.JsonUtil;
import com.Joachim.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GradeComboListServlet extends HttpServlet {

	private DbUtil dbUtil = new DbUtil();
	private GradeDao gradeDao = new GradeDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("gradeName", "«Î—°‘Ò...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(gradeDao.gradeList(conn, null, null)));
			ResponseUtil.write(response, jsonArray);
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
