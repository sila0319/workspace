<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>서적 관리 시스템 - 조회(R3)</h1>
<hr>
<h2>전체보기(부분조회 미포함, 페이징기능 개선, 출력 레코드 갯수 선택, 세션 객체 사용안함)</h2>
<hr>
<%
   String driverName = "org.mariadb.jdbc.Driver";
   String url = "jdbc:mariadb://localhost/book_db";
   String user = "root";
   String passwd = "maria";
   
   Class.forName(driverName);
   Connection con = DriverManager.getConnection(url, user, passwd);
   PreparedStatement pstmt = null;
   request.setCharacterEncoding("UTF-8"); 
%>

<%
   String sql2 = "SELECT count(*) FROM books ";
   pstmt = con.prepareStatement(sql2);
   ResultSet rs2= pstmt.executeQuery();
   
   int recordCnt =0;
   int pageCnt;
   int limitCnt = Integer.parseInt(request.getParameter("limitCnt")); /** limitCnt는 페이지를 제한하는 변수다.*/
   int startRecord =0;
   int currentPageNo;
   
   /** currentPageNo는 pageCnt에 의해서 정해진 페이지의 갯수를 뜻한다.*/
   currentPageNo = Integer.parseInt(request.getParameter("currentPageNo"));
   //index.jsp에서 currentPageNo를 넘겨주는걸 getParameter로 받는다. 
   /**startRecord는 테이블의 데이터 갯수*/
   startRecord = currentPageNo * limitCnt;
   //request의 요청을 받으면 response를 하게되는데 response는 html문서로 저장되어 있다.
   
   
   while(rs2.next()){
      recordCnt = rs2.getInt(1);
   }
   
   pageCnt = recordCnt/limitCnt; //페이지를 10으로 나누었을때 딱 떨어지는 값만큼 페이지를 증가.
   
   //페이지를 10으로 나눈후 남은 몫이 존재할수 있으므로 그 값이 0이아닐 경우 pageCnt 1증가
   if(recordCnt%limitCnt !=0){
      pageCnt++;
   }
   
%>
<h2>현재 DISPLAY RECORDS NUMBEER : <%=limitCnt %>   </h2>
<hr>
<div> 
      <form method="post" action="./book_r3.jsp" >
       디스플레이 레코드 수 변경 : 
      <select name = 'limitCnt'>
         <option value = 10 selected>10</option>
         <option value = 20 >20</option>
         <option value = 30 >30</option>
         <option value = 40 >40</option>
         <option value = 50 >50</option>
      </select>
      
      <input type = "hidden" name = "currentPageNo"  value =0>
      <input type ="submit" value = "확인">
   </form>
   
</div>


<%
   int book_id;
   String title;
   String publisher;
   String year;
   int price;
   
   
   String sql = "SELECT * FROM books ORDER BY book_id limit ?,?";
   pstmt = con.prepareStatement(sql);
   pstmt.setInt(1,startRecord);
   pstmt.setInt(2,limitCnt);
   ResultSet rs = pstmt.executeQuery();
%>

   <table border="1">
      <thread>
         <tr>
            <th>순번</th>
            <th>제목</th>
            <th>출판사</th>
            <th>출판년도</th>
            <th>가격</th>
         </tr>
      </thread>
   <tbody>
   <%
      while(rs.next()){
         book_id = rs.getInt("book_id");
         title = rs.getString("title");
         publisher = rs.getString("publisher");
         year = rs.getString("year");
         price = rs.getInt("price");   
   %>
      <tr>
         <td><%=book_id %></td>
         <td><%=title %></td>
         <td><%=publisher %>
         <td><%=year  %></td>
         <td><%=price %></td>
      </tr>
         
   <%
      }
   %>
   </tbody>
   </table>
<br>
<a href="./book_r3.jsp?currentPageNo=0&limitCnt=<%=limitCnt%>">[FIRST]</a>
<%      
   
   if(currentPageNo > 0 ){
%>
<a href="./book_r3.jsp?currentPageNo=<%=(currentPageNo-1) %>&limitCnt=<%=limitCnt%>" >[PRE]</a>

<%
   }
   else{
     
%>
   [PRE]
<%
   }
   for(int i = currentPageNo/10*10 ; i<( currentPageNo/10+1)*10; i++){
	   if(i>pageCnt-1) break;
      if(i == currentPageNo){
%>
 [<%=(i+1) %>]
 <%
      }else{
 %>
    <a href="./book_r3.jsp?currentPageNo=<%=i %>&limitCnt=<%=limitCnt%>">[<%=(i+1) %>]</a>
 <%
      }
   }
   
   %>
<%
   if(currentPageNo < pageCnt - 1){
 %>
 <a href="./book_r3.jsp?currentPageNo=<%=(currentPageNo+1)%>&limitCnt=<%=limitCnt%>">[NXT]</a>
 <%
   }else{
 %>
 [NXT]
 <%
   }
   pstmt.close();
   con.close();
 %>
 <a href="./book_r3.jsp?currentPageNo=<%=(pageCnt-1) %>&limitCnt=<%=limitCnt%>">[END]</a>
 <br><br><a href="./index.jsp">홈으로 돌아가기</a>
</body>
</html>