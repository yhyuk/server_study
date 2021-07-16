<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

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
	<!-- index.jsp -->
	<div class="container">
		<h1 class="page-header">웹 보안</h1>	
		
		<c:if test="${ empty id }">
			<button type="button" class="btn btn-default"
				onclick="location.href='/jsp/auth/login.do';">로그인</button>
		</c:if>
		
		<c:if test="${ not empty id }">
			<div class="panel panel-default" style="width: 300px;">
				<div class="panel-heading">
					회원 정보
				</div>
				<div class="panel-body">
					<div>ID: ${ id }</div>
					<div>이름: ${ name }</div>
					<div>등급: ${ lv }</div>
					<div>가입일: ${ regdate }</div>
				</div>
				
				<button type="button" class="btn btn-default"
					onclick="location.href='/jsp/auth/logoutok.do';">로그아웃</button>
			</div>
		</c:if>	
		
		<!--  
			회원 전용 페이지 조작
			1. 인증 받지 못한 사용자에게 안보이게 감춘다.
			2. 항상 보여주되.. 처리를 다르게한다.
		-->
		
		<!-- 로그인을 한사람 -->
		<c:if test="${ not empty id }">
		<button type="button" class="btn btn-default"
				onclick="location.href='/jsp/auth/member.do';">회원 전용 페이지</button>
		</c:if>	
		
		<hr />

		<c:if test="${ empty id }">
		<button type="button" class="btn btn-default"
				onclick="alert('로그인한 사용자만 접근이 가능합니다.')">회원 전용 페이지 1</button>
		</c:if>	

		<c:if test="${ not empty id }">
		<button type="button" class="btn btn-default"
				onclick="location.href='/jsp/auth/member.do';">회원 전용 페이지 2</button>
		</c:if>	
		
		<hr />
		<c:if test="${ not empty id && lv == 3 }">
		<button type="button" class="btn btn-default"
				onclick="location.href='/jsp/auth/admin.do';">관리자 전용 페이지</button>
		</c:if>
		
		
	
	</div>
	
	<script>

	</script>
</body>
</html>