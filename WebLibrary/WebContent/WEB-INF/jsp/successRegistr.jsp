<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
     
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<fmt:message key="registerSuccess "/>
<h1>
		<c:out value="Hello, ${requestScope.user.name}"/>
		<c:out value="${requestScope.user.surname}"/>
	</h1>
<fmt:message key="welcom "/>
<a href="libraryClient.jsp"> <fmt:message key="toSite "/> </a> 

</body>
</html>