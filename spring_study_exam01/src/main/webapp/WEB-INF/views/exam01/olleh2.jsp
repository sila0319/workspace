<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>olleh list 출력 (테이블 형태)</h1>
<hr>
<table border = "1">
<c:forEach items="${olleh}" var="o">
	<tr><td>${o} </td></tr>
</c:forEach>
</table>

</body>
</html>