package com.boardgame.mall.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boardgame.mall.controller.Action;
import com.boardgame.mall.controller.ActionForward;
import com.boardgame.mall.repository.UserDAO;

//import rwk51_boardgame_mall.model.user.*;



public class DeleteUserForIdAction implements Action{
	
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		
		UserDAO userDAO = new UserDAO();
		boolean result = userDAO.deleteUser(user_num);
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		if(session.getAttribute("user_class").equals("user")) {
			if(result) {
				forward.setRedirect(result);
				forward.setPath("logout");
				
			}else {
				forward.setRedirect(result);
				forward.setPath("etc/userError");
			}
		}else if(session.getAttribute("user_class").equals("admin")) {
			if(result) {
				forward.setRedirect(result);
				forward.setPath("index");
				
			}else {
				forward.setRedirect(result);
				forward.setPath("etc/userError");
			}
		}else {
			forward.setRedirect(false);
			forward.setPath("etc/userError");
		}
		return forward;
		
	}
}
