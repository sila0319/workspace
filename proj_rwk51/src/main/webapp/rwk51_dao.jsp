<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import ="java.sql.*" %>
 <%@ page import= "java.util.*" %>
 <%@ page import=" java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>RWK11_dao 부분입니다.</h1>
<hr>

<%
	Connection con = null;
	PreparedStatement pstmt = null;
	request.setCharacterEncoding("UTF-8");
	ResultSet rs = null;
	java.util.Date date = new java.util.Date();
	
	int result = 1;
	String sql = null;
	try {
		String driverName = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost/db_rwk51";
		String user = "root";
		String passwd = "maria";
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
%>

<%
	 String actionType = request.getParameter("actionType");
	int rwk51_id = 0;
	String rwk51_date = "";
	String rwk51_time = "";
	String rwk51_name = "";
	String rwk51_hp = "";
	String rwk51_chk = "";
	String rwk51_heatchk = "";
	
	switch(actionType){
	case  "C":
		
		 try {
			 rwk51_date = request.getParameter("rwk51_date");
			 rwk51_time = request.getParameter("rwk51_time");
			 rwk51_name = request.getParameter("rwk51_name");
			 rwk51_hp = request.getParameter("rwk51_hp");
			 rwk51_chk = request.getParameter("rwk51_chk");
			 rwk51_heatchk = request.getParameter("rwk51_heatchk");
			 
				sql = "INSERT INTO rwk51 (rwk51_date ,rwk51_time, rwk51_name, rwk51_hp, rwk51_chk, rwk51_heatchk) VALUES(?,?,?,?,?,?)";
		    	pstmt = con.prepareStatement(sql);
		    	pstmt.setString(1,rwk51_date);
		    	pstmt.setString(2,rwk51_time);
		    	pstmt.setString(3,rwk51_name);
		    	pstmt.setString(4,rwk51_hp);
		    	pstmt.setString(5,rwk51_chk);
		    	pstmt.setString(6,rwk51_heatchk);
				System.out.println(sql);
				System.out.println(rwk51_date+" "+ rwk51_time);
				result = pstmt.executeUpdate();
				
				if(result==1){
					System.out.println("수기작성완료");
				
				}else{
					System.out.println("수기작성실패");
					
					result = 0;
				}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	result =0;
		    }
		 break;
		 
	case  "R":
		break;
		
	case  "U":
		rwk51_id = Integer.parseInt(request.getParameter("rwk51_id"));
		rwk51_date = request.getParameter("rwk51_date");
		 rwk51_time = request.getParameter("rwk51_time");
		 rwk51_name = request.getParameter("rwk51_name");
		 rwk51_hp = request.getParameter("rwk51_hp");
		 rwk51_chk = request.getParameter("rwk51_chk");
		 rwk51_heatchk = request.getParameter("rwk51_heatchk");
		 
			sql = "update rwk51 set rwk51_date = ?, rwk51_time =  ?, rwk51_name= ?, rwk51_hp= ? ,rwk51_chk = ? ,rwk51_heatchk = ? where rwk51_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rwk51_date);
			pstmt.setString(2, rwk51_time);
			pstmt.setString(3, rwk51_name);
			pstmt.setString(4, rwk51_hp);
			pstmt.setString(5, rwk51_chk);
			pstmt.setString(6, rwk51_heatchk);
			pstmt.setInt(7, rwk51_id);
			System.out.println(sql);
			result = pstmt.executeUpdate();
			if(result == 1){
				System.out.println("레코드 업데이트 성공");
			}
			else{
				System.out.println("레코드 업데이트 실패");
				
			}
		break;
		
		
	case  "D":
		rwk51_id = Integer.parseInt(request.getParameter("rwk51_id"));
		sql = "delete from rwk51 where rwk51_id="+rwk51_id;
		pstmt = con.prepareStatement(sql);
		
		System.out.println("삭제부분"+sql);
		result = pstmt.executeUpdate();
		if(result == 1){
			System.out.println("레코드 업데이트 성공");
		}
		else{
			System.out.println("레코드 업데이트 실패");
		}
		break;
	case "D2":
		
		sql = "delete from rwk51 where rwk51_date < NOW() - INTERVAL 4 week";
		pstmt = con.prepareStatement(sql);
		result = pstmt.executeUpdate();
		if(result == 1){
			System.out.println("레코드 업데이트 성공");
		}
		else{
			System.out.println("레코드 업데이트 실패");
		}
		break;
	}
	pstmt.close();
	con.close(); 
%>
<jsp:forward page="./index_rwk51.jsp"/>
</body>
</html>