<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보드모아</title>
<%@ include file= "/header.jsp" %>
</head>
<body>
<%	session.setAttribute("viewState", "index"); //지금 보는 화면 저장 %>

<main>
<div align = "center">
	<img src="<%=imgDir%>/boardgame.png" style="max-width: 100%; height: 0%;">
</div>
	<!-- <div>
		보드게임 사진리스트
	</div>
	<div>
		전략게시글 리스트
	</div>
	<div>
		파티게임 게시글 리스트
	</div>
	<div>
		신상게임 게시글 리스트
	</div> -->
	
</main>



</body>
<%@ include file= "/footer.jsp" %>
</html>