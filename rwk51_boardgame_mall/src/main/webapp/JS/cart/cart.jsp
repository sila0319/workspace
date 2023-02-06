<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@page import ="rwk51_boardgame_mall.model.cart.* "%>
<%@page import="org.json.simple.JSONObject"%>
<%
	CartDAO cartDAO = new CartDAO();
	String actionType = request.getParameter("actionType");
	int c_id;
	int c_cnt;
	int user_num;
	int b_id;
	int b_price;
	int b_charge;
	String b_name;
	
	boolean success = false;
	JSONObject obj = new JSONObject();
	System.out.println("cart.jsp입니다.");
	System.out.println(actionType+"액션타입입니다.");
	
	
	switch(actionType){
		case "cart_chk":
			System.out.println(request.getParameter("b_id")+" "+request.getParameter("user_num"));
			b_id = Integer.parseInt(request.getParameter("b_id"));
			user_num = Integer.parseInt(request.getParameter("user_num"));
			System.out.println("카트 중복체크입니다.");
			success = cartDAO.chkCart(b_id,user_num );
			
		break;
		/* case "user_id":
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
			break; */
		
			
	}

	obj.put("checkResult",success);
	out.print(obj);
	
%>