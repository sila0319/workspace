<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
text-align:center;
}
</style>
	
<script>
	$(function (){
		$("#btn_toggle").click(function (){
	  	$("#Toggle").toggle();
	  });
	}
</script>


</head>
<body>

 <h1>자기소개</h1>

 
 <div id = "main">
 	<p>
 	안녕하세요 2101151학번 유원규입니다.<br></br>
 	나이는 21살입니다. <br></br>
 	잘부탁드립니다.<br></br>
 	</p>
 	
 	<h2>목차</h2>
 	<div>
 		<button onclick = "location.href = '개발자.jsp'">개발자 </button><br></br>
 		<button onclick = "location.href = 'me.jsp'">It's me</button><br></br>
 	</div>
	 
 </div>
 
</body>
</html>