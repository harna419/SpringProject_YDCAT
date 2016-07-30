<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 관리 페이지</title>
<link href="/YDCAT/resources/style.css" rel="stylesheet" type="text/css">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/YDCAT/resources/jquery.battatech.excelexport.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $("#btnExport").click(function () {
        $("#excel_body").battatech_excelexport({
            containerid: "excel_body"
, datatype: 'table'
        });
    });
});

$(function(){
	$(".location").hide()
	
	$("#addExcel").on('click',function(){
		//alert("asdf");
		$("#addExcel").hide()
		$(".location").show()
	})
})

function writeFormCheck(){
	if($("#location").val() == null || $("#location").val() == ""){
		alert("경로를 등록해주세요");
	$("#location").val('').focus();
	return false;
	}
	return true;
}	
</script>
	

</head>
<body>
<!--  
회원 관리 페이지
엑셀 삽입/출력 예정
회원 정보를 리스트로 뽑아내기-->

<table align="center" id="excel_body">
	
	<tr bgcolor="#CFE1E8">
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
	
	<tr>
		<td colspan="10" align="center">
		<c:forEach var="pageNum" begin="1" end="${totalPage }" step="1">
		[<a href="memberInfo.do?pageNum=${pageNum }">${pageNum }</a>]	&nbsp;
		</c:forEach>
		</td>
	</tr>
	
</table>

<table>
	<tr>
		<td>
		<form method="post" action="insertmember.do" onsubmit="return writeFormCheck()">
		<input type="button" id="btnExport" value="엑셀에 저장">
		<input type="button" id="addExcel" value="엑셀파일 추가">
		<input type="text" id="location" class="location" name="location" size="20">
		<input type="submit" class="location" value="파일 등록">
		</form>
		</td>
	</tr>
</table>

</body>
</html>