<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서적관리시스템 - 삭제(D)</title>
</head>
<body>
	<h1>서적관리시스템 - 삭제(D)</h1>
	<hr>
	<%
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	request.setCharacterEncoding("UTF-8");
	try {
		String driverName = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost/book_db";
		String user = "root";
		String passwd = "maria";
		Class.forName(driverName);
		con = DriverManager.getConnection(url, user, passwd);
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>

	<%
	int book_id;
	String title;
	String publisher;
	String year;
	int price;

	String sql = "SELECT * FROM books ORDER BY book_id";
	try {
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
	} catch (Exception e) {
		e.printStackTrace();
	}
	

	%>
	<table border="1">
		<thead>
			<tr>
				<th>순번</th>
				<th>제목</th>
				<th>출판사</th>
				<th>출판년도</th>
				<th>가격</th>
				<th>수정</th>

			</tr>
		</thead>
		<tbody>

			<%
			while (rs.next()) {
				book_id = rs.getInt("book_id");
				title = rs.getString("title");
				publisher = rs.getString("publisher");
				year = rs.getString("year");
				price = rs.getInt("price");
			%>
			<tr>
				<th><%=book_id%></th>
				<th><%=title%></th>
				<th><%=publisher%></th>
				<th><%=year%></th>
				<th><%=price%></th>
				<th><a href="./book_dao.jsp?actionType=D&book_id=<%=book_id%>">삭제</a></th>
			</tr>
			<%
			}
			pstmt.close();
			con.close();
			%>
		</tbody>
	</table>
	<br>
	<a href="./index.jsp">홈으로 돌아가기</a>
</body>
</html>