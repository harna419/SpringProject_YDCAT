<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<style type="text/css">
	A:link {text-decoration:none;color:#696969; font-size:9pt;}
	A:hover{text-decoration:yes;color:#66CCFF; font-size:9pt;}
	TABLE{font-size:10pt;}
	tr{	line-height: 35px}
	.but{
	font-size:9pt;
	background-color: white;
	border: 1px solid gray ;
	}
	</style>
	
	<script type="text/JavaScript" src="http://code.jquery.com/jquery-1.7.min.js"></script>
	
	<!-- jQuery+Ajax 로 id 중복체크 --> 
	<!-- 아래 2개는 주소 자동 입력 -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//cdn.poesis.kr/post/popup.min.js"></script>
	
	<script type="text/javascript">
	<!--Open API naver 에서 제공-->
	$(function(){
		$("#postcodify_search_button").on("click", function(e) {
			$("#postcodify_search_button").postcodifyPopUp();
			e.preventDefault(); 
		});
		

	
	function insertcheck(){
				
		if($("#passwd").val() == null || $("#passwd").val() == ""){ 
			alert("passwd를 입력해주세요");
		$("#passwd").val('').focus();
		return false;
		}
		
		if($("#name").val() == null || $("#name").val() == ""){ 
			alert("이름을 입력해주세요");
		$("#name").val('').focus();
		return false;
		}
		
		if($("#jumin1").val() == null || $("#jumin1").val() == ""){ 
			alert("주민등록번호 앞자리를 입력해주세요");
		$("#jumin1").val('').focus();
		return false;
		}
		
		if($("#jumin2").val() == null || $("#jumin2").val() == ""){ 
			alert("주민등록번호 뒷자리를 입력해주세요");
		$("#jumin2").val('').focus();
		return false;
		}
	}
	</script>
	</head>
	
	<body>
	<br>
	<h2><center>회원정보 수정</center></h2>
		
	<form method="post" name="writeForm" action="insertPro.do" onsubmit="return insertcheck()">
	<!-- 아이작스부분 함수 이름 딱히 안썼는데 실행되는 이유 : 시작하자마자 실행되니까. -->
	
	<table width="550" align="center">
	<tr><td height="15"></td></tr>
	<tr>
		<td width="120" bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;ID</td>
		<td width="7"></td>
		<td><input type="text" name="id" id="id" size="20" value="${memberDto.id }" readonly="readonly">
		</td>
	</tr>

	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호</td>
		<td width="7"></td>
		<td><input type="text" name="passwd" id="passwd" size="20"></td>
	</tr>

	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;이름</td>
		<td width="7"></td>
		<td><input type="text" name="name" id="name" size="20" value="${memberDto.name }"></td>
	</tr>

	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;주민등록번호</td>
		<td width="7"></td>
		<td><input type="text" name="jumin1" id="jumin1" size="6" value="${memberDto.jumin1 }">&nbsp;-
		<input type="text" name="jumin2" id="jumin2" size="7" value="${memberDto.jumin2 }"></td>
	</tr>

	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;email</td>
		<td width="7"></td>
		<td><input type="text" name="email" id="email" size="30" value="${memberDto.email }"></td>
	</tr>

	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;우편번호</td>
		<td width="7"></td>
		<td><input type="text" name="num1" id="num1" size="10" class="postcodify_postcode6_1" readonly="readonly">
		<input type="text" name="num2" id="num2" size="10" class="postcodify_postcode6_2" readonly="readonly">&nbsp;
		<button id="postcodify_search_button" class="but">우편번호 검색</button></td>
	</tr>

	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;도로명주소</td>
		<td width="7"></td>
		<td><input type="text" name="address" id="address" class="postcodify_address" size="50" readonly="readonly"></td>
	</tr>
	
	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;상세주소</td>
		<td width="7"></td>
		<td><input type="text" name="details" id="details" class="postcodify_details" size="50"></td>
	</tr>

	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;직업</td>
		<td width="7"></td>
		<td><select name="job">
			<option value="${memberDto.job }">${memberDto.job }</option>
			<option value="회사원">회사원</option>
			<option value="연구전문직">연구전문직</option>
			<option value="교수학생">교수학생</option>
			<option value="문화예술종사">문화예술종사</option>
			<option value="백수">백수</option>
		</select></td>
	</tr>

	<tr>
		<td bgcolor="#EAEAEA">&nbsp;&nbsp;&nbsp;&nbsp;blog</td>
		<td width="7"></td>
		<td><input type="text" name="blog" id="blog" size="40" value="${memberDto.blog }"></td>
	</tr>
	
	<tr>
		<td colspan="3" align="center" height="50">
		<input type="submit" value="Edit" class="but">&nbsp;&nbsp;
		<input type="reset" value="Reset" class="but"></td>
	</tr>
	<tr><td height="20"></td></tr>

	</table>
	
	</form>
	</body>
</html>