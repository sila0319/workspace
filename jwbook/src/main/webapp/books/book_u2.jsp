<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>서적관리시스템 - 자료수정(U2)</h1>
	<br>

	<%
	int book_id;
	String title;
	String publisher;
	String year;
	int price;

	book_id = Integer.parseInt(request.getParameter("book_id"));
	title = request.getParameter("title");
	publisher = request.getParameter("publisher");
	year = request.getParameter("year");
	price = Integer.parseInt(request.getParameter("price"));
	%>
	<form method="post" action="./book_dao.jsp">
		<br>서적번호(수정불가)<input type="text" name="book_id" size="11"
			value=<%=book_id%> readonly> <br>서적명 : <input
			type="text" name="title" size="50" value=<%=title%>> <br>출판사
		: <input type="text" name="publisher" size="30" value=<%=publisher%>>
		<br>출판년도 : <input type="text" name="year" size="10"
			value=<%=year%>> <br>가격 : <input type="text"
			name="price" size="11" value=<%=price%>> <br>
		<input type="hidden" name="actionType" value="U"> <br>
		<input type="submit" value="수정">
	</form>

	<br>
	<a href="./index.jsp">홈으로 돌아가기</a>

</body>
</html>