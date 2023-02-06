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
<title>마이쇼핑몰(rwk51) - 맥주자료조회(R5,R51)</title>
</head>
<body>
<h1>마이쇼핑몰(rwk51) - 맥주자료조회(R5, R51)</h1>
<hr>
<h2>전체보기,부분조회,페이징기능 포함</h2>
<%
	BeerVO beerVO;
	ArrayList<BeerVO>beerVOList;
	BeerImagePageInfoVO beerImagePageInfoVO;
	
	beerImagePageInfoVO = (BeerImagePageInfoVO)session.getAttribute("beerImagePageInfoVO");
	beerVOList = (ArrayList<BeerVO>)request.getAttribute("beerVOList");
	
	int currentPageNo =  beerImagePageInfoVO.getCurrentPageNo();
	System.out.println("beer_r5페이지입니다"+beerImagePageInfoVO.getLimitCnt() );
	int limitCnt;
	if(beerImagePageInfoVO == null) limitCnt = 10;
	else limitCnt = beerImagePageInfoVO.getLimitCnt();
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
				<th>이미지 순번</th>
				<th>이미지 이름</th>
				<th>이미지 원본이름</th>
				<th>이미지 썸네일이름</th>
				<th>이미지 크기</th>
				<th>이미지 외래키</th>
			</tr>
		</thread>
		<tbody>
		<%
		for(int i = 0 ; i<beerVOList.size(); i++){
			beerVO = beerVOList.get(i);	
		%>
		<tr>
			<td><%=beerVO.getB_id() %></td>
			<td><%=beerVO.getB_code() %></td>
			<td><%=beerVO.getB_category() %></td>
			<td><%=beerVO.getB_name() %></td>
			<td><%=beerVO.getB_country() %></td>
			<td><%=beerVO.getB_price() %></td>
			<td><%=beerVO.getB_alcohol() %></td>
			<td><%=beerVO.getB_content() %></td>
			<td><%=beerVO.getB_like() %></td>
			<td><%=beerVO.getB_dislike() %></td>
			<td><%=beerVO.getB_image() %></td>
			
			<td><%=beerVO.getI_id() %></td>
			<td><%=beerVO.getI_file_name() %></td>
			<td><img src="<%=originalImg %><%=beerVO.getI_original_name() %>"width="150"/></td>
			<td><img src="<%=thumbImg %><%=beerVO.getI_thumbnail_name() %>"width="150"/></td> 
			<%-- <td><%=beerVO.getI_original_name() %></td>
			<td><%=beerVO.getI_thumbnail_name() %></td> --%>
			<td><%=beerVO.getI_file_size() %></td>
			<td><%=beerVO.getI_f_id() %></td>
		</tr>
		<%
		}
		%>
		</tbody>
	</table>
	
	<a href="./BeerMultiController.be2?actionType=R5&currentPageNo=0">[FIRST]</a>
	<%      
   
   if(currentPageNo > 0 ){
%>
<a href="./BeerMultiController.be2?actionType=R5&currentPageNo=<%=(currentPageNo-1) %>" >[PIPRE]</a>

<%
   }
   else{
     
%>
   [PRE]
<%
   }
   for(int i = beerImagePageInfoVO.getStartPageNo() ; i<beerImagePageInfoVO.getEndPageNo(); i++){
	   
      if(i == currentPageNo){
%>
 [<%=(i+1) %>]
 <%
      }else{
 %>
    <a href="./BeerMultiController.be2?actionType=R5&currentPageNo=<%=(i) %>">[<%=(i+1) %>]</a>
 <%
      }
   }
   
   %>
<%
   if(currentPageNo < beerImagePageInfoVO.getPageCnt()- 1){
 %>
 <a href="./BeerMultiController.be2?actionType=R5&currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a>
 <%
   }else{
 %>
 [NXT]
 <%
   }
   
 %>
 <a href="./BeerMultiController.be2?actionType=R5&currentPageNo=<%=(beerImagePageInfoVO.getPageCnt()-1)%>">END</a>
	<br><a href="<%=rootDir %>/index.jsp">홈으로 돌아가기</a>
</body>
</html>