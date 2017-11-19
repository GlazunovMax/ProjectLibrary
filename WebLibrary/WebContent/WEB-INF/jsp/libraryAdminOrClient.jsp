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
<link href="css/menuLibraryAdmin.css" rel="stylesheet" type="text/css">
</head>
<body>

<%-- 		 <fmt:message key="registerSuccess "/> --%>
	
	<h1>
		<c:if test="${not empty requestScope.user.name}">
			<font color="green" size="3" face="Georgia">
				<c:if test="${sessionScope.role eq 'admin'}"><fmt:message key="welcomLibAdm"/></c:if>
				<c:if test="${sessionScope.role eq 'client'}"><fmt:message key="welcomLibClient"/></c:if>
			</font>
				<br>
			<fmt:message key="hello"/> <c:out value="${requestScope.user.name}"/>
									   <c:out value="${requestScope.user.surname}"/>
								
<%-- 				role = ///<c:out value="${sessionScope.role}"/> --%>
		</c:if>
	</h1>

<jsp:include page="fragment/menuAdminOrClient.jsp"/> 

	<!-- 	Успешное добавление книги -->
	<c:if test="${not empty requestScope.AddBookSuccess}">
		<h4><c:out value="${requestScope.AddBookSuccess}" /> </h4>
	</c:if>
	
	<!-- REMOVE -->
	<c:if test="${not empty requestScope.SuccessBookRemove}">
		<h4><c:out value="${requestScope.SuccessBookRemove}" /> </h4>
	</c:if>
	
	<!-- UPDATE -->
	<c:if test="${not empty requestScope.BookSuccessUpdate}">
		<h4><c:out value="${requestScope.BookSuccessUpdate}" /> </h4>
	</c:if>

<jsp:include page="fragment/allBookPage.jsp"/> 


	<!-- 	Ошибка если нет вообще книг в БД -->
	<c:if test="${empty requestScope.allBookList}">
		<h4><c:out value="${requestScope.errorGetAll}" /> </h4>
	</c:if>


	<!-- 	НЕУспешное добавление книги -->
	<c:if test="${empty requestScope.AddBookerror}">
		<h4><c:out value="${requestScope.AddBookerror}" /> </h4>
	</c:if>


</body>
</html>