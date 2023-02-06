<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.io.PrintWriter"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<%
//드라이버 연결 부분 중요하다.
Connection con = null;
PreparedStatement pstmt = null;
request.setCharacterEncoding("UTF-8");
ResultSet rs = null;
int result = 1;
try {
	String driverName = "org.mariadb.jdbc.Driver";
	String url = "jdbc:mariadb://localhost/db_rwk51";
	String user = "root";
	String passwd = "maria";
	Class.forName("org.mariadb.jdbc.Driver");
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
	String userID_DUMMY = null;
	String userPW_DUMMY = null;
	int usernum = -1; //유저 번호 저장
	String username = null;  //유저 이름 저장
	String userid = null;
	String userpasswd = null;
	String userpasswdck = null;
	String userclass = null;
	String useremail = null; //유저 이메일 저장
	String userphone = null; //유저 휴대폰 저장
	String userState = null;
	String userRRN = null;
	
	String sql;
	
	int loginState;
	PrintWriter script = response.getWriter();
	

	switch( actionType){
		case "LOGIN":
			userid = request.getParameter("userid");
		    userpasswd = request.getParameter("passwd");
		    userclass = null;
		    sql = "select * from member where mem_id = ? AND mem_passwd = ? ";
		    try {
		    	pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, userpasswd);
				System.out.println(sql);
				rs = pstmt.executeQuery();
				if(rs.next()){ //sql이 없데이트 되었고 조건이 되었다면 실행이된다. 
					usernum  = rs.getInt("mem_num"); 
					username =rs.getString("mem_name");
					userID_DUMMY = rs.getString("mem_id");
					userPW_DUMMY = rs.getString("mem_passwd");
					useremail = rs.getString("mem_email");
					userphone =rs.getString("mem_phone");
					userclass = rs.getString("mem_class");
					//if(userID_DUMMY.equals(userid) && userPW_DUMMY.equals(userpasswd)){
						session.setAttribute("loginState", "login"); //현재 로그인상태인지 아닌지 체크한다. 
			    		session.setAttribute("usernum", usernum); //유저 번호 저장
			    		session.setAttribute("username", username); //유저 이름 저장
			    		session.setAttribute("userid", userid); //유저 아이디 저장
			    		session.setAttribute("userpw", userpasswd); //유저 비번 저장
			    		session.setAttribute("useremail", useremail); //유저 이메일 저장
			    		session.setAttribute("userphone", userphone); //유저 휴대폰 저장
			    		session.setAttribute("userclass", userclass); //유저 등급 저장
					//}
				}else{
					session.setAttribute("loginState", "errorPW");	
					session.setAttribute("loginState", "errorID");
					
					result = 0;
				}
				System.out.println(userID_DUMMY+userPW_DUMMY+userclass);	
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	result =0;
		    }
		    if(result==1){
		    	System.out.println("member_dao Login 성공");
		    }else if(result==0) {
		    	System.out.println("member_dao Login 실패");
		    	result = 1;
		    	script.println("<script>");
				script.println("alert('아이디 혹은 비밀번호를 다시 확인해주세요.')");
				script.println("history.back()");
				script.println("</script>");
				return;
		    }
		    break;
		case "LOGOUT":
			//로그아웃을 했으므로 userid, userpw 에 null을 넣어준다. loginState는 로그아웃을 넣어줌.
			session.setAttribute("userid", null);
			session.setAttribute("userpw", null);
			session.setAttribute("loginState", "logout");
			break;
		case "JOIN":
			try {
				username = request.getParameter("username");
				userid = request.getParameter("userid");
				userpasswd = request.getParameter("userpasswd");
				
				useremail = request.getParameter("useremail");
				userphone = request.getParameter("userphone");
				userRRN = request.getParameter("userRRN1")+"-"+request.getParameter("userRRN2");
				userclass = "user";
			
				
					sql = "INSERT INTO member (mem_name, mem_id, mem_passwd, mem_email, mem_phone, mem_RRN, mem_class) VALUES(?,?,?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, username);
					pstmt.setString(2, userid);
					pstmt.setString(3, userpasswd);
					pstmt.setString(4, useremail);
					pstmt.setString(5, userphone);
					pstmt.setString(6, userRRN);
					pstmt.setString(7, userclass);
					System.out.println(sql);
					System.out.printf("%s %s %s %s %s %s %s ",username,userid,userpasswd,useremail,userphone,userRRN,userclass);
					result = pstmt.executeUpdate();
					
					if(result==1){
						System.out.println("dao join부분 성공");
					}else{
						script.println("<script>");
						script.println("alert('에러발생 다시시도해주세요')");
						script.println("history.back()");
						script.println("</script>");
						return;
					}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	result =0;
		    }	
			System.out.println("JOIN");
			break;
			
			
				
			
			
		default:
			break;
	}
%>

<jsp:forward page="./index_rwk51.jsp"/>
<!-- index.jsp파일로 이동하도록 한다. -->

</body>
</html>