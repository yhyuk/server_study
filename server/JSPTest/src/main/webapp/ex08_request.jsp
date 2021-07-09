<%@page import="java.util.Enumeration"%>
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
	<!-- ex08_request.jsp -->
	<div class="container">
		<h1 class="page-header">HTTP 요청 페이지 - 헤더 정보</h1>	
		
		<%-- <%= request.getHeader("User-Agent") %> --%>
		
		<table class="table table-bordered">
			<tr>
				<th>헤더명</th>
				<th>헤더값</th>
			</tr>
			<%
				// 헤더 정보
				// 값 = request.getHeader("헤더명")
				Enumeration<String> e = request.getHeaderNames();
			
				while ( e.hasMoreElements()) {
					String name = e.nextElement(); // 헤더명
			
			%>
			<tr>
				<td><%= name %></td>
				<td><%= request.getHeader(name) %></td>
			</tr>
			<%
				}
			%>
		</table>
		
		<hr />
		
		http://localhost:8090/jsp/ex08_request.jsp
		http://127.0.0.1:8090/jsp/ex08_request.jsp
		IP(127.0.0.1): 자신을 가르키는 예약 IP 주소
		
		<p>서버 도메인명: <%= request.getServerName() %></p>
		<p>서버 포트번호: <%= request.getServerPort() %></p>
		<p>요청 URL: <%= request.getRequestURL() %></p>
		<p>요청 쿼리: <%= request.getQueryString() %></p>
		
		<!-- 브라우저 사용자 컴퓨터 IP 주소 -->
		<p>클라이언트 호스트: <%= request.getRemoteHost() %></p>
		<p>클라이언트 IP: <%= request.getRemoteAddr() %></p>
		
		<p>프로토콜: <%= request.getProtocol() %></p>
		<p>요청방식: <%= request.getMethod() %></p>
		
		<!-- /jsp  : *****중요  -->		
		<p>컨텍스트 경로: <%= request.getContextPath() %></p>
	
	</div>
	
	<script>


	</script>
</body>
</html>