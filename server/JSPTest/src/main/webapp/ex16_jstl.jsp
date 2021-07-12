<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- JSTL 설치 (Ctrl+Space 3번째 항목 core) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<!-- ex16_jstl.jsp -->
	<div class="container">
		<h1 class="page-header">JSTL</h1>	
		
		<%--
		
			JSTL 액션 태그
			- <c:XXX> 형식
			- 프로그래밍 기능을 하는 태그
		
		--%>
		
		<h2>변수 선언</h2>
		<%
			int a = 10; // 지역변수
			pageContext.setAttribute("b", 20); // 내장 객체 변수(속성)
		%>
		<c:set var="c" value="30" /><!-- JSTL 변수: pageContext에 추가되는 변수(속성)************* -->
		
		<div>a: <%= a %></div>
		<div>b: <%= pageContext.getAttribute("b") %></div>
		<div>b: ${ b }</div>
		<%-- <div>c: <%= c %></div> --%> <!-- ERROR -->
		<div>c: ${ c }</div> <!-- 출력이 된다. 추측? > JSTL 변수가 내장객체 변수 인가? -->

		<!-- JSTL 변수가 내장 객체 변수인지 확인해보기(4가지) -->
		<div><%= pageContext.getAttribute("c") %></div>
		<div><%= request.getAttribute("c") %></div>
		<div><%= session.getAttribute("c") %></div>
		<div><%= application.getAttribute("c") %></div>
		
		<!-- 확인 결과 pageContext에만 출력 되므로, JSTL 변수는 pageContext에 추가되는 변수(속성)이다. -->		
		
		<%--  
			JSTL: XML 문법을 준수(문법이 엄격하다.) 
			
			올바른 문법 )
			<c:set var="c" value="300"></c:set>
			<c:set var="c" value="300" />
			
			에러 문법 )
			<c:set var="c" value="300">

		--%>
		<h2>변수 수정</h2>
		
		<c:set var="c" value="300"></c:set>
		<div>c: ${ c }</div>
		
		<h2>변수 삭제(웹 요소 삭제)</h2>
		<c:remove var="c"></c:remove>
		<div>c: ${ c }</div> <!-- 아무것도 안보임 (삭제됨) -->
		<div>c: ${ empty c }</div> <!-- 좀더 정확히 확인을 위해 empty함수 사용 (true = 없음) -->
		
		
		
		<h2>조건문</h2>
		
		<%
			int num1 = 10;
		%>
		
		<% if (num1 > 0) { %>
		<div>숫자 <%= num1 %>은 양수 입니다.</div>
		<% } else { %>
		<div>숫자 <%= num1 %>은 양수가 아닙니다.</div>
		<% }  %>
		
		
		
		
		
		<c:set var="num2" value="10" />

		<!-- 중요한 철칙)*** JSTL에서 내장객체 변수를 접근하려면 반드시(****) EL을 사용한다. -->
		
		<c:if test="${ num2  > 0 }">
		<div>숫자 ${ num2 }는 양수 입니다.</div>
		</c:if>
		
		<c:if test="${ num2 <= 0 }">
		<div>숫자 ${ num2 }는 양수가 아닙니다.</div>
		</c:if>
		
		<!-- switch case문 + 다중 if문 -->
		<c:choose>
			<c:when test="${ num2 > 0 }">양수입니다.</c:when>
			<c:when test="${ num2 < 0 }">음수입니다.</c:when>
			<c:otherwise>0입니다.</c:otherwise>
		</c:choose>
		
		<hr />
		
		<h2>반복문(for문 = 일반 for문 + 향상된 for문)</h2>
		
		<!-- java 방식 for문 -->
		<% for (int i=0; i<=10; i++) { %>
		<div>항목 <%= i %></div>
		<% } %>
		
		<%-- 
			jstl 방식 for문
			- var="i"는 c:set과 동일한 pageContext 
			- step에는 양수는 들어가지 못한다. (error)
		--%>
		<c:forEach var="i" begin="0" end="10" step="1">
		<div>item ${ 10 - i }</div>
		</c:forEach>
		
		<hr />
		
		<!-- 향상된 for문 -->
		<%
			String[] color = { "빨강", "주황", "노랑", "초록", "파랑", "남색", "보라색" };
			pageContext.setAttribute("color", color);
		%>
		
		<!-- java 방식 -->
		<ul>
			<% for (String c: color) { %>
			<li><%= c %></li>		
			<% } %>
		</ul>
		
		<!-- jstl 방식 -->
		<ol>
			<c:forEach var="c" items="${ color }">
			<li>${ c }</li>
			</c:forEach>
		</ol>
		
		
	</div>
	
	<script>


	</script>
</body>
</html>