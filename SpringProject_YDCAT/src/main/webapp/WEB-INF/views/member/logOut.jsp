<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.id!=null }">
	<c:remove var="id" scope="session"/>
	<c:redirect url="login.do"/>
</c:if>  