package com.boardgame.mall.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardgame.mall.controller.Action;
import com.boardgame.mall.controller.ActionForward;
import com.boardgame.mall.entity.UserDTO;
import com.boardgame.mall.repository.UserDAO;

public class UpdateUserForIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		UserDTO user = new UserDTO();
		System.out.println("UpdateUserForIdAction");
		
		user.setUser_num(Integer.parseInt(request.getParameter("user_num")));
		user.setUser_id( request.getParameter("user_id"));
		user.setUser_passwd(request.getParameter("user_passwd"));
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_RRN(request.getParameter("user_RRN"));
		user.setUser_email(request.getParameter("user_email"));
		user.setUser_class(request.getParameter("user_class"));
		user.setUser_phone(request.getParameter("user_phone"));
		user.setUser_address(request.getParameter("user_address"));
		user.setUser_addressdetail(request.getParameter("user_addressdetail"));
		
		
		
		UserDAO userDAO = new UserDAO();
		boolean result = userDAO.updateUser(user);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		if(result==true) {
			forward.setPath("index");
		}else {
			forward.setPath("etc/userError");
		}
		return forward;
	}

}
