<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>	

<%@ include file="./header_rwk51.jsp" %>
</head>
<body>
 <form name="login" method="post" action="./rwk51_member_dao.jsp">
		    아이디 : <input type="text" name="userid" size="30" id = "userid" required><br>
		    비밀번호 : <input type="password" name="userpasswd" size="30" required><br>
		    이름 : <input type = "text" name = "username" size = "30" required> <br>
		    이메일 : <input type="email" name= "useremail" size = "30"><br>
		    휴대폰 : <input type="text" name ="userphone" maxlength="11" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"><br>
		    주민등록번호 : <input type = "text" name ="userRRN1" maxlength="6" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" required>
		    -<input type = "text" name ="userRRN2" maxlength="7" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" required><br>
		    <input type="hidden" name="actionType" value="JOIN">
		    <input type="submit" value="회원가입">
		 </form>	
<%@ include file="./footer_rwk51.jsp" %>		
	
</body>
</html>