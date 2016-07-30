<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	.box{
	border-bottom:1px solid #D97A73;
	border-top:1px solid #D97A73;
	border-left:1px solid #D97A73;
	border-right:1px solid #D97A73;
	}
	</style>
	</head>
	
	<body>
	
	<!-- 세션을 변수로 받는다. -->
	<c:set var="ses" value="${sessionScope.loginId}"/>
	
	<br>
	<table width="105">

	
	<c:choose>		
		
		<c:when test="${ses == 'admin'}">
		<tr>
		<td align="center" height="55"><font size="1" color="gray">■</font><a href="/YDCAT/loginSuccess.do">&nbsp;&nbsp; 홈</a></td>
		</tr>
		<tr><td height="10"></td></tr>
		<tr>
		<td align="center" height="55"><font size="1" color="gray">■</font><a href="/YDCAT/editForm.do">&nbsp;&nbsp; 정보수정</a></td>
		</tr>
		<tr>
		<td align="center" height="55"><font size="1" color="gray">■</font><a href="/YDCAT/memberInfo.do">&nbsp;&nbsp; 회원 관리</a></td>
		</tr>
		</c:when>
		
		
		<c:when test="${ses!=null}">
		<tr>
		<td align="center" height="55"><font size="1" color="gray">■</font><a href="/YDCAT/loginSuccess.do">&nbsp;&nbsp; 홈</a></td>
		</tr>

		<tr><td height="10"></td></tr>

		<tr>
		<td align="center" height="55"><font size="1" color="gray">■</font><a href="/YDCAT/editForm.do">&nbsp;&nbsp; 정보수정</a></td>
		</tr>
		</c:when>
		
		<c:otherwise>
		<tr>
		<td align="center" height="55"><font size="1" color="gray">■</font><a href="/YDCAT/login.do">&nbsp;&nbsp; 로그인</a></td>
		</tr>
		</c:otherwise>
	</c:choose>	
	

	<tr><td height="10"></td></tr>

	<tr>
		<td align="center" height="55"><font size="1" color="gray">■</font><a href="/YDCAT/list.do">&nbsp;&nbsp; 글목록</a></td>
	</tr>

	</table>
	
	<br>
	<br><br>

	
	</body>
</html>
