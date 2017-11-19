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
<link href="css/singInAndRegistrCss.css" rel="stylesheet" type="text/css">
</head>
<body>

       <h3 align="center" style="color: black;"> <fmt:message key="registerAsAdmin"/> </h3>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration" />
		 
		<h2 style="margin-bottom: 0px;"> <fmt:message key="name"/> </h2>
		<input type="text" name="name" value="" class="textbox" required/><br /> 
		
		<h2 style="margin-bottom: 0px;"> <fmt:message key="surname"/> </h2> 
		<input type="text" name="surname" value="" class="textbox"/><br />
		 
		<h2 style="margin-bottom: 0px;"> <fmt:message key="login"/> </h2> 
		<input type="text" name="login" value="" class="textbox"/><br /> 
		
		<h2 style="margin-bottom: 0px;"> <fmt:message key="password"/> </h2>
		<input type="password" name="password" value="" class="textbox"/><br />
		
		<h2 style="margin-bottom: 0px;"> <fmt:message key="role"/> </h2>
		<input type="text" name="role" readonly="readonly" value="admin" class="textbox" /><br />
		<br>
		<input type="submit" value="<fmt:message key="send"/>" class="button"/>
	</form>


<div class="error">
	<c:if test="${not empty requestScope.errorRegistration}">
		<c:out value="${requestScope.errorRegistration}" />
	</c:if>
</div>	

</body>
</html>