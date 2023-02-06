<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/globalData.jsp" %>
 <%@page import="java.io.PrintWriter"%>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if( session.isNew()){ //새로운 객체가 만들어졌다면 세션을 저장한다. 
		session.setAttribute("loginState", "logout"); //로그인 상태저장
		session.setAttribute("viewState", null); //지금 보는 화면 저장
		session.setAttribute("user_num", null); //유저 번호 저장
		session.setAttribute("user_name", null); //유저 이름 저장
		session.setAttribute("user_id", null); //유저 아이디 저장
		session.setAttribute("user_passwd", null); //유저 비번 저장
		session.setAttribute("user_class", null); //유저 등급 저장	
	}
%>
<nav class="navbar navbar-light" id="navigationBox">
	<a class="navbar-brand"  href="<%=rootDir %>/index.jsp">
		<img src="<%=imgDir%>/logo.png" style="max-width: 100%; height: 0%;"/>
	</a>

	
	<div align = "center">
		
		<form id="form" method="post" action="./searchBoardgame.be" >
		
		<select name = "b_category" id = "b_category">
				<option value = "all" selected>전체</option>
				<option value = "strategy" >전략</option>
				<option value = "party">파티</option>
				<option value = "new">신상</option>
				<option value = "school">교구</option>
				<option value = "first">선 주문</option>
				<option value = "best">베스트</option>
			</select>
			<input type="text" name ="search">
			<input type ="hidden" name = "currentPageNo" value = 0>
			<input type="submit" value="상품검색">
			
			
		</form>
		
	</div>
	 <%
		if(session.getAttribute("loginState").equals("login") ){ // loginState의 상태가 로그인이라면 
%>
	
		<!-- 유저아이디 띄우고 로그인 상태 알려주기  밑에 로직은 로그아웃 버튼을 추가하고 기능   -->
		<div id="loginForm">
			<!-- <nav class="navbar navbar-default"> -->
				<table>
				<tr>				
					<td><b><%=session.getAttribute("user_id")%></b>님 반가워요!</td>
					<br>
					<td>&nbsp&nbsp</td>
					<form method="post" action="./logoutUser.be">
						<td style="text-align: center;">
							<button type="submit" name="actionType" value="LOGOUT" class="btn btn-primary" >로그아웃</button>
						</td>
					</form>
					
				</tr>
				<tr>
					<td>
						<a href="./getMyInfo.be">마이페이지</a>
					</td>
				</tr>
			</table>

		</div>
		
<%
		}
		else
		{ //로그인 상태가 아니라면
%>	 
		<!-- 아이디,비밀번호를 입력하는 창을 띄워주고 로그인을 시도한다면 정보를 전달한다. -->
		 <%			
			if(session.getAttribute("loginState").equals("errorID") || session.getAttribute("loginState").equals("errorPW") ){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('아이디 혹은 비밀번호가 잘못되었습니다.')");
				script.println("</script>");
				session.setAttribute("loginState", "logout");
			}
		
%> 	
	<div id="loginForm">
	 <form method="post" action="./loginUser.be">
	 	<table style="border-collapse: separate; border-spacing: 30px 0;">
			<tr>
				<td><input type="text" placeholder="아이디" class="form-control form-control-sm" id="idForm" name="user_id" ></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="비밀번호" class="form-control form-control-sm" id="pwForm" name="user_passwd"></td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center;">
					<button type="submit" name="actionType" value="LOGIN" class="btn btn-primary">로그인</button>
							
					<button type="submit" formaction="<%=userViewDir %>/user_c.jsp"  class="btn btn-success">회원가입</button>
				</td>
			</tr>
		<!--     아이디 : <input type="text" name="user_id" size="10" class="form-control form-control-sm" id="idForm">
		    비밀번호 : <input type="password" name="user_passwd" size="10">
		    <input type="hidden" name="actionType" value="LOGIN">
		    <input type="submit" value="로그인"> -->
		    	</table>
		 </form>
		 
		<%--  <a href="<%=userViewDir %>/user_c.jsp" >회원가입</a> --%>
	</div>


<%
	}
%>
</nav>

<!-- <div style="display: flex; justify-content: center;"> -->
	<div style="text-align:center">
	<!-- 	<ul class="list-group"> -->
	<a href="./getBoardgameList.be?currentPage=0&category=strategy" style = "margin-left:50px">전략 게임</a>
 	<a href="./getBoardgameList.be?currentPage=0&category=party" style = "margin-left:50px" >파티 게임</a>
 	<a href="./getBoardgameList.be?currentPage=0&category=new"style = "margin-left:50px">신상 게임</a>
 	<a href="./getBoardgameList.be?currentPage=0&category=school" style = "margin-left:50px">학교용 교구</a>
 	<a href="./getBoardgameList.be?currentPage=0&category=first"style = "margin-left:50px">선주문</a>
 	<a href="./getBoardgameList.be?currentPage=0&category=best" style = "margin-right:0px" >베스트 100</a>
 	<!-- 	</ul> -->
 	</div>
<!-- </div> -->


<hr> 
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="JS/bootstrap.js"></script> -->
	
	<script type ="text/javascript" src="<%=jsUserDir%>/infochk.js"></script>
		<script type ="text/javascript" src="<%=jsBoardgameDir%>/b_infochk.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" 
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" 
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" 
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	
	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
	
<!-- <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"
	integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+"
	crossorigin="anonymous"></script>
	 -->
</body>
</html>