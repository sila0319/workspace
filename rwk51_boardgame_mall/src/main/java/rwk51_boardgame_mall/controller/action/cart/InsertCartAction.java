package rwk51_boardgame_mall.controller.action.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;

import rwk51_boardgame_mall.controller.action.cart.*;
import rwk51_boardgame_mall.model.cart.*;

public class InsertCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		CartDAO cartDAO = new CartDAO();
		CartDTO cart = new CartDTO();
		
		cart.setUser_num(Integer.parseInt(request.getParameter("user_num")));
		cart.setB_id(Integer.parseInt(request.getParameter("b_id")));
		cart.setB_price(Integer.parseInt(request.getParameter("b_price")));
		cart.setB_charge(Integer.parseInt(request.getParameter("b_charge")));
		cart.setB_name(request.getParameter("b_name"));
		cart.setC_cnt(Integer.parseInt(request.getParameter("c_cnt")));
		System.out.println(cart.toString());
		
		boolean result = cartDAO.addCart(cart);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		if(result) {
			forward.setPath("/index.jsp");
		}else {
			forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
		}
		System.out.println("addCart");
		return forward;
	}

}
