<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/singInAndRegistrCss.css" rel="stylesheet" type="text/css">
</head>
<body>

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="signIn" />
		 
		<h2 style="margin-bottom: 0px;">Login:</h2>
		<input type="text" name="login" value="" class="textbox"/><br /> 
		
		<h2 style="margin-bottom: 0px;">Password:</h2> 
		<input type="password" name="password" value="" class="textbox"/><br/><br>
		 
		<input type="submit" value="sign_in" class="button" />
	</form>



	<div class="error">
		<c:if test="${not empty requestScope.errorSingIn}">
			<c:out value="${requestScope.errorSingIn}" />
		</c:if>
	
		<c:if test="${not empty requestScope.error}">
			<c:out value="${requestScope.error}" />
		</c:if>
	</div>	


</body>
</html>