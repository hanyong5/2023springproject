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

<form action="<c:url value="j_spring_security_check" />" method="post">
	<c:if test="${param.error == !null }">
	<div>
		login Error!<br>
		${error_message }
	</div>
		
	</c:if>
	id:<input type="text" name="j_username"><br />
	pw:<input type="text" name="j_password"><br />
	<input type="submit" value="LOGIN" > 
</form>
</body>
</html>