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
	<%@ include file="./header_rwk51.jsp" %> 
	<br>
	<%
	Connection con = null;
	PreparedStatement pstmt = null;
	request.setCharacterEncoding("UTF-8");
	ResultSet rs = null;
	int result = 1;
	String sql = null;
	int rwk51_id = Integer.parseInt(request.getParameter("rwk51_id"));
	String rwk51_date = "";
	String rwk51_time = "";
	String rwk51_name = "";
	String rwk51_hp = "";
	String rwk51_chk = "";
	String rwk51_heatchk = "";
	try {
		String driverName = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost/db_rwk51";
		String user = "root";
		String passwd = "maria";
		Class.forName(driverName);
		Class.forName(driverName);
		con = DriverManager.getConnection(url, user, passwd);
	} catch (Exception e) {
		e.printStackTrace();
		result =0;
	}
	if(result==1){
		System.out.println("드라이버 연결 성공");
	}else if(result==0) {
		System.out.println("드라이버 연결 실페");	
	}
   sql = "SELECT * FROM rwk51  where rwk51_id = ?";
   System.out.println(sql+rwk51_id);
   pstmt = con.prepareStatement(sql);
   pstmt.setInt(1, rwk51_id);
   rs = pstmt.executeQuery();
  if(rs.next()){
	  	rwk51_date = rs.getString("rwk51_date");
		 rwk51_time =rs.getString("rwk51_time");
		 rwk51_name = rs.getString("rwk51_name");
		 rwk51_hp = rs.getString("rwk51_hp");
		 rwk51_chk = rs.getString("rwk51_chk");
		 rwk51_heatchk = rs.getString("rwk51_heatchk");
  }    
%>
	<form method="post" action="./rwk51_dao.jsp">
		<br>날짜 :
		<input type="text" name="rwk51_date" size="30" value=<%=rwk51_date%> readonly><br>
		시간 : <input type="text" name="rwk51_time" size="50" value=<%=rwk51_time%>> <br>
		이름 : <input type="text" name="rwk51_name" size="30" value=<%=rwk51_name%>><br>
		휴대폰 : <input type="text" name="rwk51_hp" size="10" value=<%=rwk51_hp%>> <br>
		개인정보 동의  : <input type="text" name="rwk51_chk" size="11" value=<%=rwk51_chk%>> <br>
		발열유무 : <input type="text" name="rwk51_heatchk" size="11" value=<%=rwk51_heatchk%>> <br>
		<input type="hidden" name="actionType" value="U"> <br>
		<input type="hidden" name="rwk51_id" value=<%=rwk51_id %>>
		<input type="submit" value="수정">
	</form>
	<br>
<%@ include file="./footer_rwk51.jsp" %> 
</body>
</html>