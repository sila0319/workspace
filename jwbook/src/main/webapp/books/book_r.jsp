<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>서적관리시스템 - 조회(R)</h1>
<hr>
<h2>전체보기(부분조회,페이징기능 미포함)</h2>
<%
	String driverName = "org.mariadb.jdbc.Driver";
	String url = "jdbc:mariadb://localhost/book_db";
	String user = "root";
	String passwd = "maria";
	
	Class.forName(driverName);
	Connection con = DriverManager.getConnection(url,user,passwd);
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
	ResultSet rs = pstmt.executeQuery();
%>
	<table border="1">
		<thead>
			<tr>
				<th>순번</th>
				<th>제목</th>
				<th>출판사</th>
				<th>출판년도</th>
				<th>가격</th>
			</tr>
		</thead>
	<tbody>
	
	<%
		while(rs.next()){
			book_id = rs.getInt("book_id");
			title = rs.getString("title");
			publisher = rs.getString("publisher");
			year = rs.getString("year");;
			price = rs.getInt("price");;
	%>
		<tr>
				<th><%=book_id%></th>
				<th><%=title%></th>
				<th><%=publisher%></th>
				<th><%=year%></th>
				<th><%=price%></th>
				
			</tr>
	
	<%
		}
		pstmt.close();
		con.close();
	%>
	</tbody>
	</table>
	
	<br><a href="./index.jsp">홈으로 돌아가기</a>
</body>
</html>