package com.Joachim.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseUtil {
	
	public static void write(HttpServletResponse response,Object o) throws IOException{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println(o.toString());//ajax返回的文本
			out.flush();
			out.close();
			
	}

}
