<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/globalData.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <h1>마이 쇼핑몰(rwk51) ver0.1</h1>

<hr>
<!-- <h2>맥주샵 시스템 - CRUD with MVC</h2>  -->
<h2>맥주샵 시스템 - CRUD with MVC #4</h2>

 <h2>MVC페턴 ,DBCP,서블릿 적용, DAO,DTO,VO활용 (스프링 미적용)</h2> 
<h3>JNDI,DBCP 구성 및 활용</h3>
</head>
<body>
<%session.setAttribute("ViewState","index"); %>
<ul>
<li><a href="<%= beerViewDir%>/beer_r_drc.jsp">출력레코드 갯수조정(R_DRC)</a>
<li><a href="<%= beerViewDir%>/beer_c.jsp">맥주 정보입력(C)</a>

<li><a href="./getBeerList.be">맥주정보조회(R,페이징기능X)</a>
<li><a href="./getBeerListForPage.be">맥주정보조회(R4, MVC패턴적용)</a>
<li><a href="./updateBeerListDisplay.be">맥주정보수정(U,MVC패턴적용)</a>
<li><a href="./deleteBeerListDisplay.be">맥주정보삭제(D,MVC패턴적용)</a>
</ul>

<p>
<br>전체 흐름 컨트롤을 위한 BeerController.jsp 파일 구성
<br>객체 데이터의 전달을 위한 BeerDTO 빈(Bean)객체 생성 및 활용
<br>데이터베이스 연결 및 처리를 위한 BeerDAO 클래스 구현
<br>DB에서 조회된 데이터를 BeeroDTO객체 list로 뷰에 전달
<br>페이징 처리를 위한 BeerPageInfoVO 객체 생성 및 활용
<br>프로젝트 디렉터리 : MVC패턴 편성 구조로 변경

</body>
</html>