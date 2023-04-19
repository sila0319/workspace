package rwk51_boardgame_mall.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.user.UserDAO;
import rwk51_boardgame_mall.model.user.UserDTO;

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
		forward.setPath("/com/yju/2wda/ryu/view/user/user_u2.jsp");
		return forward;
	}

}
