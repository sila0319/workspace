<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
</style>

</head>
<body>
<% session.setAttribute("viewState", "update2"); %>
<%@ include file="./header.jsp" %>



<%
  	int usernum = (Integer)(session.getAttribute("usernum"));
   	String username = (String)session.getAttribute("username"); //유저 이름 저장
	String userid = (String)session.getAttribute("userid");
	String userpasswd = (String)session.getAttribute("userpw");
	String useremail = (String)session.getAttribute("useremail");
	String userphone = (String)session.getAttribute("userphone");
	String userclass = (String)session.getAttribute("userclass");
	
%>
<%
	if(session.getAttribute("userclass").equals("admin")){
		usernum = Integer.parseInt(request.getParameter("mem_num"));
		username = request.getParameter("mem_name");
		userid = request.getParameter("mem_id");
		userpasswd = request.getParameter("mem_passwd");
		useremail = request.getParameter("mem_email");
		userphone = request.getParameter("mem_phone");
		userclass = request.getParameter("mem_class");
%>
<h1>관리자 모드 - 사용자 업데이트</h1>
<hr>
<form method="post" action="/member01/mem_models/member_dao.jsp">
	<br>이름 : <input type="text" name="username" size="30" value=<%=username%>> 
	<br>아이디 : <input type="text" name="userid" size="30" value=<%=userid%>> 
	<br>비밀번호: <input type="text" name="userpasswd" size="30" value=<%=userpasswd%>>
	<br>이메일 : <input type="text" name="useremail" size="30" value=<%=useremail%>> 
	<br>휴대폰 : <input type="text" name="userphone" size="30" value=<%=userphone%>> 
	<br>등급 : <input type="text" name="userclass" size="30" value=<%=userclass%>> 
	<br><button type="submit" id="updatechk" value="" onclick="updateid()" name="updatechk">수정</button>
	<br> <input type="hidden" name="actionType" value="UPDATE"> 
	<br> <input type="hidden" name="usernum" value=<%=usernum%>>   
	<br> <input type="hidden" name="userState" value="admin"> 
	
	</form>
	
	<%
		}else{
			
	%>
		<h1>사용자 모드 - 사용자 업데이트</h1>
		<hr>
		<form method="post" action="/member01/mem_models/member_dao.jsp">
			<br>이름 (수정불가): <input type="text" name="username" size="30" value=<%=username%> readonly> 
			<br>아이디 (수정불가): <input type="text" name="userid" size="30" value=<%=userid%> readonly> 
			<br>비밀번호: <input type="text" name="userpasswd" size="30" value=<%=userpasswd%>>
			<br>이메일 : <input type="text" name="useremail" size="30" value=<%=useremail%>> 
			<br>휴대폰 : <input type="text" name="userphone" size="30" value=<%=userphone%>> 
			<br>등급 (수정불가): <input type="text" name="userclass" size="30" value=<%=userclass%> readonly> 
			<br><button type="submit" id="updatechk" value="" onclick="updateid()" name="updatechk">수정</button>
			<input type="hidden" name="actionType" value="UPDATE"> 
			<input type="hidden" name="usernum" value=<%=usernum%>> 
			<input type="hidden" name="userState" value="user"> 
			</form>
			
			<form method="post" action="/member01/mem_models/member_dao.jsp">
			<button type="submit" id="deletechk" name = "deletechk" value = "" onclick="deleteid()">삭제</button>
			<input type="hidden" name="actionType" value="DELETE"> 
			<input type="hidden" name="usernum" value=<%=usernum%>> 
			<input type="hidden" name="userState" value="user">
			</form>
	<%
		}

	
	%>
	<script>
		function updateid(){
			let check = confirm("정말로 수정하시겠습니까?");
			if(check){
				
				document.getElementById("updatechk").value= 'true';
				
			}else{
				document.getElementById("updatechk").value='false';
			}
			alert(document.getElementById("updatechk").value);
		}
		
		function deleteid(){
			let check = confirm("정말로 삭제하시겠습니까?");
			if(check){
				
				document.getElementById("deletechk").value= 'true';
				
			}else{
				document.getElementById("deletechk").value='false';
			}
			alert(document.getElementById("deletechk").value);
		}
	</script>

  
 <br>
 <%@ include file="./footer.jsp" %> 
</body>
</html>