<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file= "/header.jsp" %>

</head>
<body>
<!-- <form id="form" method="post" action="./insertBoardgame.be" enctype="multipart/form-data">

	<br>상품 코드 : <input type="text" id="b_code" name="b_code" size = 30  required>
	<br><span id="codechk"></span>
	
	<br>상품 종류: <input type="text" name="" size = 30 required id="b_category">
	<br>상품 종류: <select name = "b_category" id = "b_category">
				<option value = "strategy" selected>전략</option>
				<option value = "party">파티</option>
				<option value = "new">신상</option>
				<option value = "school">교구</option>
				<option value = "first">선 주문</option>
				<option value = "best">베스트</option>
			</select>
	
	<br>상품 이름 : <input type="text" name="b_name"  size = 30 required id ="b_name" >
	<br><span id="name_chk"></span>
	
	<br>상품 가격 : <input type="number"name="b_price" size = 30 required placeholder="원 단위">
	<br>
	
	<br>상품 적정연령: <input type="number" name="b_age" size = 30  maxlength="14" required >
	 <select name = "b_agestate" id = "b_agestate">
				<option value = "이상" selected>세 이상 사용</option>
				<option value = "이하">세 이하 사용</option>
				<option value = "미만">세 미만 사용금지</option>
			
			</select>
	<br><span id=age_chk></span>
	
	<br>상품 플레이어 : <input type="number"  name="b_player1" size = 30 id ="b_player1">~<input type="number"  name="b_player2" size = 30 id ="b_player2">
	
	<br>상품 플레이시간 : <input type="number"  name="b_time1" size = 30 id ="b_time1" >~<input type="number"  name="b_time2" size = 30 id ="b_time2">
	<br><span id=time_chk></span>
	
	<br>상품 설명 : <textarea rows="10" cols="100" name = "b_content" maxlength = 500 id = "b_content" placeholder="500자 이상 사용금지"></textarea>
	textarea로 글자수 제한 넣기
	
	<br>상품 배송비 : <input type="number" name="b_charge" size = 30 value="0">
	<br><span id=charge_chk></span>
	
	<br>상품 기본사진 : <input type ="file" name ="b_image">
	
	<br><input type="submit"  value="상품등록">
</form> -->

<div class="container center" >
	
		<div class="col-lg-7 center" >
			<div class="jumbotron" style="padding-top: 20px;">
				<h3 style="text-align: center;" class="mb-4">회원가입</h3>
				
				<!-- form -->
				
			<form id="form" method="post" action="./insertBoardgame.be" enctype="multipart/form-data">
					<!-- 경로 수정 -->
					<div class="form-group">
						<div>
							<span>상품 코드</span>
							<input class="form-control mb-2"  type="text" id="b_code" name="b_code" size = 30  required placeholder="상품 코드">
							<span id="codechk"></span>
						</div>
					</div>

					<div class="form-group">
						<span>상품 종류</span>
						<select class="form-control mb-2"  name = "b_category" id = "b_category">
							<option value = "strategy" selected>전략</option>
							<option value = "party">파티</option>
							<option value = "new">신상</option>
							<option value = "school">교구</option>
							<option value = "first">선 주문</option>
							<option value = "best">베스트</option>
						</select>
					
					</div>
		
					<!-- 비밀번호 -->
					<div class="form-group">
				
						<span>상품 이름</span>
						<input class="form-control mb-2"  type="text" name="b_name"  size = 30 required id ="b_name" placeholder="상품 이름" >
						<span id="name_chk"></span>
					</div>

					<div class="form-group has-danger">
						<span>상품 가격</span>
						<input class="form-control mb-2" type="number"name="b_price" size = 30 required placeholder="원 단위">
					
					</div>
						
						
					<div class="form-group">
			
							<span>상품 적정연령</span><br>
							 <input class="col-md-6 mb-2"type="number" name="b_age" size = 30  maxlength="14" required >
							 <select class="col-md-5 mb-2" name = "b_agestate" id = "b_agestate">
										<option value = "이상" selected>세 이상 사용</option>
										<option value = "이하">세 이하 사용</option>
										<option value = "미만">세 미만 사용금지</option>
									
									</select>
							<span id=age_chk></span>
				
					</div>

					<div class="form-group">
					
						<span>상품 플레이어</span><br>
						<input class="col-md-5 mb-2" type="number"  name="b_player1" size = 30 id ="b_player1">~<input  class="col-md-5 mb-2" type="number"  name="b_player2" size = 30 id ="b_player2">
				
					</div>
					
					<div class="form-group">
					
						<span>상품 플레이시간</span><br>
						<input class="col-md-5 mb-2" type="number"  name="b_time1" size = 30 id ="b_time1">~<input  class="col-md-5 mb-2" type="number"  name="b_time2" size = 30 id ="b_time2">
				
					</div>
					
					<div class="form-group">
	
						<span>상품 설명 </span><br>
						 <textarea class="form-control mb-2"  rows="10" cols="100" name = "b_content" maxlength = 500 id = "b_content" placeholder="500자 이상 사용금지"></textarea>
						
					</div>
					<div class="form-group">
						<span>상품 배송비</span>
						 <input class="form-control mb-2" type="number" name="b_charge" size = 30 value="0">
						<span id=charge_chk></span>
					
					</div>
						<span>상품 기본사진</span>
						<input type ="file" name ="b_image">
					<input class="btn btn-success float-right" style="margin-left: 10px;" type="submit"  value="상품 등록">
						
				</form>
			</div>
		</div>
	<!-- 	<div class="col-lg-4"></div> -->
	</div>



</body>
<%@ include file= "/footer.jsp" %>
</html>