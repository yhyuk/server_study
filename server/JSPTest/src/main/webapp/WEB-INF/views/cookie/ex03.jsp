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
	<!-- ex03.jsp -->
	<div class="container">
		<h1 class="page-header">쿠키 예제</h1>
		
		<form method="POST" action="loginok.do">
		<table class="table table-bordered" style="width: 300px;">
			<tr>
				<th style="width: 100px;">아이디</th>
				<td><input type="text" name="txtid" id="txtid" class="form-control"></td>
			</tr>
			<tr>
				<th>암호</th>
				<td><input type="password" name="txtpw" id="txtpw" class="form-control"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" class="btn btn-default" value="로그인" id="btn">
					<label><input type="checkbox" id="cb"> 아이디 저장하기</label>
				</td>
			</tr>
		</table>
		</form>		
		
	</div>	
	
	<script src="<%= request.getContextPath() %>/asset/js/cookie.js"></script>
	<script>
	
		$('#btn').click(function() {
			
			//쿠키
			//1. 메모리 쿠키
			//	- 만료 시간을 지정하지 않은 경우, 브라우저가 종료되면 쿠키도 같이 소멸된다.
			//2. 하드 쿠키
			//	- 만료 시간을 지정하면 만료 시간까지 쿠키를 보관한다.
			
			//아이디 저장하기
			//cb.checked
			//alert($('#cb').prop('checked'));
			
			if ($('#cb').prop('checked')) {
				//로그인 + 아이디 저장하기O
				setCookie('id', $('#txtid').val(), 100); //100일 동안 저장
				
			} else {
				//로그인 + 아이디 저장하기X
				//만료 시간을 현재 시간보다 과거로 지정 > 쿠키 자동 소멸
				setCookie('id', '', -1); 
			}
			
			//로그인 처리!!!
			this.form.submit(); //폼 전송(에뮬레이터 함수)
			
		});//btn.click
		
		//페이지 로드
		// -> 아이디 저장하기 쿠키가 있으면.. 아이디를 읽어서 텍스트 박스에 세팅하기
		if (getCookie('id') != "") {
			$('#txtid').val(getCookie('id'));
			$('#cb').prop('checked', true);
			$('#txtpw').focus();
		} else {
			$('#txtid').focus();
		}
	
	</script>
</body>
</html>















