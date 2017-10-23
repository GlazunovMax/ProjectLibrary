<%@ page language="java" contentType="text/html; charset=UTF-8" import="by.htp.library.bean.User"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/menuLibraryAdmin.css" rel="stylesheet" type="text/css">
</head>
<body>

	<h1>
		<c:if test="${not empty requestScope.user.name}">
			<font color="green" size="3" face="Georgia">
				Welcome L i b r a r y Client
			</font>
				<br>
			<c:out value="Hello, ${requestScope.user.name}"/>
		</c:if>
	</h1>

	<jsp:include page="WEB-INF/jsp/fragment/menuClient.jsp"/>
	<jsp:include page="WEB-INF/jsp/fragment/allBookPage.jsp"/> 
	
	<!-- 	Ошибка если нет вообще книг в БД -->
	<c:if test="${empty requestScope.allBookList}">
		<h4><c:out value="${requestScope.errorGetAll}" /> </h4>
	</c:if>
	
	
</body>
</html>