package com.test.mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.jsp.jdbc.DBUtil;

public class AddOk extends HttpServlet {
	
	// POST -> doPost()
	// GET 	-> doGet()
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 할일
		// 1. POST -> 인코딩 처리
		// 2. 데이터 가져오기
		// 3. DB 작업 -> insert
		// 4. JSP 호출하기 + 결과 출력
		
		// 1. POST -> 인코딩 처리
		req.setCharacterEncoding("UTF-8");
		
		// 2. 데이터 가져오기
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		
		// 3. DB 작업 -> insert
		Connection conn = null;
		PreparedStatement stat = null;
		int result = -1; // result 변수가 절대로 가질 수 없는 값.
		
		try {
			
			String sql = "insert into tblAddress(seq, name, age, gender, address) "
					+ "values(seqAddress.nextval, ?,?,?,?)";
			
			conn = DBUtil.open();
			stat = conn.prepareStatement(sql);
			
			stat.setString(1, name);
			stat.setString(2, age);
			stat.setString(3, gender);
			stat.setString(4, address);
			
			result = stat.executeUpdate(); // 1(성공), 0(실패)

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 출력할 내용이 거의 없는 경우엔.. ****
		// JSP와 짝을 맺지 않고, Servlet 혼자 일을 마무리하는 경우도 종종 있다.
		
		/*
		if ( result == 1 ) {
			resp.sendRedirect("/jsp/list.do");
		} else {
			// resp.sendRedirect("/jsp/add.do");
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("history.back()");
			writer.print("</script>");
			writer.close();
		}
		*/
		
		// 4. JSP 호출하기 + 결과 출력
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/address/addok.jsp");
		dispatcher.forward(req, resp);
		
	}

}
