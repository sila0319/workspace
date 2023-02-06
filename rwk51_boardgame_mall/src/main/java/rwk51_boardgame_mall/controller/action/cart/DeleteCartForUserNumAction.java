package rwk51_boardgame_mall.controller.action.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rwk51_boardgame_mall.controller.*;
import rwk51_boardgame_mall.model.cart.*;

public class DeleteCartForUserNumAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		//int user_num = (Integer)request.getAttribute("user_num");
		CartDAO cartDAO = new CartDAO();
		boolean result = cartDAO.deleteCart(user_num);
		ActionForward forward = new ActionForward();
		
		if(result) {
			forward.setRedirect(false);
			forward.setPath("./getCartList.be?user_num="+user_num);
			
		}else {
			forward.setRedirect(result);
			forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
		}
		return forward;
		
	}
}
