<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
	<link href="/YDCAT/resources/style.css" rel="stylesheet" type="text/css">	
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
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;글쓰기폼</h2>
		
		<form method="post" action="write.do" onsubmit="return writeFormCheck()" enctype="multipart/form-data">
		<table align="center">
		<tr>
			<td align="right" colspan="2" class="line"><a href="list.do">리스트</a></td>
			
		<tr>
			<th width="110" bgcolor="#B7D7E8">글쓴이</th>
			<td class="line"><%=session.getAttribute("loginId")%>
			<!-- 
			<input type="text" id="writer" name="writer" size="10">
			 -->
			<!-- 세션값 추가  -->		
			</td>
		</tr>
		
		<tr>
			<th bgcolor="#B7D7E8">글제목</th>
			<td class="line"><input type="text" id="subject" name="subject" size="30"></td>
		</tr>
					
		<tr>
			<th bgcolor="#B7D7E8">글내용</th>
			<td class="line"><textarea name="content" id="content" cols="40" rows="13"></textarea></td>
		</tr>
		
		<tr>
		<td colspan="2"><input type="file" name="uploadfile"></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="글등록" class="but">
			<input type="reset" value="리셋" class="but">
			<input type="hidden" name="id" value="<%=session.getAttribute("loginId")%>">
		</tr>
		
		
		
		</table>
		</form>
		
	</body>
</html>