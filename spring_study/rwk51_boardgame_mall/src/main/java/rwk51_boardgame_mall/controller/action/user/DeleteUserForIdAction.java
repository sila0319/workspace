package rwk51_boardgame_mall.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rwk51_boardgame_mall.controller.*;
import rwk51_boardgame_mall.model.user.*;



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
				forward.setPath("./logoutUser.be");
				
			}else {
				forward.setRedirect(result);
				forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
			}
		}else if(session.getAttribute("user_class").equals("admin")) {
			if(result) {
				forward.setRedirect(result);
				forward.setPath("./deleteUserListDisplay.be");
				
			}else {
				forward.setRedirect(result);
				forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
			}
		}else {
			forward.setRedirect(false);
			forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
		}
		return forward;
		
	}
}
