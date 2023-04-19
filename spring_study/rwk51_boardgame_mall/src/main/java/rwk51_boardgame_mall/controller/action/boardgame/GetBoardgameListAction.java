package rwk51_boardgame_mall.controller.action.boardgame;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.boardgame.*;
import rwk51_boardgame_mall.model.user.UserDAO;
import rwk51_boardgame_mall.model.user.UserDTO;
import rwk51_boardgame_mall.model.user.UserPageInfoVO;

public class GetBoardgameListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("category");
		String currentPageNo = request.getParameter("currentPageNo");
		int cpn = (currentPageNo == null) ?0 : Integer.parseInt(currentPageNo);
		
		HttpSession session = request.getSession();
		BoardgamePageInfoVO bpiVO = (BoardgamePageInfoVO)session.getAttribute("boardgamePageInfoVO");
		BoardgameDAO boardgameDAO = new BoardgameDAO();
		ArrayList<BoardgameDTO> boardgameList;
		
		bpiVO.setCurrentPageNo(cpn);
		bpiVO.adjPageInfo();
		System.out.println(category);
		
		boardgameList = boardgameDAO.getBoardgameListForPage(category,bpiVO);
		request.setAttribute("boardgameList", boardgameList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/yju/2wda/ryu/view/boardgame/boardgame_r.jsp");
		return forward;
		
	}

}
