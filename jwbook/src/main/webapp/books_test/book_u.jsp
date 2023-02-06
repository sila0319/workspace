<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서적관리시스템 - 수정(U)</title>
</head>
<body>
	<h1>서적관리시스템 - 수정(U)</h1>
	<hr>

	<%
	String driverName = "org.mariadb.jdbc.Driver";
	String url = "jdbc:mariadb://localhost/book_db";
	String user = "root";
	String passwd = "maria";

	Class.forName(driverName);
	Connection con = DriverManager.getConnection(url, user, passwd);
	PreparedStatement pstmt = null;
	request.setCharacterEncoding("UTF-8");
	%>

	<%
	int book_id;
	String title;
	String publisher;
	String year;
	int price;

	String sql = "SELECT * FROM books ORDER BY book_id";
	pstmt = con.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery(sql);
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
				;
				price = rs.getInt("price");
				;
			%>
			<tr>
				<th><%=book_id%></th>
				<th><%=title%></th>
				<th><%=publisher%></th>
				<th><%=year%></th>
				<th><%=price%></th>
				<th>
				<a href="./book_u2.jsp?book_id=<%=book_id%>&title=<%=title%>&publisher=<%=publisher%>&year=<%=year%>&price=<%=price%>">수정</a>
				</th>
				<!-- DB에 lock을 안 걸게되어서 id만 전송후에 where절을 통해 데이터베이스에서 셀렉트를해서 처리를 하는게 좋다. -->

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