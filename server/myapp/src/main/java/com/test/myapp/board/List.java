package com.test.myapp.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/list.do")
public class List extends HttpServlet {
	
	/**
	 * GET or POST
	 * - 페이지를 요청할 때 어느때 GET 요청? POST 요청?
	 * 	1. GET
	 * 		- 브라우저 주소창을 URL을 입력해서 요청할 때
	 * 		- <a>태그로 이동시
	 * 		- location.href 이동 시
	 * 		- resp.sendRedirect() 이동 시
	 * 		- <form method="GET">
	 * 	2. POST
	 * 		- POST는 form이 유일한 방법
	 * 		- <form method="POST">
	 *		- 폼태그의 POST가 아닌 이상, 전부 GET방식이다.
	 * 
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPostGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPostGet(req, resp);

	}

	private void doPostGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// list.do
		// 1. 목록보기 (게시판의 시작 페이지 역할)
		// 	- select ..
		// 2. 검색 결과보기(검색 버튼 눌러서 호출)
		//	- select where ..
		
		String column = req.getParameter("column");
		String search = req.getParameter("search");
		String isSearch = "n";
		
		// System.out.println("column: " + column);
		// System.out.println("search: " + search);
		
		if ( column != null && search != null 
				&& !column.equals("") 
				&& !search.equals(""))  {
			isSearch = "y";
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("column", column);
		map.put("search", search);
		map.put("isSearch", isSearch);
		
		
		// 할일
		// 1. DB 작업 > DAO 위임 > select
		// 2. ArrayList<BoardDTO> 반환하기
		// 3. JSP 호출하기 (2번 전달)
		
		// 1.
		BoardDAO dao = new BoardDAO();
		
		// 2.
		ArrayList<BoardDTO> list = dao.list(map);

		// 날짜 자르는 업무
		// 1. SQL -> 나중에 쓸꺼임.. -> 처음부터 짜르면 뒤에도 안되기 때문.. 선택 X
		// 2. DAO -> DAO에서는 데이터 가공은 안한다.(원본만 구현), 순수 데이터 입출력만 전달 -> 선택 X
		// 3. Servlet -> 데이터 가공 및 조작 -> 선택 O
		// 4. JSP -> 화면만 구현 -> 선택 X
		
		// 2.5
		for ( BoardDTO dto : list ) {
			
			// 날짜 > 시,분초 제거
			String regdate = dto.getRegdate();
			regdate = regdate.substring(0, 10);
			dto.setRegdate(regdate);
			
			String subject = dto.getSubject();
			
			// 무조건 글 제목과 내용에 들어있는 <script>태그는 비활성화 시키기!!
			subject = subject.replace("<script", "&lt;script").replace("</script>", "&lt;/script&gt;");
			dto.setSubject(subject);
			
			// 제목이 길면 > 자르기
			if ( subject.length() > 40 ) {
				subject = subject.substring(0, 30) + "...";
				dto.setSubject(subject);
			}
			
			// 제목으로 검색중이면.. 검색어를 강조표시하기
			// "게시판입니다." > 
			// 검색어(게시판) > 
			// "<span style="color: tomato; background-color: yellow;">게시판</sapn>입니다."
			
			if ( isSearch.equals("y") && column.equals("subject")) {
				subject = subject.replace(search, "<span style='color: tomato; background-color: yellow;'>" 
														+ search + "</span>");
			
				dto.setSubject(subject);
			}

		}

		// 새로고침에 의한 조회수 증가 방지 티켓
		HttpSession session = req.getSession();
		
		session.setAttribute("read", "n");
		
		
		// 3.
		req.setAttribute("list", list);
		req.setAttribute("map", map); // *******
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);
	}

}
