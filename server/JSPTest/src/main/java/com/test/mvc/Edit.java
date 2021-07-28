package com.test.mvc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.jsp.jdbc.DBUtil;

public class Edit extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기(수정할 번호)
		//2. DB 작업 -> select(수정하기 전 데이터)
		//3. ResultSet -> HashMap 옮겨 담기
		//4. JSP 호출하기 + HashMap 넘기기
		
		String seq = req.getParameter("seq");
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		try {
			
			conn = DBUtil.open();	
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress where seq = " + seq;
			
			rs = stat.executeQuery(sql);
			
			while ( rs.next() ) {

				// 레코드 1줄 접근 -> HashMap 1개
				HashMap<String, String> map = new HashMap<String, String>();
				
				//******
				map.put("seq", rs.getString("seq"));
				map.put("name", rs.getString("name"));
				map.put("age", rs.getString("age"));
				map.put("gender", rs.getString("gender"));
				map.put("address", rs.getString("address"));
				
				
				list.add(map);
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/address/edit.jsp");
		dispatcher.forward(req, resp);
		
	}

}
