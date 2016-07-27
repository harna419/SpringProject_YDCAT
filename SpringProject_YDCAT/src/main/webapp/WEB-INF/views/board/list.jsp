<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
	<style type="text/css">
	A:link {text-decoration:none;color:#696969; font-size:9pt;}
	A:hover{text-decoration:yes;color:#66CCFF; font-size:9pt;}
	.line{border-bottom:1px solid #D97A73}
	TABLE{font-size:9pt;}
	tr{	line-height: 30px}
	</style>
	
	</head>
	
	<body>
	<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;게시판 리스트</h2>
		
	<table bgcolor="#F2E3DB" align="center">
	<tr>
		<td colspan="5" align="right">
		<a href="writeForm.do">글쓰기</a></td>
	</tr>
	
	<tr bgcolor="#D97A73">
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
		<img src="/asdf/resources/level.png" width="${map.ref_level*25 }" height="15">
		<img src="/asdf/resources/point.png" height="15">
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
	
	</table>
	<br>
	
	</body>
</html>