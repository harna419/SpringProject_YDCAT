<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 관리 페이지</title>
<style type="text/css">
	A:link {text-decoration:none;color:#696969; font-size:9pt;}
	A:hover{text-decoration:yes;color:#66CCFF; font-size:9pt;}
	.line{border-bottom:1px solid #D97A73}
	TABLE{font-size:9pt;}
	tr{	line-height: 30px}
	</style>

</head>
<body>
<!--  
회원 관리 페이지
엑셀 삽입/출력 예정
회원 정보를 리스트로 뽑아내기-->

<table bgcolor="#F2E3DB" align="center">
	
	<tr bgcolor="#D97A73">
		<th width="80">회원번호</th>
		<th width="80">아이디</th>
		<th width="100">비밀번호</th>
		<th width="150">주민번호</th>
		<th width="60">직업</th>
		<th width="300">주소</th>
		<th width="150">이메일</th>
		<th width="150">휴대폰</th>
		<th width="120">가입 날짜</th>
		<th width="120">접속일</th>
	
	
	</tr>	
<c:forEach var="map" items="${list }">	
		
	<tr>
		<td align="center" class="line">${map.no}</td>
		<td align="center" class="line">${map.id }</td>
		<td align="center" class="line">${map.password }</td>
		<td align="center" class="line">${map.jumin }</td>
		<td align="center" class="line">${map.job}</td>
		<td align="center" class="line">${map.addr}</td>
		<td align="center" class="line">${map.email}</td>
		<td align="center" class="line">${map.phone}</td>
		
		<td align="center" class="line">
		<fmt:formatDate pattern="yyyy년 MM월 dd일" value="${map.regdate }"/></td>
		
		<td align="center" class="line">
		<fmt:formatDate pattern="yyyy년 MM월 dd일" value="${map.curdate }"/></td>
		
	</tr>
		
	</c:forEach>

</table>
</body>
</html>