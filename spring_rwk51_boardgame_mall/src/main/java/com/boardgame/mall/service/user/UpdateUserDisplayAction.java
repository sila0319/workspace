package com.boardgame.mall.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardgame.mall.controller.Action;
import com.boardgame.mall.controller.ActionForward;
import com.boardgame.mall.entity.UserDTO;
import com.boardgame.mall.repository.UserDAO;

public class UpdateUserDisplayAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UpdateUserDisplay");
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		
		UserDAO userDAO = new UserDAO();
		UserDTO user = new UserDTO();
		
		user = userDAO.getUser(user_num);
		
		request.setAttribute("user", user);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_u2");
		return forward;
	}

}
