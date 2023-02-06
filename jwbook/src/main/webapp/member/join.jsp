<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<h1>회원가입</h1>
<br>
</head>
<body>
<form method="post" action="memberdao.jsp" >
	<br>이름 : <input type ="text" name ="userName"  size = "20">
	<br>아이디 : <input type ="text" name ="userID"  size = "20">
	<br>패스워드 : <input type ="PassWord" name ="userPW"  size = "20">
	<br>패스워드 확인: <input type ="PassWord" name ="userPW"  size = "20">
	<br>이메일 : <input type ="email" name ="userEmail"  size = "20">
	<br>전화번호 : <input type ="text" name ="userHP"  size = "20">
	<br>주민등록번호 : <input type ="text" name ="userBirth1"  size = "6">-<input type ="text" name ="userBirth2"  size = "7">
	
	<br><input type ="hidden" name ="actionType"  value = "J">
	<br><input type ="submit" value = "가입">
</form>
</body>
</html>