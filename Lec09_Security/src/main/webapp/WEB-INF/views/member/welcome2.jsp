<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty pageContext.request.userPrincipal }">
		<p>login</p>
	</c:if>
	welcome - member<br />
	
	user id : ${pageContext.request.userPrincipal.name }<br/>
	<a href="/logout">logout</a>
</body>
</html>