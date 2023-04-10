<%-- <%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@page import ="com.boardgame.mall.repository.* "%>

<%@page import="org.json.simple.JSONObject"%>
<%
	UserDAO userDAO = new UserDAO();
	String actionType = request.getParameter("actionType");
	String user_id;
	String user_passwd;
	String user_passwdchk;
	String user_RRN;
	String user_phone;
	String user_email;
	boolean success = false;
	JSONObject obj = new JSONObject();
	
	
	switch(actionType){
		case "user_id":
			user_id = request.getParameter("user_data");
			System.out.println(user_id+"유저아이디입니다.");
			success = userDAO.chkID(user_id);
			
			break;
			
		case "user_RRN":
			user_RRN = (request.getParameter("user_data"));
			System.out.println(user_RRN+"유저주민등록번호입니다.");
			success = userDAO.chkRRN(user_RRN);
			break;
			
		case "user_email":
			user_email = request.getParameter("user_data");
			System.out.println(user_email + "유저이메일입니다.");
			success = userDAO.chkemail(user_email);
			
			break;
		
		case "user_phone":
			user_phone = request.getParameter("user_data");
			System.out.println(user_phone+"유저 휴대폰번호입니다.");
			success = userDAO.chkphone(user_phone);
			break;
		
			
	}

	obj.put("checkResult",success);
	out.print(obj);
	
%>