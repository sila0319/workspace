<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
    <%@ page import ="rwk51_boardgame_mall.model.user.*" %>
    <%@ page import ="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        body {
            color: #566787;
            background: #f5f5f5;
            font-family: 'Varela Round', sans-serif;
            font-size: 13px;
        }
        .table-responsive {
            margin: 30px 0;
        }
        .table-wrapper {
            background: #fff;
            padding: 20px 25px;
            border-radius: 3px;
            min-width: 1000px;
            box-shadow: 0 1px 1px rgba(0,0,0,.05);
        }
        .table-title {
            padding-bottom: 15px;
            background: #435d7d;
            color: #fff;
            padding: 16px 30px;
            min-width: 100%;
            margin: -20px -25px 10px;
            border-radius: 3px 3px 0 0;
        }
        .table-title h2 {
            margin: 5px 0 0;
            font-size: 24px;
        }
        .table-title .btn-group {
            float: right;
        }
        .table-title .btn {
            color: #fff;
            float: right;
            font-size: 13px;
            border: none;
            min-width: 50px;
            border-radius: 2px;
            border: none;
            outline: none !important;
            margin-left: 10px;
        }
        .table-title .btn i {
            float: left;
            font-size: 21px;
            margin-right: 5px;
        }
        .table-title .btn span {
            float: left;
            margin-top: 2px;
        }
        table.table tr th, table.table tr td {
            border-color: #e9e9e9;
            padding: 12px 15px;
            vertical-align: middle;
        }
        table.table tr th:first-child {
            width: 60px;
        }
        table.table tr th:last-child {
            width: 100px;
        }
        table.table-striped tbody tr:nth-of-type(odd) {
            background-color: #fcfcfc;
        }
        table.table-striped.table-hover tbody tr:hover {
            background: #f5f5f5;
        }
        table.table th i {
            font-size: 13px;
            margin: 0 5px;
            cursor: pointer;
        }
        table.table td:last-child i {
            opacity: 0.9;
            font-size: 22px;
            margin: 0 5px;
        }
        table.table td a {
            font-weight: bold;
            color: #566787;
            display: inline-block;
            text-decoration: none;
            outline: none !important;
        }
        table.table td a:hover {
            color: #2196F3;
        }
        table.table td a.edit {
            color: #FFC107;
        }
        table.table td a.delete {
            color: #F44336;
        }
        table.table td i {
            font-size: 19px;
        }
        table.table .avatar {
            border-radius: 50%;
            vertical-align: middle;
            margin-right: 10px;
        }
        .pagination {
            float: right;
            margin: 0 0 5px;
        }
        .pagination li a {
            border: none;
            font-size: 13px;
            min-width: 30px;
            min-height: 30px;
            color: #999;
            margin: 0 2px;
            line-height: 30px;
            border-radius: 2px !important;
            text-align: center;
            padding: 0 6px;
        }
        .pagination li a:hover {
            color: #666;
        }
        .pagination li.active a, .pagination li.active a.page-link {
            background: #03A9F4;
        }
        .pagination li.active a:hover {
            background: #0397d6;
        }
        .pagination li.disabled i {
            color: #ccc;
        }
        .pagination li i {
            font-size: 16px;
            padding-top: 6px
        }
        .hint-text {
            float: left;
            margin-top: 10px;
            font-size: 13px;
        }
        /* Modal styles */
        .modal .modal-dialog {
            max-width: 400px;
        }
        .modal .modal-header, .modal .modal-body, .modal .modal-footer {
            padding: 20px 30px;
        }
        .modal .modal-content {
            border-radius: 3px;
            font-size: 14px;
        }
        .modal .modal-footer {
            background: #ecf0f1;
            border-radius: 0 0 3px 3px;
        }
        .modal .modal-title {
            display: inline-block;
        }
        .modal .form-control {
            border-radius: 2px;
            box-shadow: none;
            border-color: #dddddd;
        }
        .modal textarea.form-control {
            resize: vertical;
        }
        .modal .btn {
            border-radius: 2px;
            min-width: 100px;
        }
        .modal form label {
            font-weight: normal;
        }
    </style>
</head>
<%@ include file= "/header.jsp" %>
<body>

<%
	UserDTO user;
	ArrayList<UserDTO>userList;
	UserPageInfoVO upiVO;
	
	upiVO = (UserPageInfoVO)session.getAttribute("userPageInfoVO");
	userList = (ArrayList<UserDTO>)request.getAttribute("userList");
	
	int currentPageNo = upiVO.getCurrentPageNo();
	System.out.println("user_d페이지입니다"+upiVO.getLimitCnt() );
%>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-10">
                        <h2>관리자 <b>유저 삭제</b></h2>
                    </div>
                    <div class="col-sm-2">
					  
                    </div>
                </div>
            </div>
   			 <table class="table table-striped table-hover">
		<thread>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>주민등록번호</th>
				<th>이메일</th>
				<th>휴대폰</th>
				<th>주소</th>
				<th>상세 주소</th>
				<th>등급</th>
				<th>삭제</th>
			</tr>
		</thread>
		<tbody>
		<%
		for(int i = 0 ; i<userList.size(); i++){
			user = userList.get(i);	
		%>
		<tr>
			<td><%=user.getUser_num()%></td>
			<td><%=user.getUser_id() %></td>
			<td><%=user.getUser_passwd() %></td>
			<td><%=user.getUser_name() %></td>
			<td><%=user.getUser_RRN()  %></td>
			<td><%=user.getUser_email() %></td> 
			<td><%=user.getUser_phone() %></td>
			<td><%=user.getUser_address() %></td>
			<td><%=user.getUser_addressdetail() %></td>
			<td><%=user.getUser_class()%></td>
			<td><a href ="./deleteUserForId.be?user_num=<%=user.getUser_num()%>">DELETE</a></td>
		</tr>
		<%
		}
		%>
		</tbody>
	</table>
	 <div class="clearfix">
     	<ul class="pagination">
	
	<li class="page-item disabled"><a href="./deleteUserListDisplay.be?currentPageNo=0">[FIRST]</a></li>
	<%      
   
   if(currentPageNo > 0 ){
%>
  <li class="page-item"><a href="./deleteUserListDisplay.be?currentPageNo=<%=(currentPageNo-1) %>">[PRE]</a></li>

<%
   }
   else{
     
%>
 <li class="page-item">[PRE]</li>
   
<%
   }
   for(int i = upiVO.getStartPageNo() ; i<upiVO.getEndPageNo(); i++){
	   
      if(i == currentPageNo){
%>
 <li class="page-item active">  [<%=(i+1) %>]</li>
 <%
      }else{
 %>
   <li class="page-item"><a href="./deleteUserListDisplay.be?currentPageNo=<%=(i) %>">[<%=(i+1) %>]</a></li>
 <%
      }
   }
   
   %>
<%
   if(currentPageNo < upiVO.getPageCnt()- 1){
 %>
 <li class="page-item" ><a href="./deleteUserListDisplay.be?currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a></li>
 <%
   }else{
 %>
 [NXT]
 <%
   }
 %>
  <li class="page-item"> <a href="./deleteUserListDisplay.be?currentPageNo=<%=(upiVO.getPageCnt()-1)%>">END</a></li>
  </ul>
  </div>
	        </div>
    </div>
</div>
	
	<%@ include file= "/footer.jsp" %>
</body>
</html>