package com.Joachim.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.Joachim.dao.UserDao;
import com.Joachim.model.User;
import com.Joachim.util.DbUtil;
import com.Joachim.util.StringUtil;

import sun.awt.datatransfer.DataTransferer.ReencodingInputStream;

public class LoginServlet extends HttpServlet {
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		String input = request.getParameter("input");
		/*
		 * HttpSession session1 = request.getSession(); 
		 * String code = (String)session1.getAttribute("code");
		 */
		String code = (String) request.getSession().getAttribute("code");// 这句话相当于把上面两句整合了
		//System.out.println(input);
		//System.out.println(code);
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			request.setAttribute("error", "姓名或密码为空");
			// 服务器跳转
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		Connection conn = null;
		User user = new User(userName, password);
		try {
			conn = dbUtil.getConn();
			User resultUser = userDao.login(conn, user);
			if (!input.equals(code)) {
				request.setAttribute("error", "验证码错误");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else if (resultUser == null) {
				request.setAttribute("error", "姓名或密码错误");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("resultUser", resultUser);
				response.sendRedirect("main.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					dbUtil.closeConn(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
