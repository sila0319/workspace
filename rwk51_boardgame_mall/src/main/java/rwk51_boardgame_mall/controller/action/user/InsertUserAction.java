package rwk51_boardgame_mall.controller.action.user;

import javax.servlet.http.*;
import java.util.*;


import rwk51_boardgame_mall.controller.*;
import rwk51_boardgame_mall.model.user.*;

public class InsertUserAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("insertUserAction");
		UserDAO userDAO = new UserDAO();
		UserDTO user = new UserDTO();
		
		user.setUser_id(request.getParameter("user_id"));
		user.setUser_passwd(request.getParameter("user_passwd"));
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_RRN(request.getParameter("user_RRN"));
		user.setUser_email(request.getParameter("user_email"));
		user.setUser_phone(request.getParameter("user_phone"));
		user.setUser_address(request.getParameter("user_address"));
		user.setUser_addressdetail(request.getParameter("user_addressdetail"));
		
		boolean result = userDAO.insertUser(user);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		if(result) {
			forward.setPath("/index.jsp");
		}else {
			forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
		}
		System.out.println("insertUser");
		return forward;
	}

}
