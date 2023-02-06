<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="rwk51_boardgame_mall.model.boardgame.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file= "/header.jsp" %>

</head>
<%
BoardgameDTO boardgame = (BoardgameDTO)request.getAttribute("boardgame");
int player1 = 0 ,player2 =0 ,time1 =0 ,time2 = 0;
if(boardgame.getB_time().contains("~")){
	String [] arr = boardgame.getB_time().split("~");
	time1 = Integer.parseInt(arr[0]);
	time2 = Integer.parseInt(arr[1]);
}else{
	
	time1 =time2= Integer.parseInt(boardgame.getB_time());
}

if(boardgame.getB_player().contains("~")){
	String [] arr = boardgame.getB_player().split("~");
	player1 = Integer.parseInt(arr[0]);
	player2 = Integer.parseInt(arr[1]);
}else{
	
	player1 = player2  = Integer.parseInt(boardgame.getB_player());
}
%>
<body>
<form id="form" method="post" action="./updateBoardgameForId.be?b_id=<%=boardgame.getB_id() %>" enctype="multipart/form-data">

	<br>상품 코드 : <input type="text" id="b_code" name="b_code" size = 30  required value="<%=boardgame.getB_code()%>">
	<br><span id="codechk"></span>
	
	<!-- <br>상품 종류: <input type="text" name="" size = 30 required id="b_category"> -->
	<br>상품 종류: <select name = "b_category" id = "b_category" value="<%=boardgame.getB_category()%>">
				<option value = "strategy" >전략</option>
				<option value = "party">파티</option>
				<option value = "new">신상</option>
				<option value = "school">교구</option>
				<option value = "first">선 주문</option>
				<option value = "best">베스트</option>
			</select>
	
	<br>상품 이름 : <input type="text" name="b_name"  size = 30 required id ="b_name" value="<%=boardgame.getB_name()%>">
	<br><span id="name_chk"></span>
	
	<br>상품 가격 : <input type="number"name="b_price" size = 30 required placeholder="원 단위" value="<%=boardgame.getB_price()%>">
	<br>
	
	<br>상품 적정연령: <input type="number" name="b_age" size = 30  maxlength="14" required value="<%=boardgame.getB_age()%>">
	 <select name = "b_agestate" id = "b_agestate">
				<option value = "이상" selected>세 이상 사용</option>
				<option value = "이하">세 이하 사용</option>
				<option value = "미만">세 미만 사용금지</option>
			
			</select>
	<br><span id=age_chk></span>
	
	<br>상품 플레이어 : <input type="number"  name="b_player1" size = 30 id ="b_player1" value="<%=player1%>" >~<input type="number"  name="b_player2" size = 30 id ="b_player2" value="<%=player2%>">명
	
	<br>상품 플레이시간 : <input type="number"  name="b_time1" size = 30 id ="b_time1" value="<%=time1%>">~<input type="number"  name="b_time2" size = 30 id ="b_time2"value="<%=time2%>">분
	<br><span id=time_chk></span>
	
	<br>상품 설명 : <textarea rows="100" cols="100" name = "b_content" id = "b_content" maxlength = 500 placeholder="500자 이상 사용금지" value="<%=boardgame.getB_content()%>"></textarea>
	<!-- textarea로 글자수 제한 넣기 -->
	
	<br>상품 배송비 : <input type="number" name="b_charge" size = 30 value="0"value="<%=boardgame.getB_charge()%>">
	<br><span id=charge_chk></span>
	
	<br>상품 기본사진 : <input type ="file" name ="b_image">
	
	<br><input type="submit"  value="상품등록">
	<input type="hidden" value= "<%=boardgame.getB_image() %>" name = b_image2>
	
</form>


</body>
<%@ include file= "/footer.jsp" %>
</html>