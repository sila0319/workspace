package rwk51_boardgame_mall.controller.action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import rwk51_boardgame_mall.model.user.*;
import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;


public class GetUserListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDAO userDAO = new UserDAO();
		ArrayList<UserDTO> userList;
		userList = userDAO.getUserList();
		request.setAttribute("userList", userList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/yju/2wda/ryu/view/user/user_r.jsp");
		return forward;
	}

}
