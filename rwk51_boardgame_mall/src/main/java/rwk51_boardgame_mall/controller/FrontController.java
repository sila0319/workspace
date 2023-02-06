package rwk51_boardgame_mall.controller;

import rwk51_boardgame_mall.controller.*;

import rwk51_boardgame_mall.controller.action.user.*;
import rwk51_boardgame_mall.model.user.*;

import rwk51_boardgame_mall.model.boardgame.*;
import rwk51_boardgame_mall.controller.action.boardgame.*;

import rwk51_boardgame_mall.model.cart.*;
import rwk51_boardgame_mall.controller.action.cart.*;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class FrontController extends HttpServlet implements Servlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		System.out.println("FrontController");
		String RequestURI =request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		command = command.substring(command.lastIndexOf("/"));
		
		UserPageInfoVO upiVO;
		BoardgamePageInfoVO bpiVO;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userPageInfoVO")== null) {
			upiVO = new UserPageInfoVO();
			session.setAttribute("userPageInfoVO", upiVO);
		}else {
			upiVO = (UserPageInfoVO)session.getAttribute("userPageInfoVO");
		}
		
		if(session.getAttribute("boardgamePageInfoVO")== null) {
			bpiVO = new BoardgamePageInfoVO();
			session.setAttribute("boardgamePageInfoVO", bpiVO);
		}else {
			bpiVO = (BoardgamePageInfoVO)session.getAttribute("boardgamePageInfoVO");
		}
	
		ActionForward forward = null;
		Action action = null;
		
		System.out.println("command = " + command);
		
		switch(command) {
			/**회원가입 (C)를 하기위한 경로다*/
		case "/insertUser.be":
			action = new InsertUserAction();
			break;
		case "/loginUser.be":
			action = new LoginUser();
			break;
		case "/logoutUser.be" :
			action = new LogoutUser();
			break;
		
		case "./userChk.be":
			action = new UserChkAction();
			break;
			
			/**유저 정보 조회*/
		case "/getUserList.be":
			action = new GetUserListAction();
			break;
		case "/getMyInfo.be":
			action = new GetMyInfo();
			break;
			
			/**회원삭제관련 */
		case "/deleteUserForId.be":
			action = new DeleteUserForIdAction();
			break;
		case "/deleteUserListDisplay.be":
			action = new DeleteUserListDisplayAction();
			break;
			
			
			/**회원수정관련*/
		case "/updateUserListDisplay.be":
			action = new UpdateUserListDisplayAction();
			break;
			
		case "/updateUserDisplay.be":
			action = new UpdateUserDisplayAction();
			break;
			
		case "/updateUserForId.be":
			action = new UpdateUserForIdAction();
			break; 
			

			/**상품 c를 하기 위한 경로.*/
		case "/insertBoardgame.be":
			action = new InsertBoardgameAction();
			break;
			
			/**상품게시판 띄우기*/
		case "/getBoardgameList.be":
			action = new GetBoardgameListAction();
			break;
		case "/boardgameInfo.be":
			action = new BoardgameInfoAction();
			break;
			
			/**상품삭제*/
		case"/deleteBoardgameForId.be":
			action = new DeleteBoardgameForIdAction();
			break;
			
			/**상품수정*/
		case "/updateBoardgameDisplay.be":
			action = new UpdateBoardgameDisplayAction();
			break;
		case "/updateBoardgameForId.be":
			action = new UpdateBoardgameForIdAction();
			break;
			
			/**상품검색*/
		case "/searchBoardgame.be":
			action = new SearchBoardgameForCategoryAction();
			break;
			
			/**장바구니 담기*/
		case "/insertcart.be":
			action = new InsertCartAction();
			break;
			
		case "/getCartList.be":
			action = new GetCartListAction();
			break;
			
		case "/deleteCartForUserNum.be":
			action = new DeleteCartForUserNumAction();
			break;
		case "/deleteCartForCID.be":
			action = new DeleteCartForCIDAction();
			break;

		}
		try {
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(forward !=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}

}
