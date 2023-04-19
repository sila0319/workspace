package rwk51_boardgame_mall.controller.action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;

import rwk51_boardgame_mall.model.user.*;


public class UpdateUserListDisplayAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentPageNo = request.getParameter("currentPageNo");
		int cpn = (currentPageNo == null) ?0 : Integer.parseInt(currentPageNo);
		
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
		forward.setPath("/com/yju/2wda/ryu/view/user/user_u.jsp");
		return forward;
	}

}
