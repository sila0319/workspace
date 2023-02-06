<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if( session.isNew()){ //새로운 객체가 만들어졌다면 세션을 저장한다. 
		session.setAttribute("loginState", "logout"); //로그인 상태저장
		session.setAttribute("viewState", "main"); //지금 보는 화면 저장
		session.setAttribute("usernum", null); //유저 번호 저장
		session.setAttribute("username", null); //유저 이름 저장
		session.setAttribute("userid", null); //유저 아이디 저장
		session.setAttribute("userpw", null); //유저 비번 저장
		session.setAttribute("useremail", null); //유저 이메일 저장
		session.setAttribute("userphone", null); //유저 휴대폰 저장
		session.setAttribute("userclass", null); //유저 등급 저장	
	}
%>
<table>
 <tr>
	
	 <td width="400" align="center"><h1>2101151/유원규 (rwk51)</h1></td> 
	 <td>
<%
		if(session.getAttribute("loginState").equals("login") ){ // loginState의 상태가 로그인이라면 
%>
		<%=session.getAttribute("userid")%>님 로그인중. 
		<!-- 유저아이디 띄우고 로그인 상태 알려주기  밑에 로직은 로그아웃 버튼을 추가하고 기능   -->
		<form name="logout" method="post" action="./rwk51_member_dao.jsp">
		  <input type="hidden" name="actionType" value="LOGOUT">
		  <input type="submit" value="로그아웃">
		</form> 
<%
		}
		else{ //로그인 상태가 아니라면
%>		 
		<!-- 아이디,비밀번호를 입력하는 창을 띄워주고 로그인을 시도한다면 정보를 전달한다. -->
		 <form name="login" method="post" action="./rwk51_member_dao.jsp">
		    아이디 : <input type="text" name="userid" size="10">
		    비밀번호 : <input type="password" name="passwd" size="10">
		    <input type="hidden" name="actionType" value="LOGIN">
		    <input type="submit" value="로그인">
		 </form>
		 <a href="./join_rwk51.jsp" >회원가입</a>
<%			
			if(session.getAttribute("loginState").equals("errorID") || session.getAttribute("loginState").equals("errorPW") ){
				out.print("[사용자 ID 혹은 PW가 잘못 되었습니다.]");
			}
		}
%>		
	 </td>
 </tr>
</table>
</body>
</html>