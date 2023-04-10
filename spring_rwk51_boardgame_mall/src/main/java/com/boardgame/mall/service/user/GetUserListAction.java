package com.boardgame.mall.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardgame.mall.controller.Action;
import com.boardgame.mall.controller.ActionForward;
import com.boardgame.mall.entity.UserDTO;
import com.boardgame.mall.repository.UserDAO;


public class GetUserListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDAO userDAO = new UserDAO();
		ArrayList<UserDTO> userList;
		userList = userDAO.getUserList();
		request.setAttribute("userList", userList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_r");
		return forward;
	}

}
