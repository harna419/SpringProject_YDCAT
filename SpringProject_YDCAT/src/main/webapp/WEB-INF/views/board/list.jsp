<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>

	<link href="/YDCAT/resources/style.css" rel="stylesheet" type="text/css">

	</head>
	
	<body>
	<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;게시판 리스트</h2>
		
	<table align="center">
	<tr>
		<td colspan="5" align="right">
		<a href="writeForm.do">글쓰기</a></td>
	</tr>
	
	<tr bgcolor="#CFE1E8">
		<th width="80">글번호</th>
		<th width="230">글제목</th>
		<th width="120">글쓴이</th>
		<th width="180">날짜</th>
		<th width="70">조회수</th>
	</tr>	
	
	<c:forEach var="map" items="${list }">	
		
	<tr>
		<td align="center" class="line">${map.no}</td>
		
		<td class="line">&nbsp;&nbsp;	
		<c:if test="${map.ref_level>0 }">		
		<img src="/YDCAT/resources/level.png" width="${map.ref_level*25 }" height="15">
		<img src="/YDCAT/resources/point.png" height="15">
		<a href="content.do?no=${map.no }">${map.subject }</a>
		</c:if>
		
		<c:if test="${map.ref_level==0 }">
		<a href="content.do?no=${map.no }">${map.subject }</a>
		</c:if>
		</td>
				
		
		<td align="center" class="line">${map.id }</td>
		<td align="center" class="line">
		<fmt:formatDate pattern="yyyy년 MM월 dd일" value="${map.regdate }"/></td>
		<td align="center" class="line">${map.readcount }</td>
	</tr>
		
	</c:forEach>
	
	<tr>
		<td colspan="5" align="center">
		<c:forEach var="pageNum" begin="1" end="${totalPage }" step="1">
		[<a href="list.do?pageNum=${pageNum }">${pageNum }</a>]	&nbsp;
		</c:forEach>
		</td>
	</tr>
	
	<tr>
	<td colspan="5" align="center">
	<form method="post" action="search.do" name="searchForm">
	<select name="searchField">
			<option value="id">작성자</option>
			<option value="subject">글 제목</option>
			<option value="content">글 내용</option>
	</select>
	<input type="text" name="searchValue">&nbsp;&nbsp;<input type="submit" value="검색" class="but">
	</form>
	</td>
	</tr>
	
	</table>
	<br>
	
	</body>
</html>