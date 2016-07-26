<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	.bottom{border-bottom:1px solid lightgray}
	.right{border-right:1px solid lightgray}
	.top{border-top:1px solid lightgray}
	</style>
	</head>
	
	<body>
	<table align="center">
	<tr>
		<td colspan="2" height="200" align="right" class="bottom">
			<tiles:insertAttribute name="header"/> <!-- header.jsp -->
		</td>
	</tr>
	
	<tr>
		<td width="120" height="400" align="center" valign="top" class="right">
			<tiles:insertAttribute name="side"/> <!-- side.jsp -->
		</td>
		<td valign="top">
			<tiles:insertAttribute name="content"/> <!-- 중앙에 표시 -->
		</td>
	</tr>
	
	<tr>
		<td colspan="2" height="70" align="center" valign="bottom" class="top">
			<tiles:insertAttribute name="foot"/> <!-- foot.jsp -->
		</td>
	</tr>
	</table>
	</body>
</html>

