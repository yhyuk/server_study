package com.test.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/admin.do")
public class Admin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 인증 받지 못한 사용자는 차단하기.
		HttpSession session = req.getSession();
		
		if ( session.getAttribute("id") == null ) {
			// 아이디가 session에 없는사람 == 로그인 안한 사람
			resp.sendRedirect("/jsp/auth/index.do");
			
			return;
		}
		
		
		// 로그인을 했더라도, 관리자가 아닌 인증 사용자를 차단하기.
		if ( !session.getAttribute("lv").toString().equals("3") ) {
			// lv:1,2	- 일반회원
			// lv:3 	- 관리자
			
			resp.sendRedirect("/jsp/auth/index.do");
			return;
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/auth/admin.jsp");
		dispatcher.forward(req, resp);

	}

}
