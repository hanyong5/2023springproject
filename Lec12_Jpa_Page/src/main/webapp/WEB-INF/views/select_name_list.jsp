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

총글의 개수 : ${totalElements }<br>
총 페이지 : ${totalPages }<br>
size : ${size }<br>
pageNumber : ${pageNumber}<br>
numberOfElements : ${numberOfElements }<br>



<c:forEach var="lists" items="${lists }">
	${lists.email } / ${lists.name }
	<hr>
</c:forEach>

</body>
</html>