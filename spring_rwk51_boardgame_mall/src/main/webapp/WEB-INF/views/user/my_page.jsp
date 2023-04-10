<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.sql.*" %>
    <%@ page import ="com.boardgame.mall.entity.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file= "../header.jsp" %>
<body>

	<c:set var="user" value="${myPage}"/>
<div class="container row">
    <div class="col-lg-7">
        <div class="jumbotron" style="padding-top: 20px;">
            <h3 style="text-align: center;" class="mb-4">내 정보</h3>
            <!-- form -->
            <div class="form-group">
                <div>
                    <span>아이디 : ${user.user_id}</span>
                </div>
            </div>

            <div class="form-group">
                <span>이름 : ${user.user_name}</span>
            </div>

            <div class="form-group">
                <span>이메일 : ${user.user_email}</span>
            </div>

            <div class="form-group has-danger">
                <span >휴대폰 : ${user.user_phone}</span>
            </div>


            <div class="form-group">
                <span >주소 : ${user.user_address}</span>
            </div>

            <div class="form-group">
                <span >상세 주소 : ${user.user_addressdetail}</span>
            </div>

            <div class="form-group">
                <span >등급 : ${user.user_class}</span>
            </div>
            <form id="form" method="post" action="/user/UpdateUserDisplay">
                <input class="btn btn-success float-right" style="margin-left: 10px;" type="submit" value="회원수정">
                <input type="hidden" name="user_num" value="${user.user_num}">
            </form>
            <form id="form" method="post" action="/user/DeleteUserForId">
                <input class="btn btn-success float-right" style="margin-left: 10px;" type="submit" value="회원탈퇴">
                <input type="hidden" name="user_num" value="${user.user_num}">
            </form>
        </div>
    </div>

    <div class="col-lg-7" >
        <div class="jumbotron" style="padding-top: 20px;">
            <div class="form-group">
                <div><a href="./getCartList.be?user_num=${user.user_num}" >장바구니</a></div>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.user_class eq 'admin'}">
        <div class="col-lg-7" >
            <div class="jumbotron" style="padding-top: 20px;">
                <h3 style="text-align: center;" class="mb-4">관리자 기능</h3>
                <!-- form -->
                <div class="form-group">
                    <div><a href="/user/GetUserList">회원정보조회</a></div>
                </div>

                <div class="form-group">
                    <div><a href="/user/DeleteUserListDisplay/0">회원정보삭제</a></div>
                </div>

                <div class="form-group">
                    <div><a href="/user/UpdateUserListDisplay/0">회원정보수정</a></div>
                </div>

                <div class="form-group has-danger">
                    <div><a href ="${boardgameViewDir}/boardgame_c.jsp">상품등록</a></div>
                </div>
            </div>
        </div>
    </c:if>
</div>
	

</body>
<%@ include file= "../footer.jsp" %>
</html>