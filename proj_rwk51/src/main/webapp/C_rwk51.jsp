<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import= "java.util.*" %>
    <%@ page import=" java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="./header_rwk51.jsp" %> 
<%
Date date = new Date();
SimpleDateFormat rwk51_date = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat rwk51_time = new SimpleDateFormat("HH:mm:ss");

System.out.println(rwk51_date.format(date));
System.out.println(rwk51_time.format(date));

if(session.getAttribute("loginState").equals("login")){
	System.out.println(session.getAttribute("userphone")+" "+session.getAttribute("username"));
%>
 <form name="login" method="post" action="./rwk51_dao.jsp">
 	  		날짜 : <input type="date" name="rwk51_date" size="30" value = <%=rwk51_date.format(date)%> required><br>
		    방문시간 : <input type="time" name="rwk51_time" size="30" value = <%=rwk51_time.format(date)%> required><br>
		    성명 : <input type="text" name = "rwk51_name" size = "30" value = <%=session.getAttribute("username")%> required ><br>
		    휴대폰 : <input type="text" name ="rwk51_hp" maxlength="11" value = <%=session.getAttribute("userphone")%> onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"><br>
		    개인정보정보 수집 제공동의 : <select name = rwk51_chk><option value = "o">o</option> <option value="x">x</option></select><br>
		   	발열현상 유무 : <select name = rwk51_heatchk><option value = "x">x</option> <option value="o">o</option></select><br>
		    <input type="hidden" name="actionType" value="C">
		    <input type="submit" value="출입자 명부작성">
		 </form>	
<%} else { %>
 <form name="login" method="post" action="./rwk51_dao.jsp">
 	  		날짜 : <input type="date" name="rwk51_date" size="30" value = <%=rwk51_date.format(date)%> required><br>
		    방문시간 : <input type="time" name="rwk51_time" size="30" value = <%=rwk51_time.format(date)%> required><br>
		    성명 : <input type="text" name = "rwk51_name" size = "30" required ><br>
		    휴대폰 : <input type="text" name ="rwk51_hp" maxlength="11" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"><br>
		    개인정보정보 수집 제공동의 : <input type="text" name= "rwk51_chk" size = "30" required ><br>
		   	발열현상 유무 : <input type="text" name= "rwk51_heatchk" size = "30" required><br>
		    <input type="hidden" name="actionType" value="C">
		    <input type="submit" value="출입자 명부작성">
		 </form>	
<%} %>
<%@ include file="./footer_rwk51.jsp" %> 
</body>
</html>