<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="java.sql.*" %>
       <%@ page import ="java.util.*" %>
    <%@ page import ="rwk51_boardgame_mall.model.cart.*" %>
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
<%@ include file= "/header.jsp" %>
</head>
<body>

<%
	CartDTO cart;
	ArrayList <CartDTO> cartList = (ArrayList<CartDTO>)request.getAttribute("cartList");
	System.out.println("cartList 입니다."+cartList);
	int cart_price = 0;

%>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
        
         <div class="table-title">
                <div class="row">
                    <div class="col-sm-10">
                        <h2>장바구니</h2>
                    </div>
                    <div class="col-sm-2">
					   
                    </div>
                </div>
            </div>
            
			<table class="table table-striped table-hover">
		<thread>
			<tr>
				<th>번호</th>
				<th>상품이름</th>
				<th>상품가격</th>
				<th>상품갯수</th>
				<th>상품삭제</th>
			</tr>
		</thread>
		<tbody>
		<%
		for(int i = 0 ; i<cartList.size(); i++){
			cart = cartList.get(i);	
			cart_price += cart.getB_price()* cart.getC_cnt();
		%>
		<tr>
			<td><%=(i+1) %> </td>
			<td><%=cart.getB_name()%></td>
			<td><input value = "<%=cart.getB_price()%>" id = "b_price" readonly></td>
			<td><%=cart.getC_cnt() %></td>
			<td><a href="./deleteCartForCID.be?c_id=<%=cart.getC_id()%>&user_num=<%=session.getAttribute("user_num")%>">상품 삭제</a></td>
	<%-- 		<td> <input type="number" id="c_cnt"  value ="<%=cart.getC_cnt() %>" oninput="cnt_chk()"/></td> --%>
		</tr>
		<%
		}
		%>
		</tbody>
	</table>
	<div class="table-title">
                <div class="row">
                      <div class="col-sm-9">
                        상품 총 가격 : <input id = "cart_price"type=number readonly value="<%=cart_price%>">원 
                    </div>
                    <div class="col-sm-3">
					   <a href="./deleteCartForUserNum.be?user_num=<%=session.getAttribute("user_num")%>">장바구니 초기화</a>
						<a href="#">결제하기</a>
                    </div>
                </div>
            </div>
			</div>
	</div>
</div>


	
	
	
<%@ include file= "/footer.jsp" %>
</body>
</html>