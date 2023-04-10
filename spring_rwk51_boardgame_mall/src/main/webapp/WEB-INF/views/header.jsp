<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./globalData.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-light" id="navigationBox">
		<a class="navbar-brand" href="/">
		<%--<img src="${imgDir}/logo.png" style="max-width: 100%; height: 0%;" />--%>			
			<img src="/img/logo.png" alt="Example Image" style="max-width: 100%; height: 0%;">
		</a>
		<div align="center">
			<!-- <form id="form" method="post" action="/user/" > -->
			<select name="b_category" id="b_category">
				<option value="all" selected>전체</option>
				<option value="strategy">전략</option>
				<option value="party">파티</option>
				<option value="new">신상</option>
				<option value="school">교구</option>
				<option value="first">선 주문</option>
				<option value="best">베스트</option>
			</select>
			<input type="text" name="search">
			<input type="hidden" name="currentPageNo" value="0">
			<input type="submit" value="상품검색">
			<!-- </form> -->
		</div>

<c:if test="${sessionScope.loginState != 'login'}">

  <c:if test="${sessionScope.loginState == 'errorID' || sessionScope.loginState == 'errorPW'}">
    <script>
      alert('아이디나 비밀번호가 잘못되었습니다.');
    </script>
    <c:set var="loginState" scope="session" value="logout"/>
  </c:if>
</c:if>
<div id="loginForm">
	<c:if test="${sessionScope.loginState == 'logout'}">
		<form method="post" action="/user/login">
		 	<table style="border-collapse: separate; border-spacing: 30px 0;">
				<tr>
					<td><input type="text" placeholder="아이디" class="form-control form-control-sm" id="user_id" name="user_id" ></td>
				</tr>
				<tr>
					<td><input type="password" placeholder="비밀번호" class="form-control form-control-sm" id="user_passwd" name="user_passwd"></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<button type="submit" name="actionType" value="LOGIN" class="btn btn-primary">로그인</button>						
						<button type="submit" formaction="/user/join"  class="btn btn-success">회원가입</button>
					</td>
				</tr>
			</table>
		 </form>
	</c:if>
</div>
<c:if test="${sessionScope.loginState == 'login'}">
	<div id="loginForm">
		<table>
			<tr>
				<td><b> ${sessionScope.user_id}님 만나서 반갑습니다.</b></td>
				<br>
				<td>&nbsp&nbsp</td>
				<form method="post" action="/user/logout">
					<td style="text-align: center;">
						<button type="submit" name="actionType" value="LOGOUT" class="btn btn-primary" >로그아웃</button>
					</td>
				</form>
			</tr>
			<tr>
				<td>
					<a href="/user/GetMyInfo">마이페이지</a>
				</td>
			</tr>
		</table>
	</div>
</c:if>
</nav>
<div style="text-align:center">
	<a href="./getBoardgameList.be?currentPage=0&category=strategy" style="margin-left:50px">전략 게임</a>
	<a href="./getBoardgameList.be?currentPage=0&category=party" style="margin-left:50px">파티 게임</a>
	<a href="./getBoardgameList.be?currentPage=0&category=new" style="margin-left:50px">신상 게임</a>
	<a href="./getBoardgameList.be?currentPage=0&category=school" style="margin-left:50px">학교용 교구</a>
	<a href="./getBoardgameList.be?currentPage=0&category=first" style="margin-left:50px">선주문</a>
	<a href="./getBoardgameList.be?currentPage=0&category=best" style="margin-right:0px">베스트 100</a>
</div>
<hr> 
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
</body>
</html> 
		
