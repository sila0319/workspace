<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%@ page import="java.sql.*" %>
    <%@ page import ="rwk51_boardgame_mall.model.boardgame.*" %>
    
    <%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.test {
	display: flex;
	justify-content: center;
}



</style>
<%@ include file= "/header.jsp" %>
<body>
<%
	BoardgameDTO boardgame;
	ArrayList<BoardgameDTO>boardgameList;
	boardgameList = (ArrayList<BoardgameDTO>)request.getAttribute("boardgameList");
%>
<!-- <div style="text-align:center;"> -->

	<div style="margin:0 auto" align="center" > 
		<div style="margin-left: 10px;">
		
			<%
			for(int i = 0 ; i<boardgameList.size(); i++){
				boardgame = boardgameList.get(i);	
			%>
				<div class= "test">
					<img src ="<%=oriDir%>/<%=boardgame.getB_image()%>"/>
				
				<div class = "column">
						<p>이름 : <%=boardgame.getB_name() %></p>
						<p>가격 : <%=boardgame.getB_price() %>원</p>
						<p>적정 나이 : <%=boardgame.getB_age() %>세 <%=boardgame.getB_agestate() %></p>
						<p>시간 : <%=boardgame.getB_time() %>분</p>
						<p>적정 인원 : <%=boardgame.getB_player() %>명</p>
						<p>카테고리 : <%=boardgame.getB_category() %></p>
						<p>배송비 : <%=boardgame.getB_charge() %>원</p>
						<input type=hidden id="num" value = "<%=session.getAttribute("user_num")%>">
						<input type=hidden id="boardgame_id" value = "<%=boardgame.getB_id() %>">
					</div>
				</div>
		</div> 
		<br>
		
			
		<%
			if(session.getAttribute("user_class")==null){
		%>
			<a onclick="return l_chk()" href="">상품 구매</a>
			<a onclick="return l_chk()" href="">장바구니 담기</a>
		<%
			
			}else{
		%>
			
		
	<!-- 		<a onclick="return b_buychk()" href="">상품 구매</a> -->
			<!-- <input type="button" onclick="b_addchk()">상품 담기 -->
			
				<form class = "column" id="addcart_form" method="post" action = "./insertcart.be" onsubmit="return b_addchk()">
					<p>구매 수량 : <input type = "number" id="c_cnt" name = "c_cnt"  required></p>
					
					<input type="hidden" name="user_num" value = "<%=session.getAttribute("user_num")%>">
					<input type="hidden" name="b_id" value = "<%=boardgame.getB_id()%>">
					<input type="hidden" name="b_price" value = "<%=boardgame.getB_price()%>">
					<input type="hidden" name="b_charge" value = "<%=boardgame.getB_charge()%>">
					<input type="hidden" name="b_name" value = "<%=boardgame.getB_name()%>">
						
					<input class="btn btn-success float-right" style="margin-right: 300px;"  
					type="submit" value = "장바구니 담기">
					
				</form>
				<form 
				id="addcart_form" method="post" action = "#" onsubmit="return b_buychk()">		
					<input class="btn btn-dark float-right" type="submit" value = "구매하기">
				</form>
			
			
			
		 	<%-- <a onclick="return b_addchk()" href="./insertcart.be?user_num=<%=session.getAttribute("user_num")%>&b_id=<%=boardgame.getB_id()%>&b_price=<%=boardgame.getB_price()%>
			&b_charge=<%=boardgame.getB_charge()%>&b_name=<%=boardgame.getB_name()%>&c_cnt=${'#c_cnt'}.value">장바구니 담기</a> --%>
		<%
			}
		%>
		
		
		
		<%
			if(session.getAttribute("user_class")!=null&&session.getAttribute("user_class").equals("admin")){
			%>
				<br>
				<br>
				<a  href="./updateBoardgameDisplay.be?b_id=<%=boardgame.getB_id()%>">상품 수정</a>
				<a onclick="return b_deletechk()" href="./deleteBoardgameForId.be?category=<%=boardgame.getB_category()%>&b_id=<%=boardgame.getB_id()%>">상품 삭제</a>
				<br>
			<%
			}
			%>
			<br>
			<p>상품 설명 <br><%=boardgame.getB_content() %></p>
			<br>
		<%
		}
		%>
		</div>
<!-- 	</div> -->
	<%@ include file= "/footer.jsp" %>
	<script type="text/javascript">
	function b_addchk(){
		var success = false;
		var user_num = document.querySelector('#num').value;
		var b_id = document.querySelector('#boardgame_id').value;
	
		var actionType = "cart_chk";
	
		console.log("실행되었다.");
		console.log(user_num, b_id);
		
		
			if(window.confirm("장바구니에 담겠습니까?")){
				if(document.querySelector('#c_cnt').value<1){
					alert("물품 갯수를 확인해주세요.")
				}else{
					success = cart_chk(actionType, b_id, user_num);
					if(success ==false){
						alert("장바구니에 이미 담겨있습니다.");
					}
				}
				
			
			}
		console.log(document.querySelector('#c_cnt').value);
		console.log(success);
		return success;
	}

	function cart_chk(actionType , b_id, user_num){
		var success_data = false;
		$.ajax({
			url:'/rwk51_boardgame_mall/JS/cart/cart.jsp' ,
			type:'post',
			async: false,
			dataType: "json",
			data:{actionType: actionType, b_id: b_id , user_num : user_num},
			success:function(data){
				success_data = data.checkResult;
			
				console.log(data);
				console.log(success_data);
						
			},
		});	
		return success_data;
	}

	</script>
</body>
</html>