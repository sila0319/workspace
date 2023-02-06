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
<h1>서적 관리 시스템 - 조회(R2)</h1>
<hr>
<h2>전체보기(부분조회 미포함, 페이징기능 포함)</h2>
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
	String sql2 = "SELECT count(*) FROM books ";
	pstmt = con.prepareStatement(sql2);
	ResultSet rs2= pstmt.executeQuery();
	
	int recordCnt =0;
	int pageCnt;
	
	while(rs2.next()){
		recordCnt = rs2.getInt(1);
	}
	
	pageCnt = recordCnt/10; //페이지를 10으로 나누었을때 딱 떨어지는 값만큼 페이지를 증가.
	
	//페이지를 10으로 나눈후 남은 몫이 존재할수 있으므로 그 값이 0이아닐 경우 pageCnt 1증가
	if(recordCnt%10 !=0){
		pageCnt++;
	}
	
%>
<%
	int book_id;
	String title;
	String publisher;
	String year;
	int price;
	
	
	int startRecord =0;
	int limitCnt = 10;
	int currentPageNo;
	
	currentPageNo = Integer.parseInt(request.getParameter("currentPageNo"));
	//index.jsp에서 currentPageNo를 넘겨주는걸 getParameter로 받는다. 
	startRecord = currentPageNo * 10;
	//request의 요청을 받으면 response를 하게되는데 response는 html문서로 저장되어 있다.
	
	String sql = "SELECT * FROM books ORDER BY book_id limit ?, ?";	
	pstmt = con.prepareStatement(sql);
	
	pstmt.setInt(1, startRecord);
	pstmt.setInt(2, limitCnt);
	
	
	ResultSet rs = pstmt.executeQuery();
%>
	<table border="1">
		<thread>
			<tr>
				<th>순번</th>
				<th>제목</th>
				<th>출판사</th>
				<th>출판년도</th>
				<th>가격</th>
			</tr>
		</thread>
	<tbody>
	<%
		while(rs.next()){
			book_id = rs.getInt("book_id");
			title = rs.getString("title");
			publisher = rs.getString("publisher");
			year = rs.getString("year");
			price = rs.getInt("price");	
	%>
		<tr>
			<td><%=book_id %></td>
			<td><%=title %></td>
			<td><%=publisher %>
			<td><%=year  %></td>
			<td><%=price %></td>
		</tr>
			
	<%
		}
	%>
	</tbody>
	</table>
<br>
<a href="./book_r2.jsp?currentPageNo=0">[FIRST]</a>
<%
	if(currentPageNo > 0 ){
%>
<a href="./book_r2.jsp?currentPageNo=<%=(currentPageNo-1) %>" >[PRE]</a>

<%
	}else{
%>
	[PRE]
<%
	}
	for(int i = 0; i<pageCnt; i++){
		if(i == currentPageNo){
%>
 [<%=(i+1) %>]
 <%
		}else{
 %>
 	<a href="./book_r2.jsp?currentPageNo=<%=i %>">[<%=(i+1) %>]</a>
 <%
		}
	}
	%>
<%
	if(currentPageNo < pageCnt - 1){
 %>
 <a href="./book_r2.jsp?currentPageNo=<%=(currentPageNo+1)%>">[NXT]</a>
 <%
	}else{
 %>
 [NXT]
 <%
	}
	pstmt.close();
	
	con.close();
 %>
 <a href="./book_r2.jsp?currentPageNo=<%=(pageCnt-1) %>">[END]</a>
 <br><br><a href="./index.jsp">홈으로 돌아가기</a>
</body>
</html>