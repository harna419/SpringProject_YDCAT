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
	A:link {text-decoration:none;color:#696969}
	A:hover{text-decoration:yes;color:#66CCFF}
	.line{border-bottom:1px solid #D97A73}
	TABLE{font-size:9pt;}
	tr{	line-height: 30px}
	</style>
	
	</head>
	
	<body>
	<h2>글 내용보기</h2>
	
	<table width="500" bgcolor="#F2E3DB">
	<tr>
		<td height="40px" align="center" colspan="4" class="line">
		<a href="update.do?no=${boardDto.no }">글수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="delete.do?no=${boardDto.no }">글삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="writeForm.do">새글 쓰기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="replyForm.do?no=${boardDto.no }">답글 쓰기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="list.do">리스트</a>&nbsp;
	</tr>
	
	<tr height="40px">
		<th width="90" bgcolor="#D97A73">글쓴이</th>
		<td width="150" class="line">${boardDto.id }</td>
		
		<th width="90" bgcolor="#D97A73">ip</th>
		<td class="line">${boardDto.ip}</td>
	</tr>
	
	<tr height="40px">
		<th bgcolor="#D97A73">글제목</th>
		<td class="line" colspan="3">${boardDto.subject}</td>
	</tr>
	
	
	
	<tr height="100px">
		<th bgcolor="#D97A73">글내용</th>
		<td class="line" colspan="3">${boardDto.content}</td>
	</tr>

	<tr height="40px">
		<th bgcolor="#D97A73">작성시간</th>
		<td class="line" colspan="3">
		<fmt:formatDate value="${boardDto.regdate }" pattern="yyyy. MM. dd hh:mm"/>
		</td>
	</tr>
	
	</table>
	
	</body>
</html>