<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

	// 말풍선
	$('*[title]').data('placement', 'left');
	$('*[title]').tooltip();
	
	// 사용자 설정 적용 
	let menucolor = 'tomato';
	let membercolor = 'cornflowerblue';
	
	if (getCookie('menucolor') != null && getCookie('menucolor') != "") {
		menucolor = getCookie('menucolor');
	}
	
	if (getCookie('membercolor') != null && getCookie('membercolor') != "") {
		membercolor = getCookie('membercolor');
	}
	
	$('.main-header > section > nav > ul > li').css('color', menucolor);
	$('.main-header > section > .auth > .btn-auth').css('color', membercolor);

</script>