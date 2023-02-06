<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import ="rwk51_boardgame_mall.model.boardgame.*" %>
    
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
	BoardgameDTO boardgame;
	ArrayList<BoardgameDTO>boardgameList;
	BoardgamePageInfoVO bpiVO;
	
	bpiVO = (BoardgamePageInfoVO)session.getAttribute("boardgamePageInfoVO");
	boardgameList = (ArrayList<BoardgameDTO>)request.getAttribute("boardgameList");
	
	int currentPageNo = bpiVO.getCurrentPageNo();
	System.out.println("boardgame_s페이지입니다"+bpiVO.getLimitCnt() );
	
	String search = (String)request.getAttribute("search");
	String b_category = (String)request.getAttribute("b_category");
	System.out.println("값체크 " + search+" "+b_category);
	

%>
<div>
		
		<%
		for(int i = 0 ; i<boardgameList.size(); i++){
			boardgame = boardgameList.get(i);	
		%>
		<div><a href="./boardgameInfo.be?b_id=<%=boardgame.getB_id()%>">
			<img src ="<%=oriDir%>/<%=boardgame.getB_image()%>"/>
			<p>이름 : <%=boardgame.getB_name() %></p>
			<p>가격 : <%=boardgame.getB_price() %>원</p>
			<p>적정 나이 : <%=boardgame.getB_age() %><%=boardgame.getB_agestate() %></p>
			<p>시간 : <%=boardgame.getB_time() %>분</p>
			<p>적정 인원 : <%=boardgame.getB_player() %>명</p>
			<p>카테고리 : <%=boardgame.getB_category() %></p>
			<p>배송비 : <%=boardgame.getB_charge() %>원</p>
			
		</a></div>	
			<%
			if(session.getAttribute("user_class")!=null&&session.getAttribute("user_class").equals("admin")){
			%>
				<a  href="./updateBoardgameDisplay.be?b_id=<%=boardgame.getB_id()%>">상품 수정</a>
				<a onclick="return b_deletechk()" href="./deleteBoardgameForId.be?category=<%=boardgame.getB_category()%>&b_id=<%=boardgame.getB_id()%>">상품 삭제</a>
				<br>
			<%
			}
			%>
			<br>
		<%
		}
		%>
		
	</div>
	<a href="./searchBoardgame.be?currentPageNo=0&search=<%=search %>&b_category=<%=b_category%>">[FIRST]</a>
	<%      
   
   if(currentPageNo > 0 ){
%>
 <a href="./searchBoardgame.be?currentPageNo=<%=(currentPageNo-1) %>&search=<%=search %>&b_category=<%=b_category%> ">[PRE]</a>

<%
   }
   else{
     
%>
   [PRE]
<%
   }
   for(int i = bpiVO.getStartPageNo() ; i<bpiVO.getEndPageNo(); i++){
	   
      if(i == currentPageNo){
%>
 [<%=(i+1) %>]
 <%
      }else{
 %>
    <a href="./searchBoardgame.be?currentPageNo=<%=(i) %>&search=<%=search %>&b_category=<%=b_category%>">[<%=(i+1) %>]</a>
 <%
      }
   }
   
   %>
<%
   if(currentPageNo < bpiVO.getPageCnt()- 1){
 %>
 <a href="./searchBoardgame.be?currentPageNo=<%=(currentPageNo+1)%>&search=<%=search %>&b_category=<%=b_category%>">[NXT]</a>
 <%
   }else{
 %>
 [NXT]
 <%
   }
 %>
  <a href="./searchBoardgame.be?currentPageNo=<%=(bpiVO.getPageCnt()-1)%>&search=<%=search %>&b_category=<%=b_category%>">END</a>
	
	
<%@ include file= "/footer.jsp" %>

</body>
</html>