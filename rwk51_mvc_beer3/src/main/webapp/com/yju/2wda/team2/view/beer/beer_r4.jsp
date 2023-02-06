<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
    <%@ page import ="rwk51_mvc_beer3.model.beer.*" %>
    <%@ page import ="java.util.*" %>
    <%@ include file="/globalData.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이쇼핑몰(rwk51) - 맥주자료조회(R4,R41)</title>
</head>
<body>
<h1>마이쇼핑몰(rwk51) - 맥주자료조회(R4, R41)</h1>
<hr>
<h2>전체보기 페이징기능 포함(부분조회,미포함)</h2>
<%
	BeerDTO beer;
	ArrayList<BeerDTO>beerList;
	BeerPageInfoVO bpiVO;
	
	bpiVO = (BeerPageInfoVO)session.getAttribute("beerPageInfoVO");
	beerList = (ArrayList<BeerDTO>)request.getAttribute("beerList");
	
	int currentPageNo = bpiVO.getCurrentPageNo();
	System.out.println("beer_r4페이지입니다"+bpiVO.getLimitCnt() );
	int limitCnt;
	if(bpiVO == null) limitCnt = 10;
	else limitCnt = bpiVO.getLimitCnt();
	System.out.println("beer_r_drc페이지 입니다."+limitCnt);
	
%>

<h2>현재 DISPLAY RECORDS NUMBER : <%= limitCnt %></h2>
<hr>
<br>
	<table border="1">
		<thread>
			<tr>
				<th>순번</th>
				<th>코드</th>
				<th>종류</th>
				<th>이름</th>
				<th>원산지</th>
				<th>가격</th>
				<th>알코올</th>
				<th>설명</th>
				<th>좋아요</th>
				<th>싫어요</th>
				<th>사진</th>
			</tr>
		</thread>
		<tbody>
		<%
		for(int i = 0 ; i<beerList.size(); i++){
			beer = beerList.get(i);	
		%>
		<tr>
			<td><%=beer.getB_id() %></td>
			<td><%=beer.getB_code() %></td>
			<td><%=beer.getB_category() %></td>
			<td><%=beer.getB_name() %></td>
			<td><%=beer.getB_country() %></td>
			<td><%=beer.getB_price() %></td>
			<td><%=beer.getB_alcohol() %></td>
			<td><%=beer.getB_content() %></td>
			<td><%=beer.getB_like() %></td>
			<td><%=beer.getB_dislike() %></td>
			<td><%=beer.getB_image() %></td>
		</tr>
		<%
		}
		%>
		</tbody>
	</table>
	
	<a href="./BeerController.be?actionType=R4&currentPageNo=0">[FIRST]</a>
	<%      
   
   if(currentPageNo > 0 ){
%>
<a href="./BeerController.be?actionType=R4&currentPageNo=<%=(currentPageNo-1) %>" >[PIPRE]</a>

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
    <a href="./BeerController.be?actionType=R4&currentPageNo=<%=(i) %>">[<%=(i+1) %>]</a>
 <%
      }
   }
   
   %>
<%
   if(currentPageNo < bpiVO.getPageCnt()- 1){
 %>
 <a href="./BeerController.be?actionType=R4&currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a>
 <%
   }else{
 %>
 [NXT]
 <%
   }
   
 %>
 <a href="./BeerController.be?actionType=R4&currentPageNo=<%=(bpiVO.getPageCnt()-1)%>">END</a>
	<br><a href="<%=rootDir %>/index.jsp">홈으로 돌아가기</a>
</body>
</html>