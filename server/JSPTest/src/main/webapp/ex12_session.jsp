<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%


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
	<!-- ex12_session.jsp -->
	<div class="container">
		<h1 class="page-header">Session vs Application</h1>
		
		<%
			// session
			// 	- 페이지 이동 시 전달 과정없이 데이터를 전달할 수 있다.
			// 	- 데이터 전달용으로 많이 사용한다.
			
			// application
			// 	- 페이지 이동 시 전달 과정없이 데이터를 전달할 수 있다.
			//	- 데이터 전달용으로 많이 사용한다.
			
			
			// 한장의 JSP 페이지내에서만 사용한다면 아래의 5개의 변수는 차이가 없다.
			int num = 123;
			pageContext.setAttribute("num1", 10);
			request.setAttribute("num2", 20);
			session.setAttribute("num3", 30);
			application.setAttribute("num4", 40);
		%>	
		
		<div>num1: <%= pageContext.getAttribute("num1") %></div>
		<div>num2: <%= request.getAttribute("num2") %></div>
		<div>num3: <%= session.getAttribute("num3") %></div>
		<div>num4: <%= application.getAttribute("num4") %></div>
		
		<hr />
		
		<a href="ex12_session_1.jsp">다른 페이지로 이동하기</a>
	
	</div>
	
	<script>


	</script>
</body>
</html>