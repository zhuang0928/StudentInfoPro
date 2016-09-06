package com.Joachim.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static JSONArray formatRsToJsonArray(ResultSet rs) throws SQLException {
		//��ȡ����Ϣ
		ResultSetMetaData md = rs.getMetaData();
		int num=md.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){
			//rs.next()���򣬻�ȡÿ�е���Ϣ
			JSONObject mapOfColvalues=new JSONObject();
			for (int i = 1; i <= num; i++) {
				Object o=rs.getObject(i);//����Ϣ
				if(o instanceof Date){
					mapOfColvalues.put(md.getColumnName(i), DateUtil.formatDate((Date)o, "yyyy-MM-dd"));
				}else{
					mapOfColvalues.put(md.getColumnName(i), rs.getObject(i));					
				}
				//md.getColumnName(i)//����ģ���ȡÿ�е���Ϣ
			}
			array.add(mapOfColvalues);
		}
		return array;
	}

}
