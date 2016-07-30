<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/JavaScript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
<link href="/YDCAT/resources/style.css" rel="stylesheet" type="text/css">
	<script>
	function insertcheck(){
				
		if($("#password").val() == null || $("#password").val() == ""){ 
			alert("password를 입력해주세요");
		$("#passwd").val('').focus();
		return false;
		}
		
		return true;
	}
	</script>
</head>
<body>
	<form method="post" name="writeForm" action="memberDelete.do" onsubmit="return insertcheck()">
	<br><br>
	<table width="550" align="center">
	<tr>
	<td bgcolor="#EAEAEA" align="center">회원 탈퇴를 원하시면 패스워드를 입력하세요.</td>
	</tr>
	<tr>
	<td align="center"><input type="password" id="password" name="password"></td>
	</tr>
	<tr>
	<td align="center">
	<input type="submit" value="탈퇴" class="but">&nbsp;&nbsp;
	<input type="button" value="취소" onclick="history.go(-1)" class="but">
	<input type="hidden" name="id" value="<%=session.getAttribute("loginId")%>">
	</td>
	</tr>
	</table>
	</form>
</body>
</html>