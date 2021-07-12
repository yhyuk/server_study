<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
	// 파일 업로드 처리
	// 1. 업로드된 파일을 어디에 저장할 지 결정한다.
	//		- webapp > files 폴더
	//		- 반드시 로컬 경로로 표시(드라이브명으로 시작)
	//		- D:\class\server\JSPTest\src\main\webapp\files
	//		- 톰캣은 프로젝트 소스를 직접 실행하지 않고, 복사본(.metadata)을 가지고 실행한다.
	//		- 업로드를 저장할 폴더가 Tomcat 실행하는 작업 폴더를 지정해야 한다.
	
	// "/files" : 가상 경로를 물리 경로로 바꿔준다.
	String path = application.getRealPath("/files");
	// System.out.println(path);
	// D:\class\server\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPTest\files
	
	
	// ----------------------------------------------------------------------
	// 2. 업로드 파일의 최대 크기 지정
	// 		- 최대한 작게
	//		- 바이트 단위
	int size = 1024 * 1024 * 100; // 100MB
	
	// ----------------------------------------------------------------------
	// 3. 변수 선언
	String subject = ""; // 입력 데이터
	String name = "";
	String filename = ""; // 첨부 파일명
	String orgfilename = ""; // 첨부 파일명
	// ----------------------------------------------------------------------
	
	// *** <form enctype="multipart/form-data">으로 선언 되면
	// 기존의 request 객체의 getParameter()가 정상 동작을 하지 않는다. (인코딩 변경으로 인해서..)
	// -> request 객체를 대신할 다른 객체로 사용한다. -> cos.jar > MultipartRequest 사용
	
	// subject = request.getParameter("subject"); // null ( 동작 X ) 
	// ----------------------------------------------------------------------
	
	// 서버가 보낸 한글 데이터를 안깨지도록 인코딩
	request.setCharacterEncoding("UTF-8");
	
	// 파일 업로드 처리 -> 외부 입출력 -> try 필수
	try {
		// request -> MultipartRequest
		// com.oreilly.servlet.MultipartRequest --> import
		// 이 객체를 생성하는 순간 업로드 된 파일은 처리를 마치고 첨부파일 폴더에 저장되어 있다.
		MultipartRequest multi = new MultipartRequest(
									request, // 기존의 request Wrapping 클래스
									path, // 업로드 폴더 지정
									size, // 업로드 크기 지정
									"UTF-8", // 인콬딩 지정
									new DefaultFileRenamePolicy() // 중복된 파일명을 처리 -> 넘버링 카운트
								);
	
		// 데이터 수신하기
		subject = multi.getParameter("subject");
		name = multi.getParameter("name");
		
		// 파일 정보 수신하기(***)
		//	- 첨부 파일 가져오기
		//	- <input type="file" name="attach" />
		// 	- 파일 name을 입력한다.
		// 	- multi.getFilesystemName("attach");
		
		// 실제로 저장된 파일명 (넘버링 O)
		filename = multi.getFilesystemName("attach");
		
		// 사용자가 올린 파일명 (넘버링 X)
		orgfilename = multi.getOriginalFileName("attach");
		
	} catch (Exception e) {
		System.out.println(e);
	}
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
	<!-- ex17_file_ok.jsp -->
	<div class="container">
		<h1 class="page-header">결과 페이지</h1>
		
		<div>제목: <%= subject %></div>
		<div>이름: <%= name %></div>
		<div>첨부파일명(실제): <%= filename %></div>
		<div>첨부파일명(원본): <%= orgfilename %></div>
		
		
		<h2>다운로드</h2>
		
		<!-- 
			- 브라우저는 링크 대상이 자신이 열 수 있는 파일(텍스트, 웹페이지, 이미지) 이면 해석해서 보여주고, 
			알 수없는 파일(zip..)이면 다운로드를 한다.
		-->
		<!-- 방법1 -->
		<div><a href="/jsp/files/<%= filename %>"><%= orgfilename %></a></div>
		
		<!-- 방법2 - <a download> 속성이 파일을 무조건 다운로드 되게 해준다. -->
		<div><a href="/jsp/files/<%= filename %>" download><%= orgfilename %></a></div>
		
		<!-- 방법3 (가장 완벽한방법) -->
		<div><a href="/jsp/download.jsp?filename=<%= filename %>&orgfilename=<%= orgfilename %>"><%= orgfilename %></a></div>
		
	
	</div>
	
	<script>


	</script>
</body>
</html>