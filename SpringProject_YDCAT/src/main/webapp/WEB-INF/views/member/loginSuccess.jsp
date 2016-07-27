<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	A:link {text-decoration:none;color:#696969; font-size:9pt;}
	A:hover{text-decoration:yes;color:#66CCFF; font-size:9pt;}
	tr{	line-height: 30px; font-size:9pt;}
	.line{border-bottom:1px solid lightgray}
	</style>
	<script>
	function updateUser(){
		//alert("updateUser(): "+document.updateForm.id.value);
		document.updateForm.action = "editForm.do"; //회원수정
		document.updateForm.submit();
	}
	
	function deleteUser(){
		//alert("deleteUser(): "+document.updateForm.id.value);
		document.deleteForm.action = "memberDelete.do"; //회원탈퇴
		document.deleteForm.submit();
	}
	
	</script>
	</head>
	
	<body>	
	<%
	//session.setAttribute("id", "park");
	//session.setAttribute("pwd", "park123");
	%>
	<br>
	<table width="600" align="center">
	<tr>
		<td height="320" align="center"><img src="/asdf/resources/blue.jpg" width="400">
		</td>
	</tr>
	
	<tr>
		<td align="center" bgcolor="lightgray">
		
		
		 
		 <b><%=session.getAttribute("loginId")%></b>
		 
		</td>
	</tr>
	
	<tr>
		<td align="center" class="line">
		<!-- 위에꺼처럼 할 수 있는걸 아래처럼 el,jstl로 할거야 -->
		<c:set var="id" value="${memberDto.id }" scope="session"/>
		<font size="2">
		<a href="list.do">게시판 리스트</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javaScript:updateUser()">회원정보 수정</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javaScript:deleteUser()">회원 탈퇴</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="logOut.do">로그아웃</a>
		</font>	
		</td>
	</tr>
	
	<tr>
		<td height="50"></td>
	</tr>
	</table>
	
	
	<form method="get" name="updateForm">
	</form>
	
	<form method="post" name="deleteForm">
		<input type="hidden" id="id" name="id" value="${id }">
	</form>
	
	</body>
</html>