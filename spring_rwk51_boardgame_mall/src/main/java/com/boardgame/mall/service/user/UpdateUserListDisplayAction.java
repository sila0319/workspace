package com.boardgame.mall.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boardgame.mall.controller.Action;
import com.boardgame.mall.controller.ActionForward;
import com.boardgame.mall.dto.UserPageInfoVO;
import com.boardgame.mall.entity.UserDTO;
import com.boardgame.mall.repository.UserDAO;
public class UpdateUserListDisplayAction implements Action {
	
	int cpn;
	public UpdateUserListDisplayAction(int cpn) {
		this.cpn = cpn;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String currentPageNo = request.getParameter("currentPageNo");
//		int cpn = (currentPageNo == null) ?0 : Integer.parseInt(currentPageNo);
//		
		HttpSession session = request.getSession();
		UserPageInfoVO upiVO = (UserPageInfoVO)session.getAttribute("userPageInfoVO");
		
		UserDAO userDAO = new UserDAO();
		ArrayList<UserDTO> userList;
		
		upiVO.setCurrentPageNo(cpn);
		upiVO.adjPageInfo();
		
		userList = userDAO.getUserListForPage(upiVO);
		request.setAttribute("userList", userList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_u");
		return forward;
	}

}
