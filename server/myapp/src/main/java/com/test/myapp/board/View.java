package com.test.myapp.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 할일
		// 1. 데이터 가져오기(seq)
		// 2. DB 작업 > DAO 위임 > select where seq
		// 3. BoardDTO 반환 > JSP 호출하기 + 전달하기
		
		HttpSession session = req.getSession();
		
		// 1.
		String seq = req.getParameter("seq");
		
		// 2.
		BoardDAO dao = new BoardDAO();
		
		if (session.getAttribute("read") != null & session.getAttribute("read").toString().equals("n")) {
			
			// 2.3 조회수 증가하기
			dao.updateReadCount(seq);
			
			session.setAttribute("read", "y");
			
		}
		
		BoardDTO dto = dao.get(seq);
		
		// 2.5 
		String subject = dto.getSubject();
		String content = dto.getContent();
		
		// 무조건 글 제목과 내용에 들어있는 <script>태그는 비활성화 시키기!!
		subject = subject.replace("<script", "&lt;script").replace("</script>", "&lt;/script&gt;");
		dto.setSubject(subject);
		
		content = content.replace("<script", "&lt;script").replace("</script>", "&lt;/script&gt;");
		dto.setContent(content);

		// 글 내용에 태그 적용 안되게 하기
		if (dto.getTag().equals("n")) {
			// <b> -> &lt;b&gt; -> 꺽새 제거하기
			content = content.replace("<", "&lt;").replace(">", "&gt;");
			dto.setContent(content);
		}
		
		// 글 내용(content)에 개행 문자 처리하기
		content = content.replace("\r\n", "<br>");
		dto.setContent(content);
		
		
		// 3.
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);

	}

}
