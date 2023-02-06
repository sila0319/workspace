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
if( !session.getAttribute("userclass").equals("block")){ //금지된 유저인 경우 화면 보여주지 않는다.
%>

 <img src="/member01/images/yju_com_01.jpg"/>
 
 <ul>
 <%
 	if(session.getAttribute("userclass").equals("admin")){ //로그인한사람이 관리자 일경우 관리자 페이지 
 		
 	
 %>
 	<!-- 관리자 전용 페이지 부분 -->
  <h1>관리자 모드 추가해야함 login page.jsp에 존재함</h1>
  <li><a href="/member01/user_select.jsp?currentPageNo=0">사용자 조회</a></li>
  <li><a href="/member01/user_update.jsp?currentPageNo=0">사용자 수정</a></li>
  <li> <a href="/member01/mem_views/member01.jsp">회원관리페이지01</a>
  <li> <a href="/member01/mem_views/member02.jsp">회원관리페이지02</a>
  <li> <a href="/member01/mem_views/member03.jsp">회원관리페이지03</a>
  <li> <a href="/member01/mem_views/member04.jsp">회원관리페이지04</a>
  <li> <a href="/member01/mem_views/member05.jsp">회원관리페이지05</a>
 <%
 	}else{//관리자가 아니라면 일반 화면만 보여주기
 		
 %>
  <li><a href="/member01/user_update2.jsp?currentPageNo=0">사용자 수정</a></li>
  <li> <a href="/member01/mem_views/member01.jsp">회원관리페이지01</a>
  <li> <a href="/member01/mem_views/member02.jsp">회원관리페이지02</a>
  <li> <a href="/member01/mem_views/member03.jsp">회원관리페이지03</a>
  <li> <a href="/member01/mem_views/member04.jsp">회원관리페이지04</a>
  <li> <a href="/member01/mem_views/member05.jsp">회원관리페이지05</a>
  <%
 	}
  %>
 </ul>
 <%
	}else{ //금지된 유저임을 알려준다.
		 %>
		<h1>금지된 유저입니다.</h1>
		<%
	}	
	 %>


</body>
</html>