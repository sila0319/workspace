package rwk51_boardgame_mall.controller.action.user;

import javax.servlet.http.*;
import java.util.*;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.user.*;

public class LoginUser implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean result = false;
		request.setCharacterEncoding("utf-8");
		System.out.println("loginUserAction");
		UserDAO userDAO = new UserDAO();
		//UserDTO user = new UserDTO();
		String user_id = request.getParameter("user_id");
		String user_passwd = request.getParameter("user_passwd");
		System.out.println("유저 아이디 비번 값 출력" +user_id +" "+ user_passwd);
		UserDTO user =  userDAO.loginUser(user_id, user_passwd);
		HttpSession session = request.getSession();
		
		if(user.getUser_id() == null || user.getUser_passwd() == null) {
			session.setAttribute("loginState", "errorID");
			result = true;
		}
		else if(user.getUser_id().equals(user_id)&& user.getUser_passwd().equals(user_passwd)) {
			session.setAttribute("user_id",user_id);
			session.setAttribute("user_passwd", user_passwd);
			session.setAttribute("user_class", user.getUser_class());
			session.setAttribute("user_num", user.getUser_num());
			session.setAttribute("loginState", "login");
			result = true;
			System.out.println("로그인 성공");
			System.out.println(session.getAttribute("user_num")+"유저 넘번호.");
			
		}else {
			
			session.setAttribute("loginState", "errorID");
		}
		
		
		
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
