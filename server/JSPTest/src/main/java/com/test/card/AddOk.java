package com.test.card;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/card/addok.do")
public class AddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 인코딩
		// 2. 데이터 가져오기
		// 3. DB 작업 위임 
		//	3-1 DAO 객체 생성
		//	3-2 DTO 객체 생성 + 데이터 추가
		// 	3-3 업무 위임(insert)
		// 4. 결과 + JSP 호출하기
		//	4-1 결과를 서블릿이 직접 처리
		
		// 1.
		req.setCharacterEncoding("UTF-8");
		
		// 2.
		String korname = req.getParameter("korname");
		String engname = req.getParameter("engname");
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		String company = req.getParameter("company");
		String position = req.getParameter("position");
		
		// 3.
		CardDAO dao = new CardDAO();
		
		CardDTO dto = new CardDTO();
		dto.setKorname(korname);
		dto.setEngname(engname);
		dto.setTel(tel);
		dto.setEmail(email);
		dto.setCompany(company);
		dto.setPosition(position);
		
		int result = dao.add(dto); // 1(성공), 0(실패)
		
		// 4.
		if ( result == 1 ) {
			resp.sendRedirect("/jsp/card/list.do");
		} else {
			resp.sendRedirect("/jsp/card/add.do");
		}
		

	}

}
