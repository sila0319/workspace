<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% session.setAttribute("viewState", "main"); %>
<%@ include file="./header.jsp" %> <!-- heder.jsp 파일을 포함하도록 설정한다. 해당하는 파일을 부르는거다.-->
<%
	if(session.getAttribute("loginState").equals("login")){ //로그인상태가 로그인을포함하고 있다면  로그인 page를 보여준다.
%>
		 <jsp:include page="./login_page.jsp"/>  <!-- page.jsp에다가 값을 보낸다. -->
<%
	}else{//그외의 경우에는 로그인하지않았으므로 로그아웃 페이지를 보여준다.
%>
		 <jsp:include page="./logout_page.jsp"/>
<%
	}
%>		 
<%@ include file="./footer.jsp" %> <!-- footer 페이지 부분을 불러온다.-->
</body>
</html>