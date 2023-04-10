package com.boardgame.mall.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boardgame.mall.controller.Action;
import com.boardgame.mall.controller.ActionForward;
import com.boardgame.mall.entity.UserDTO;
import com.boardgame.mall.repository.UserDAO;

public class GetMyInfo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GetMyInfo");
		UserDAO userDAO = new UserDAO();
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("user_num"));
		int user_num = ((Integer)(session.getAttribute("user_num"))).intValue();
		UserDTO user = userDAO.getUser(user_num);
				
		
		request.setAttribute("myPage",user);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/my_page");
		return forward;
	}

}
