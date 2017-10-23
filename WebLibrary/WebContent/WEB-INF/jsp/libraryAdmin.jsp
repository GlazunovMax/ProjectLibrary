<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				Welcome Library A d m i n 
			</font>
				<br>
			<c:out value="Hello, ${requestScope.user.name}"/>
			<c:out value="${requestScope.user.surname}"/>
		</c:if>
	</h1>

<jsp:include page="fragment/menuAdmin.jsp"/> 
<jsp:include page="fragment/allBookPage.jsp"/> 

<!-- 	Ошибка если нет вообще книг в БД -->
	<c:if test="${empty requestScope.allBookList}">
		<h4><c:out value="${requestScope.errorGetAll}" /> </h4>
	</c:if>

</body>
</html>