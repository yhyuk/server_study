<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%

	// 넘어온 데이터 가져오기
	// - request 객체 담당
	// - request.getParameter("name") -> 1개의 컨트롤을 얻는다.
	// - 모든 데이터는 문자열로 전송 된다. (*******************) -> 자료형이 존재하지 않음
	
	
	String data = request.getParameter("data"); // 문자열
	String num = request.getParameter("num"); // 숫자
	
	
	// 텍스트 박스
	String txt1 = request.getParameter("txt1");
	
	// 암호
	String txt2 = request.getParameter("txt2");
	
	// 멀티 텍스트 박스
	String txt3 = request.getParameter("txt3");
	// 아래 html에서 작성하는것보다, 여기에 작성하는것이 가독성이 더 좋다.
	txt3 = txt3.replace("\r\n", "<br>"); 
	
	/*  
		체크 박스
	 	1. value 미지정
		 	- 체크O -> on 
		 	- 체크X -> null
	
	 	2. value 지정
	 		- 체크O -> value
	 		- 체크X -> null
	 		
	 	체크박스는 value를 지정을 해야한다.
	 		
	*/
	String cb1 = request.getParameter("cb1");
	String cb2 = request.getParameter("cb2");

	String cb3 = request.getParameter("cb3");
	String cb4 = request.getParameter("cb4");
	
	// 체크박스의 value값이 여러개 라면? 일일이 하나씩 할 수 없다... 밑에 ㄱㄱ
	String cb5 = request.getParameter("cb5");
	String cb6 = request.getParameter("cb6");
	String cb7 = request.getParameter("cb7");
	String cb8 = request.getParameter("cb8");
	String cb9 = request.getParameter("cb9");
	
	
	//******
	// String cb = request.getParameter("cb"); 
	// -> 리턴값이 1개라서 맨 처음 나온것만 반환됨
	
	// 여러개값이 있을 때 getParameterValues 메소드의 반환값 배열 사용
	String[] cb = request.getParameterValues("cb");
	String[] txt = request.getParameterValues("txt");
	
	
	// 라디오버튼 -> 100개(여러개)가 있어도 딱 1개만 전송!!
	String rb = request.getParameter("rb");
	
	
	// 셀렉트 박스
	String sel1 = request.getParameter("sel1");
	
	String[] sel2 = request.getParameterValues("sel2");
	
	// 히든 태그
	String id = request.getParameter("id");
	
	String date1 = request.getParameter("date1");
	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<%@ include file="/inc/asset.jsp" %>

<style>

</style>

</head>
<body>
	<!-- ex06_ok.jsp -->
	<div class="container">
		<h1 class="page-header">결과</h1>
		
		<div>data: <%= data %></div>	
		<div>num: <%= num %></div>	
		
		<hr />
		
		<div>txt1: <%= txt1 %></div>	
		<div>txt2: <%= txt2 %></div>	
		<div>txt3: <%= txt3 %></div>
		
		<hr />
			
		<div>cb1: <%= cb1 %></div>	
		<div>cb2: <%= cb2 %></div>	

		<div>cb3: <%= cb3 %></div>	
		<div>cb4: <%= cb4 %></div>	
		
		<hr />
		
		<div>cb5: <%= cb5 %></div>	
		<div>cb6: <%= cb6 %></div>	
		<div>cb7: <%= cb7 %></div>	
		<div>cb8: <%= cb8 %></div>	
		<div>cb9: <%= cb9 %></div>	
	
		<hr />
		
		<div>cb: <%= cb %></div>	
		
		<% 
			if (cb != null) {
				for (String c : cb) { 
		%>
			<div>c: <%= c %></div>
		<% 
				}
			}
		%>
		
		<hr />
		
		<% for (String t : txt) { %>
			<div>t: <%= t %></div>
		<% } %>
		
		<hr />
		
		<div>rb: <%= rb %></div>	
		
		<hr />
		
		<div style="background-color: <%= sel1 %>;">sel1: <%= sel1 %></div>	
		
		<% for (String s : sel2) { %>
			<div>s: <%= s %></div>
		<% } %>
		
		<hr />
		
		<div>id: <%= id %></div>
		<div>date1: <%= date1 %></div>
		
	
	</div>
	
	<script>
	
	</script>
</body>
</html>