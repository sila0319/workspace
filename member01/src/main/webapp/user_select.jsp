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
<% session.setAttribute("viewState", "select"); %>
<%@ include file="./header.jsp" %>
<h1>관리자 모드 - 사용자 조회</h1>
<hr>

<%
   String driverName = "org.mariadb.jdbc.Driver";
   String url = "jdbc:mariadb://localhost/member_db";
   String user = "root";
   String passwd = "maria";
   
   Class.forName(driverName);
   Connection con = DriverManager.getConnection(url, user, passwd);
   PreparedStatement pstmt = null;
   request.setCharacterEncoding("UTF-8"); 
%>

<%
	//user라는 클래스를 지닌 사람의 수만 조회한다 그외의 등급은 안함 사용자 중 등급을 나눴더라면 그사람들 조건 추가하기 (관리자 미포함)
   String sql2 = "SELECT count(*) FROM member where mem_class = 'user' OR mem_class = 'block'" ;
   pstmt = con.prepareStatement(sql2);
   ResultSet rs2= pstmt.executeQuery();
   
   int recordCnt =0;
   int pageCnt;
   int limitCnt = 10; /** limitCnt는 페이지를 제한하는 변수다.*/
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



<%
   String mem_name;
	String mem_id;
	String mem_passwd;
	String mem_email;
	String mem_phone;
	String mem_class;
	
	//user만 조회할 수 있도록 한다. user중 등급을 추가한다면 조건을 추가하여서 생성
   String sql = "SELECT mem_num, mem_name, mem_id,mem_passwd,mem_email,mem_phone,mem_class FROM member where mem_class = 'user' OR mem_class ='block' ORDER BY mem_num limit ?,?";
   pstmt = con.prepareStatement(sql);
   pstmt.setInt(1,startRecord);
   pstmt.setInt(2,limitCnt);
   ResultSet rs = pstmt.executeQuery();
%>

   <table border="1">
      <thread>
         <tr>
            <th>이름</th>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>이메일</th>
            <th>휴대폰</th>
            <th>등급</th>
         </tr>
      </thread>
   <tbody>
   <%
      while(rs.next()){
    	   mem_name = rs.getString("mem_name");
    	   mem_id = rs.getString("mem_id");
    	   mem_passwd = rs.getString("mem_passwd");
    	   mem_email = rs.getString("mem_email");
    	   mem_phone = rs.getString("mem_phone");
    	   mem_class = rs.getString("mem_class");
   %>
      <tr>
         <td><%=mem_name %></td>
         <td><%=mem_id %></td>
         <td><%=mem_passwd %></td>
         <td><%=mem_email %></td>
         <td><%=mem_phone  %></td>
         <td><%=mem_class %></td>
      </tr>
         
   <%
      }
   %>
   </tbody>
   </table>
<br>
<a href="./user_select.jsp?currentPageNo=0">[FIRST]</a>
<%      
   
   if(currentPageNo > 0 ){
%>
<a href="./user_select.jsp?currentPageNo=<%=(currentPageNo-1) %>" >[PRE]</a>

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
    <a href="./user_select.jsp?currentPageNo=<%=i %>">[<%=(i+1) %>]</a>
 <%
      }
   }
   
   %>
<%
   if(currentPageNo < pageCnt - 1){
 %>
 <a href="./user_select.jsp?currentPageNo=<%=(currentPageNo+1)%>">[NXT]</a>
 <%
   }else{
 %>
 [NXT]
 <%
   }
   pstmt.close();
   con.close();
 %>
 <a href="./user_select.jsp?currentPageNo=<%=(pageCnt-1) %>">[END]</a>
 
 <br>
 <%@ include file="./footer.jsp" %> 
</body>
</html>