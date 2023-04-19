package rwk51_boardgame_mall.controller.action.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.cart.CartDAO;

public class DeleteCartForCIDAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteCartForIDAction입니다.");
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		//int user_num = (Integer)request.getAttribute("user_num");
		CartDAO cartDAO = new CartDAO();
		boolean result = cartDAO.deleteCartForCID(c_id);
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
