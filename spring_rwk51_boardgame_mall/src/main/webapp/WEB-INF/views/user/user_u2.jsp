<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="com.boardgame.mall.entity.* "%>
     
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
<%@ include file= "../header.jsp" %>
</head>

<body>
<div class="container test" >
		<div class="col-lg-7" >
			<div class="jumbotron" style="padding-top: 20px;">
				<h3 style="text-align: center;" class="mb-4">정보수정</h3>
				<form method="post" action="/user/UpdateUserForId">
					<!-- 경로 수정 -->
					<div class="form-group">
						<div>
							<span>아이디</span>
							<input class="form-control mb-2" name="user_id" value="${user.user_id}" size="30" readonly>
						</div>
					</div>
						<!-- 비밀번호 -->
					<div class="form-group">
						<span>비밀번호</span>
						<input class="form-control mb-2" type="password" name="user_passwd" size="30" value="${user.user_passwd}" required id="user_pw" oninput="pw_chk()" placeholder="비밀번호">
					</div>
					
					<div class="form-group has-danger">
						<span>비밀번호 재확인</span>
						<input class="form-control mb-2" type="password" name="user_passwordchk" size="30" required placeholder="비밀번호 재확인" id="user_repw" oninput="pw_chk()">
						<span id="pw_chk"></span>
					</div>
					
					<div class="form-group">
						<span>이름</span>
						<input class="form-control mb-2" type="text" name="user_name" size="30" value="${user.user_name}" required readonly>
					</div>
					
					<div class="form-group">
						<span>이메일</span>
						<input class="form-control mb-2" type="email" name="user_email" size="30" value="${user.user_email}" required readonly>
					</div>
					
					<div class="form-group">
						<span>휴대폰</span>
						<input class="form-control mb-2" type="number" name="user_phone" size="30" value="${user.user_phone}" required readonly>
					</div>
					
					<div class="form-group">
						<input class="form-control mb-2" type="text" id="user_address" name="user_address" readonly required placeholder="주소 " value="${user.user_address}">
					</div>
					
					<div class="form-group">
						<input class="form-control mb-2" type="text" name="user_addressdetail" required placeholder="상세주소 ">
					</div>
					
					<c:if test="${sessionScope.user_class eq 'admin'}">
						<div class="form-group">
							<span>등급</span>
							<select name="user_class" class="form-control mb-2">
								<option value="user" <c:if test="${user.user_class eq 'user'}">selected</c:if>>일반사용자</option>
								<option value="admin" <c:if test="${user.user_class eq 'admin'}">selected</c:if>>관리자</option>
							</select>
						</div>
					</c:if>
					
					<c:if test="${sessionScope.user_class ne 'admin'}">
						<div class="form-group">
							<span>등급</span>
							<input class="form-control mb-2" type="text" name="user_phone" size="30" value="${user.user_class}" required readonly>
						</div>
					</c:if>
					<input type="hidden" name="user_num" value="${user.user_num}" />
					<input type="hidden" name="user_RRN" value="${user.user_RRN}" />
					<input class="btn btn-success float-right" style="margin-left:10px;" type="submit" value="수정하기" />
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
<%@ include file= "../footer.jsp" %>
</html>