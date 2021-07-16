<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<%@ include file="/inc/asset.jsp" %>

<style>
   .container {
      width: 1000px;
   }
   #list {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
   }
   .item {
      width: 300px;
      border: 1px solid #999;
      border-radius: 5px;
      margin: 10px;      
      padding: 15px;
      box-shadow: 2px 2px 4px #888;
   }
   .item .name {
      font-size: 20px;
      border-bottom: 1px dashed #CCC;
   }
   .item .name .engname {
      font-variant: small-caps;
   }
   .info {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      padding-top: 30px;
   }
   .info1 {
      font-size: 13px;
      color: #555;
   }
   .info2 {
      font-size: 13px;
      color: #555;
      text-align: right;
   }
   .btns {
      padding: 10px;
   }
</style>

</head>
<body>
   <!-- list.jsp -->
   <div class="container">
      <h1 class="page-header">Business Card <small>총 ${ list.size() }개의 명함이 있습니다.</small></h1>      
      
      <div id="list">
      		
      	<c:forEach items="${list}" var="dto">
	        <div class="item">
	            <div class="name">
	               <div class="korname">${ dto.korname }</div>
	               <div class="engname">${ dto.engname }</div>
	            </div>
	            <div class="info">
	               <div class="info1">
	                  <div class="tel">${ dto.tel }</div>
	                  <div class="email">$ { dto.email }</div>
	               </div>
	               <div class="info2">
	                  <div class="company">${ dto.company }</div>
	                  <div class="position">$ { dto.position }</div>
	               </div>
	            </div>
	         </div>
         </c:forEach>
         
      </div>
      
      <hr>
      
      <div class="btns">
         <input type="button" value="Register" class="btn btn-default"
         	onclick="location.href='/jsp/card/add.do';">
      </div>
      
   </div>   
   
   <script>
      
   </script>
</body>
</html>














