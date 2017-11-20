<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="titleLibrary"/></title>

<link href="css/indexCss.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="index">

<!-- 	<a href="Controller?language=en_index.jsp "> -->
<%-- 		<img alt="<fmt:message key="english"/>" src="${pageContext.request.contextPath}/resources/images/eng.jpg"> --%>
<!-- 	</a> -->

<!--     <a href="Controller?language=ru_index.jsp "> -->
<%-- 		<img alt="<fmt:message key="russian"/>" src="${pageContext.request.contextPath}/resources/images/rus.jpg"> --%>
<!--     </a> -->

<a href="Controller?language=en&page=index.jsp&command=changeLocale ">
		<img alt="<fmt:message key="english"/>" src="${pageContext.request.contextPath}/resources/images/eng.jpg">
	</a>

    <a href="Controller?language=ru&page=index.jsp&command=changeLocale ">
		<img alt="<fmt:message key="russian"/>" src="${pageContext.request.contextPath}/resources/images/rus.jpg">
    </a>
 

	<h1 title="<fmt:message key="titleLibrary"/> ">
		<fmt:message key="titleLibrary"/>
	</h1>

	<h4  title="<fmt:message key="register"/> ">
		<a href="registration.jsp"> <fmt:message key="register"/> </a>  
	</h4>
	<fmt:message key="or"/>
	<h4 title="<fmt:message key="signIn"/>">
		<a href="signIn.jsp"> <fmt:message key="signIn"/> </a> 
	</h4>
	
	
</div>	
	

</body>
</html>