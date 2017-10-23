<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%

Cookie[] reqCookie = request.getCookies();
if (reqCookie != null) {
	for (Cookie cookie : reqCookie) {
		response.getWriter().println(cookie.getName() + " - " + cookie.getValue()	+ "<br/>");
	}
} else {
	response.getWriter().println("No cookies");
}		

%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
showCookies.jsp
</body>
</html>