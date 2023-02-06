<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	margin : 15px;
}
</style>
<%@ include file= "/header.jsp" %>

</head>
<body>
<!-- <form id="form" method="post" action="./insertUser.be" onsubmit="return chkform()">

	<br>유저아이디 : <input type="text" id="user_id" name="user_id" size = 30 oninput="id_chk()" required>
	<br><span id="id_chk"></span>
	
	<br>유저 비밀번호 : <input type="password" name="user_passwd" size = 30 required id="user_pw" oninput="pw_chk()">
	<br>유저 비밀번호 재확인 : <input type="password" name="user_passwordchk"  size = 30 required id ="user_repw" oninput="pw_chk()">
	<br><span id="pw_chk"></span>
	
	<br>유저 이름 : <input type="text" name="user_name" size = 30 required>
	<br>
	
	<br>유저 주민등록번호 : <input type="number" name="user_RRN" size = 30  maxlength="14" required oninput ="RRN_chk(this)">
	<br><span id=RRN_chk></span>
	
	<br>유저 이메일 : <input type="email"  name="user_email" size = 30 id ="user_email" oninput="email_chk()" >
	<br><span id=email_chk></span>
	<br>유저 휴대폰 : <input type="number" name="user_phone" size = 30 oninput="phone_chk(this)">
	<br><span id=phone_chk></span>
	
	<br>유저 주소 : <input type="text" id="user_address" name="address" readonly required/>
	<br>
	
	<br>유저 상세주소: <input type="text" name="user_addressdetail" required />
	<br>
	
	<br><input type="submit"  value="회원가입">
</form> -->
<div class="container test" >
		
		<div class="col-lg-7" >
			<div class="jumbotron" style="padding-top: 20px;">
				<h3 style="text-align: center;" class="mb-4">회원가입</h3>
				
				<!-- form -->
				
			<form id="form" method="post" action="./insertUser.be" onsubmit="return chkform()">
					<!-- 경로 수정 -->
					<div class="form-group">
						<div>
							<input class="form-control mb-2" type="text" name="user_name" size = 30 required placeholder="이름" >
							
						</div>
					</div>

					<div class="form-group">
						<input class="form-control mb-2" type="text" id="user_id" name="user_id" size = 30 oninput="id_chk()" required placeholder="아이디">
						<span id="id_chk"></span>
					
					</div>
		
					<!-- 비밀번호 -->
					<div class="form-group">
						<input class="form-control mb-2"  type="password" name="user_passwd" size = 30 required id="user_pw" oninput="pw_chk()" placeholder="비밀번호" >
					</div>

					<div class="form-group has-danger">
						<input class="form-control mb-2" type="password" name="user_passwordchk"  size = 30 required placeholder="비밀번호 재확인" id ="user_repw" oninput="pw_chk()">
						<span id="pw_chk"></span>
					</div>
						
						
					<div class="form-group">
						<input class="form-control mb-2" type="email"  name="user_email" size = 30 id ="user_email" oninput="email_chk()" placeholder="이메일 @ ">
						<span id=email_chk></span>
					</div>

					<div class="form-group">
						<input  class="form-control mb-2"  type="number" name="user_phone" size = 30 oninput="phone_chk(this)" placeholder="핸드폰(숫자만 입력해주세요)">
						<span id=phone_chk></span>
					</div>
					
					<div class="form-group">
						<input  class="form-control mb-2" type="number" name="user_RRN" size = 30  maxlength="14" required oninput ="RRN_chk(this)" placeholder="주민번호13자리 ">
						<br><span id=RRN_chk></span>
					</div>
					
					<div class="form-group">
						<input  class="form-control mb-2" type="text" id="user_address" name="user_address" readonly required placeholder="주소 ">
						
					</div>
					<div class="form-group">
						<input  class="form-control mb-2" type="text" name="user_addressdetail" required placeholder="상세주소 ">	
					</div>
					
					<input class="btn btn-success float-right" style="margin-left: 10px;" type="submit"  value="회원가입">
						
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