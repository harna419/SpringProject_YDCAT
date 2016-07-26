<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		function check(){
			
			if($("#id").val() == null || $("#id").val() == ""){ 
				alert("id를 입력해주세요");
			$("#id").val('').focus();
			return false;
			}
			
			if($("#passwd").val() == null || $("#passwd").val() == ""){ 
				alert("글쓴이를 입력해주세요");
			$("#passwd").val('').focus();
			return false;
			}
			
			return true;
		}
	</script>
	
	<style type="text/css">
	A:link {text-decoration:none;color:#696969; font-size:9pt;}
	A:hover{text-decoration:yes;color:#66CCFF; font-size:9pt;}
	TABLE{font-size:9pt;}
	tr{	line-height: 30px}
	.but{
	font-size:9pt;
	background-color: white;
	border: 1px solid gray ;
	}
	</style>
	</head>
	
	<body>
		
	<form method="post" action="loginPro.do" onSubmit="return check()">
	<table width="600" align="center">
	<tr>
		<td height="370" colspan="5" align="center"><img src="/asdf/resources/pic.jpg" width="420">
		</td>
	</tr>
	<tr>
		<td width="80" align="right">ID</td>
		<td width="200" align="right"><input type="text" name="id" id="id" size="20"></td>
		<td width="20"></td>
		<td width="60" align="center">PWD</td>
		<td width="240"><input type="text" name="passwd" id="passwd" size="20"></td>
	</tr>
	
	<tr>
		<td colspan="5" align="center" height="80">
		<input type="submit" value="Login"  class="but">&nbsp;&nbsp;
		<input type="reset"  value="Reset"  class="but">&nbsp;&nbsp;
		<input type="button" onclick="location.href='insertForm.do'" value="Join Us" class="but">
		</td>
	</tr>
	<tr><td height="10"></td></tr>
	</table>
	</form>
	
	</body>
</html>