package com.test.mvc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.jsp.jdbc.DBUtil;

public class DelOk extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. POST -> 인코딩 처리
		//2. 데이터 가져오기
		//3. DB 작업 -> delete
		//4. JSP 호출하기 + 결과 출력
		
		// 1. POST -> 인코딩 처리
		req.setCharacterEncoding("UTF-8");
		
		// 2. 데이터 가져오기
		String seq = req.getParameter("seq");
		
		// 3. DB 작업 -> delete
		Connection conn = null;
		PreparedStatement stat = null;
		int result = -1; // result 변수가 절대로 가질 수 없는 값.
		
		try {
			
			String sql = "delete from tblAddress where seq=? ";
			
			conn = DBUtil.open();
			stat = conn.prepareStatement(sql);
			
			stat.setString(1, seq);
			
			result = stat.executeUpdate(); // 1(성공), 0(실패)

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 4. JSP 호출하기 + 결과 출력
		req.setAttribute("result", result);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/address/delok.jsp");
		dispatcher.forward(req, resp);
		
	}

}
