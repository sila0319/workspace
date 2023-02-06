<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.sql.*" %>
    <%@ page import ="rwk51_boardgame_mall.model.user.*" %>
    
    <%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file= "/header.jsp" %>
<body>
<%
	UserDTO user;

	user = (UserDTO)request.getAttribute("myPage");
%>

<%-- <div align = "left">

	<div><%=user.getUser_num()%></div>
	<div>사용자 아이디 :  <br><%=user.getUser_id() %></div>
	
	<div><%=user.getUser_passwd() %></div>
	<div>사용자 이름 : <br><%=user.getUser_name() %></div>
	<div><%=user.getUser_RRN()  %></div>
	<div>사용자 이메일 : <br><%=user.getUser_email() %></div> 
	<div>사용자 휴대폰 : <br><%=user.getUser_phone() %></div>
	<div>사용자 주소 : <br><%=user.getUser_address() %></div>
	<div>사용자 상세주소 : <br><%=user.getUser_addressdetail() %></div>
	<div>사용자 등급: <br><%=user.getUser_class()%></div>
	<br>
	<div><a href ="./updateUserDisplay.be?user_num=<%=user.getUser_num()%>">정보수정</a></div>
</div>
<div align = "center">
	<p><a href="./getCartList.be?user_num=<%=user.getUser_num()%>" >장바구니</a></p>
</div>
<%
if(session.getAttribute("user_class").equals("admin")){
	
%>
<div align = "right">
	<div><a href ="./getUserList.be">회원정보조회</a></div>
	<div><a href="./deleteUserListDisplay.be">회원정보삭제</a></div>
	<div><a href="./updateUserListDisplay.be">회원정보수정</a></div>
	<br>
	
	<div><a href ="<%=boardgameViewDir%>/boardgame_c.jsp">상품등록</a></div>
</div>
<%
}

%> --%>

	<div class="container row">
		<div class="col-lg-7">
		<div class="jumbotron" style="padding-top: 20px;">
				<h3 style="text-align: center;" class="mb-4">내 정보</h3>
					<!-- form -->
						<div class="form-group">
							<div>
								<span>아이디 : <%=user.getUser_id() %></span>
							</div>
						</div>
	
						<div class="form-group">
							<span>이름 : <%=user.getUser_name() %></span>
						</div>
			
						<div class="form-group">
							<span>이메일 : <%=user.getUser_email() %></span>
						</div>
	
						<div class="form-group has-danger">
							<span >휴대폰 : <%=user.getUser_phone() %></span>
						</div>
							
							
						<div class="form-group">
							<span >주소 : <%=user.getUser_address() %></span>
						</div>
	
						<div class="form-group">
							<span >상세 주소 : <%=user.getUser_addressdetail() %></span>
						</div>
						
						<div class="form-group">
							<span >등급 : <%=user.getUser_class() %></span>
						</div>
							<form id="form" method="post" action="./updateUserDisplay.be">
								<input class="btn btn-success float-right" style="margin-left: 10px;" type = "submit" value = "회원수정">
								<input type = "hidden" name = "user_num" value = "<%=user.getUser_num()%>">
								
							</form>
						<form id="form" method="post" action="./deleteUserForId.be">
								<input class="btn btn-success float-right" style="margin-left: 10px;" type = "submit" value = "회원탈퇴">
								<input type = "hidden" name = "user_num" value = "<%=user.getUser_num()%>">
								
							</form>
			</div> 
		</div> 
		
		<div class="col-lg-7" >
			<div class="jumbotron" style="padding-top: 20px;">
			<div class="form-group">
							<div><a href="./getCartList.be?user_num=<%=user.getUser_num()%>" >장바구니</a></div>
						</div>
			</div>
		</div >
			<%
			if(session.getAttribute("user_class").equals("admin")){
			%>
			<div class="col-lg-7" >
				<div class="jumbotron" style="padding-top: 20px;">
				<h3 style="text-align: center;" class="mb-4">관리자 기능</h3>
					<!-- form -->
						<div class="form-group">
							<div><a href ="./getUserList.be">회원정보조회</a></div>
						</div>
	
						<div class="form-group">
							<div><a href="./deleteUserListDisplay.be">회원정보삭제</a></div>
						</div>
			
						<div class="form-group">
							<div><a href="./updateUserListDisplay.be">회원정보수정</a></div>
						</div>
	
						<div class="form-group has-danger">
								<div><a href ="<%=boardgameViewDir%>/boardgame_c.jsp">상품등록</a></div>
						</div>
				</div>
			</div> 
			<%
			}
			%>
	</div>

</body>
<%@ include file= "/footer.jsp" %>
</html>