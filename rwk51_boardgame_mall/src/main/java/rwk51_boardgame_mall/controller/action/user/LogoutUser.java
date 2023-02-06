package rwk51_boardgame_mall.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.user.UserDAO;
import rwk51_boardgame_mall.model.user.UserDTO;

public class LogoutUser implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean result = true;
		request.setCharacterEncoding("utf-8");
		System.out.println("logoutUserAction");
		HttpSession session = request.getSession();
		
	
		session.setAttribute("user_id", null );
		session.setAttribute("user_passwd", null );
		session.setAttribute("user_class", null );
		session.setAttribute("user_num", null);
		session.setAttribute("loginState", "logout");
		
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		if(result) {
			forward.setPath("/index.jsp");
		}else {
			forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
		}

		return forward;
	}

}
