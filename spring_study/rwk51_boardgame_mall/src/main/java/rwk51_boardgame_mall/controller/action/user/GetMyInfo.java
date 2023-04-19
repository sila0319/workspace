package rwk51_boardgame_mall.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.user.*;

public class GetMyInfo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GetMyInfo");
		UserDAO userDAO = new UserDAO();
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("user_num"));
		int user_num = ((Integer)(session.getAttribute("user_num"))).intValue();
		UserDTO user = userDAO.getUser(user_num);
				
		
		request.setAttribute("myPage",user);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/yju/2wda/ryu/view/user/my_page.jsp");
		return forward;
	}

}
