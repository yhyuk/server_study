<%@page import="com.test.jsp.User"%>
<%@page import="java.util.HashMap"%>
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
	<!-- ex15_el.jsp -->
	<div class="container">
		<h1 class="page-header">EL</h1>	
		
		<%--  
			
			액션 태그 + EL
			-> 목적: JSP 페이지에서 자바 구문을 사용하지 말자!!
			
			EL, Expreesion Language
			- 표현식 언어
			- 내장 객체(pageContext, request, session, application)에 들어있는 데이터를 HTML 소스에 출력하는 언어
			- ${}
		
		--%>
		
		<%
			// 평범한 지역 변수
			int a = 10;
		
			// 내장 객체 변수
			pageContext.setAttribute("b", 20);
			request.setAttribute("c", 30);
			session.setAttribute("d", 40);
			application.setAttribute("e", 50);
		
		%>
		
		<h2>표현식</h2>
		<div>평범한 지역 변수 a: <%= a %></div> <!-- 자바 표현식을 사용 -->
		<div>내장 객체 변수 b: <%= pageContext.getAttribute("b") %></div>
		
		<hr />
		
		<h2>EL</h2>
		<div>a: ${a}</div>
		
		<!-- 아래 두 코드는 동일하다. -->
		<!-- pageContext.getAttribute("b") == b -->
		<%-- <div>b: ${pageContext.getAttribute("b")}</div> --%>
		<div>b: ${b}</div>
		<!-- 이렇게 짧게 쓰기위해 EL은 태어났다!! 나머지 내장 객체도 확인해보자! -->
		<div>c: ${c}</div>
		<div>d: ${d}</div>
		<div>e: ${e}</div>
		
		<hr />
		
		<%
			int num1 = 10;
			pageContext.setAttribute("num2", 20);
		%>
		<h2>EL로 할 수 있는 일들(연산)</h2>
		<div>num1 + 10 = <%= num1 + 10 %></div>
		<div>num2 + 10 = <%= (int)pageContext.getAttribute("num2") + 10 %></div>
		<div>num2 + 10 = ${num2} + 10</div>
		
		<hr />
		
		<div>num2 + 10 = ${num2 + 10}</div>
		<div>num2 - 10 = ${num2 - 10}</div>
		<div>num2 * 10 = ${num2 * 10}</div>
		<div>num2 / 10 = ${num2 / 10}</div>
		<div>num2 % 10 = ${num2 % 10}</div>
		<div>num2 mod 10 = ${num2 mod 10}</div>
		
		<hr />
		
		<div>num2 > 0 : ${num2 > 0}</div>
		<div>num2 >= 0 : ${num2 >= 0}</div>
		<div>num2 < 0 : ${num2 < 0}</div>
		<div>num2 <= 0 : ${num2 <= 0}</div>
		<div>num2 == 0 : ${num2 == 0}</div>
		<div>num2 != 0 : ${num2 != 0}</div>
		
		<hr />
		
		<div>${true && true}</div>
		<div>${true && false}</div>
		<div>${true || true}</div>
		<div>${true || false}</div>
		
		<hr />
		
		<div>num2 = ${ num2 > 0 ? "양수" : "음수" }</div>
		<div>${ "홍길동" == "홍길동"}</div> <!-- 사용(X) -->
		<div>${ "홍길동".equals("홍길동")}</div> <!-- 사용(O) -->
		
		<hr />
		
		<h2>HashMap 출력 지원</h2>
		
		<%
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("name", "홍길동");
			map.put("age", "20");
			map.put("job", "학생");
			map.put("phone-number", "010-1232-1234");
			
			// EL 방식
			pageContext.setAttribute("map", map);
		%>
		<!-- JAVA 방식 -->
		<div>이름: <%= map.get("name") %></div>
		<div>나이: <%= Integer.parseInt(map.get("age")) - 1 %></div>
		<div>직업: <%= map.get("job") %></div>
		
		<br />
		
		<!-- EL 방식 -->
		<div>이름: ${ pageContext.getAttribute("map").get("name") }</div>
		<div>이름: ${ map }</div>
		
		<div>이름: ${ map.get("name") }</div> <!-- 방법1 -->
		<div>이름: ${ map.name }</div> <!-- 방법2 -->
		<div>이름: ${ map["name"] }</div> <!-- 방법3 -->
		
		<div>전화: ${ map.phone-number }</div> <!-- phone-number는 빼기 기호로 인식해서 안됨 -->
		<div>전화: ${ map["phone-number"] }</div> <!-- 이럴땐 []속성 명시자로 ㄱㄱ -->
		
		<div>나이: ${ map.age - 1 }</div>	
		
		
		
		
		<h2>Java Object 출력 지원</h2>
		
		<%
		
			User hong = new User();
		
			hong.setFullname("홍길동");
			hong.setNick("둘리");
			hong.setInfo("크아아앙앙");
			
			pageContext.setAttribute("hong", hong);
		
		%>
		<!-- JAVA 방식 -->
		<%-- <div>이름: <%= hong.getName() %></div> --%>
		<div>벌명: <%= hong.getNick() %></div>
		<div>정보: <%= hong.getInfo() %></div>
		
		<br />
		
		<!-- EL 방식 -->
		<%--  
			${hong.name} 
				- name은 멤버 변수 name이 아니다!!
				- name은 getName() 메소드의 이름 이다!! -> get(X) -> 소문자 -> name
		
			타입 [com.test.jsp.User]에서 프로퍼티 [fullname]을(를) 찾을 수 없습니다.
				-> User 클래스에서 getFullname() 메소드를 찾을 수 없습니다.
		--%>
		<div>이름: ${ hong.fullname }</div>
		<div>벌명: ${ hong.nick }</div>
		<div>정보: ${ hong.info }</div>
		
		<hr />
		
		<h2>empty 연산자</h2>
		<!-- empty: 존재하는지 확인.. 있으면 false 없으면 true -->
		<!-- color는 존재안함..(null) -->
		<div>color = ${ color }</div>		
		<div>color = ${ empty color }</div> <!-- true -->
		<div>hong = ${ empty hong }</div> <!-- false -->

	</div>
	
	<script>
	
		let name = '홍길동';
		let str = `안녕하세요. ${name}입니다.`; // template string
		
		$(`#div`).css('color', 'red');

	</script>
</body>
</html>