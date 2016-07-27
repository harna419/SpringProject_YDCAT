<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	
	<body>
	
	<c:set var="ses" value="${sessionScope.loginId}"/>
	
	<img src="/asdf/resources/book.jpg" width="900"><br>
	<a href="/YDCAT/list.do">글목록</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<c:choose>		
		<c:when test="${ses!=null}">
		<a href="/YDCAT/logOut.do">&nbsp;&nbsp; 로그아웃</a>
		</c:when>
		
		<c:otherwise>
		<a href="/YDCAT/login.do">&nbsp;&nbsp; 로그인</a>
		</c:otherwise>
	</c:choose>
	</body>
</html>

