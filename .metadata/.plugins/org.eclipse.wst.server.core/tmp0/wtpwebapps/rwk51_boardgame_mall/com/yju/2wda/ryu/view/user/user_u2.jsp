<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="rwk51_boardgame_mall.model.user.* "%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.test {
	display: flex;
	width: 100%;
	justify-content: center;
}
</style>
<%@ include file= "/header.jsp" %>
</head>

<body>

<%
	UserDTO user = (UserDTO)request.getAttribute("user");
%>

<%-- <%
	if(session.getAttribute("user_class").equals("admin")){
%> --%>
<%-- <form method="post" action="./updateUserForId.be" >

	<br>유저아이디 : <input type="text" name="user_id" value =<%=user.getUser_id() %> size = 30 readonly>
	<br>
	<br>유저 비밀번호 : <input type="password" name="user_passwd" size = 30 value =<%=user.getUser_passwd() %> required id="user_pw" oninput="pw_chk()" >
	<br>유저 비밀번호 재확인 : <input type="password" name="user_passwdchk" size = 30  required id ="user_repw" oninput="pw_chk()">
	<br><span id="pw_chk"></span>
	<br>유저 이름 : <input type="text" name="user_name" size = 30 value =<%=user.getUser_name() %> required readonly>
	<br>
	<br>유저 주민등록번호 : <input type="text" name="user_RRN" size = 30 value =<%=user.getUser_RRN()%>  required readonly>
	<br>유저 이메일 : <input type="email" name="user_email" size = 30 value =<%=user.getUser_email()%>  required readonly>
	<br>
	<br>유저 휴대폰 : <input type="text" name="user_phone" size = 30 value =<%=user.getUser_phone()%> required readonly>
	<br>
	<br>유저 주소 : <input type="text" id="user_address" name="user_address"  value = "<%=user.getUser_address()%>" required/>
	<br>
	
	<br>유저 상세주소: <input type="text" name="user_addressdetail" value = "<%=user.getUser_addressdetail() %>" required />
	<br>
	<%
	if(session.getAttribute("user_class").equals("admin")){
	%>
	<br>등급 : <select name = "user_class">
				<option value = "user" selected>일반사용자</option>
				<option value = "admin">관리자</option>
			</select>
	<%
			}
	%>
	<br><input type="hidden" name = "user_num" value="<%=user.getUser_num()%>">
	<br><input type="submit"  value="저장">
</form> --%>

<div class="container test" >
		<div class="col-lg-7" >
			<div class="jumbotron" style="padding-top: 20px;">
				<h3 style="text-align: center;" class="mb-4">정보수정</h3>
				
				<!-- form -->
				
			<form method="post" action="./updateUserForId.be">
					<!-- 경로 수정 -->
					<div class="form-group">
						<div>
							<span>아이디</span>
							<input class="form-control mb-2" name="user_id" value =<%=user.getUser_id() %> size = 30 readonly>
							
						</div>
					</div>

					
		
					<!-- 비밀번호 -->
					<div class="form-group">
						<span>비밀번호</span>
						<input class="form-control mb-2"  type="password" name="user_passwd" size = 30 value =<%=user.getUser_passwd() %>  required id="user_pw" oninput="pw_chk()" placeholder="비밀번호" >
					</div>

					<div class="form-group has-danger">
						<span>비밀번호 재확인</span>
						<input class="form-control mb-2" type="password" name="user_passwordchk"  size = 30 required placeholder="비밀번호 재확인" id ="user_repw" oninput="pw_chk()">
						<span id="pw_chk"></span>
					</div>
					
					<div class="form-group">
						<span>이름</span>
						<input class="form-control mb-2" type="text" name="user_name" size = 30 value =<%=user.getUser_name() %> required readonly >					
					</div>
						
						
					<div class="form-group">
						<span>이메일</span>
						<input class="form-control mb-2" type="email"  name="user_email" size = 30 value =<%=user.getUser_email()%>  required readonly >
						
					</div>

					<div class="form-group">
						<span>휴대폰</span>
						<input  class="form-control mb-2"  type="number" name="user_phone" size = 30 value =<%=user.getUser_phone()%> required readonly>
						
					</div>
					
					
					
					<div class="form-group">
						<input  class="form-control mb-2" type="text" id="user_address" name="user_address" readonly required placeholder="주소 ">
						
					</div>
					<div class="form-group">
						<input  class="form-control mb-2" type="text" name="user_addressdetail" required placeholder="상세주소 ">	
					</div>
					<%
					if(session.getAttribute("user_class").equals("admin")){
					%>
					<div class="form-group">
						<span>등급</span>
						<select name = "user_class" class = "form-control mb-2">
							<option value = "user" selected>일반사용자</option>
							<option value = "admin">관리자</option>
						</select>
					</div>
					<%
						}else{
					%>
						<div class="form-group">
						<span>등급</span>
						<input  class="form-control mb-2"  type="text" name="user_phone" size = 30 value =<%=user.getUser_class()%> required readonly>
						
					</div>
					<%
						}
					%>
					<input type="hidden" name = "user_num" value="<%=user.getUser_num()%>">
						<input type="hidden" name = "user_RRN" value="<%=user.getUser_RRN()%>">
					<input class="btn btn-success float-right" style="margin-left: 10px;" type="submit"  value="수정">
						
				</form>
			</div>
		</div>
	<!-- 	<div class="col-lg-4"></div> -->
	</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
window.onload = function(){
    document.getElementById("user_address").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("user_address").value = data.address; // 주소 넣기
                document.querySelector("input[name=user_addressdetail]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>

</body>
<%@ include file= "/footer.jsp" %>
</html>