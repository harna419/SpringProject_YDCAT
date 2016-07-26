<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
	function writeFormCheck(){
		
		if($("#subject").val() == null || $("#subject").val() == ""){
			alert("글제목을 입력해주세요");
		$("#subject").val('').focus();
		return false;
		}
				
		if($("#content").val() == null || $("#content").val() == ""){  
			alert("글내용을 입력해주세요");
		$("#content").val('').focus();
		return false;
		}
		
		return true;
	}//function
	</script>
	</head>
	
	<body>
		<h2>수정폼</h2>
		
		
		<form method="post" action="updatePro.do" onsubmit="return writeFormCheck()">
		<table bgcolor="#F2E3DB">
		<tr>
			<td align="right" colspan="2" class="line"><a href="list.do">리스트</a></td>
			
		<tr>
			<th width="70" bgcolor="#D97A73">글쓴이</th>
			<td class="line"><%=session.getAttribute("loginId")%></td>
		</tr>
		
		<tr>
			<th bgcolor="#D97A73">글제목</th>
			<td class="line"><input type="text" id="subject" name="subject" size="30" value=${boardDto.subject }></td>
		</tr>
				
		<tr>
			<th bgcolor="#D97A73">글내용</th>
			<td class="line"><textarea name="content" id="content" cols="40" rows="13">${boardDto.content}</textarea></td>
		</tr>
				
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="글등록">
			<input type="reset" value="리셋">
			<input type="hidden" id="no" name="no" value="${boardDto.no }"/>
		</tr>
		
		
		</table>
		</form>
		
	</body>
</html>