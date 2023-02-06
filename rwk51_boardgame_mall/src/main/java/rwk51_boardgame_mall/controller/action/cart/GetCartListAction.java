package rwk51_boardgame_mall.controller.action.cart;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.cart.CartDAO;
import rwk51_boardgame_mall.model.cart.CartDTO;

public class GetCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		CartDAO cartDAO = new CartDAO();
		CartDTO cart = new CartDTO();
		HttpSession session = request.getSession();
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		/*
		 * System.out.println(request.getAttribute("user_num")); int user_num =
		 * ((Integer)(request.getAttribute("user_num")) ).intValue();
		 */
		ArrayList<CartDTO> cartList = cartDAO.getCartList(user_num);
		request.setAttribute("cartList", cartList);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/yju/2wda/ryu/view/cart/cart_r.jsp");
		return forward;
	}

}
