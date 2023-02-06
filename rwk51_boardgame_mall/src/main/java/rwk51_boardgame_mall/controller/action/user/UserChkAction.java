package rwk51_boardgame_mall.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.user.*;

public class UserChkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserDAO userDAO = new UserDAO();
		String actionType = request.getParameter("actionType");
		String user_id;
		String user_passwd;
		String user_passwdchk;
		boolean success = false;
	
		
		switch(actionType){
			case "user_id":
				user_id = request.getParameter("user_id");
				System.out.println(user_id);
				success = userDAO.chkID(user_id);
				
				break;
		}
		
		System.out.print(success);
	
		return null;
	}

}
