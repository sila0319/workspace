<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="./header_rwk51.jsp" %> 
<%
	Connection con = null;
	PreparedStatement pstmt = null;
	request.setCharacterEncoding("UTF-8");
	ResultSet rs = null;
	int result = 1;
	String sql = null;
	try {
		String driverName = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost/db_rwk51";
		String user = "root";
		String passwd = "maria";
		Class.forName(driverName);
		Class.forName(driverName);
		con = DriverManager.getConnection(url, user, passwd);
	} catch (Exception e) {
		e.printStackTrace();
		result =0;
	}
	if(result==1){
		System.out.println("드라이버 연결 성공");
	}else if(result==0) {
		System.out.println("드라이버 연결 실페");
		
	}
%>

<%
   String sql2 = "SELECT count(*) FROM rwk51 ";
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

<div> 
      <form method="post" action="./R_rwk51.jsp" >
      	조회 방법 선택 
      <select name = 'optionchk'>
         <option value = "all" selected>전체</option>
         <option value = "date" >날짜별</option>
         <option value = "heat" >발열현상</option>
      </select>
      
      <input type = "hidden" name = "currentPageNo"  value =0>
      <input type ="submit" value = "확인">
   </form>
   
</div>


<%
	int rwk51_id = 0;
	String rwk51_date = "";
	String rwk51_time = "";
	String rwk51_name = "";
	String rwk51_hp = "";
	String rwk51_chk = "";
	String rwk51_heatchk = "";
   String optionchk = request.getParameter("optionchk");
   System.out.println(optionchk+"선택사항입니다.");
   
   switch(optionchk){
   case "all":
	   	sql = "SELECT * FROM rwk51 limit ?,?";
	   break;
   case  "date":
	   sql = "SELECT * FROM rwk51 ORDER BY CAST(rwk51_data AS SIGNED) limit ?,?";
	   break;
   case "heat": 
	   sql = "SELECT * FROM rwk51 where rwk51_heatchk = 'o' limit ?,?";
	   
	   break;
   
   }
   pstmt = con.prepareStatement(sql);
   pstmt.setInt(1,startRecord);
   pstmt.setInt(2,limitCnt);
   rs = pstmt.executeQuery();
  
   
%>

   <table border="1">
      <thread>
         <tr>
         	<th>번호</th>
            <th>날짜</th>
            <th>방문시각</th>
            <th>성명</th>
            <th>전화번호</th>
            <th>개인정보수집 제공동의</th>
            <th>발열현상 유무</th>
            <th>수정</th>
         </tr>
      </thread>
   <tbody>
   <%
      while(rs.next()){
    	  rwk51_id = rs.getInt("rwk51_id");
    	  rwk51_date = rs.getString("rwk51_date");
			 rwk51_time = rs.getString("rwk51_time");
			 rwk51_name = rs.getString("rwk51_name");
			 rwk51_hp = rs.getString("rwk51_hp");
			 rwk51_chk = rs.getString("rwk51_chk");
			 rwk51_heatchk = rs.getString("rwk51_heatchk");
   %>
      <tr>
      	<td><%=rwk51_id %></td>
         <td><%=rwk51_date %></td>
         <td><%=rwk51_time %></td>
         <td><%=rwk51_name %>
         <td><%=rwk51_hp  %></td>
         <td><%=rwk51_chk %></td>
         <td><%=rwk51_heatchk %></td>
         <td>
				<a href="./U2_rwk51.jsp?rwk51_id=<%=rwk51_id %>">수정</a>
		</td>
      </tr>
         
   <%
      }
   %>
   </tbody>
   </table>
<br>
<a href="./U_rwk51.jsp?currentPageNo=0&optionchk=<%=optionchk%>">[FIRST]</a>
<%      
   
   if(currentPageNo > 0 ){
%>
<a href="./U_rwk51.jsp?currentPageNo=<%=(currentPageNo-1) %>&optionchk=<%=optionchk%>" >[PRE]</a>

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
    <a href="./U_rwk51.jsp?currentPageNo=<%=i %>&optionchk=<%=optionchk%>">[<%=(i+1) %>]</a>
 <%
      }
   }
   
   %>
<%
   if(currentPageNo < pageCnt - 1){
 %>
 <a href="./U_rwk51.jsp?currentPageNo=<%=(currentPageNo+1)%>&optionchk=<%=optionchk%>">[NXT]</a>
 <%
   }else{
 %>
 [NXT]
 <%
   }
   pstmt.close();
   con.close();
 %>
 <a href="./U_rwk51.jsp?currentPageNo=<%=(pageCnt-1) %>&optionchk=<%=optionchk%>">[END]</a>
 
  <br>
<%@ include file="./footer_rwk51.jsp" %> 

</body>
</html>