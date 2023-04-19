package rwk51_boardgame_mall.controller.action.boardgame;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;

import rwk51_boardgame_mall.model.boardgame.*;

public class SearchBoardgameForCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SearchBoardgameForCategoryAction");
		request.setCharacterEncoding("utf-8");
		
		String b_category = request.getParameter("b_category");
		String search = request.getParameter("search");
		String currentPageNo = request.getParameter("currentPageNo");
		int cpn = (currentPageNo == null) ?0 : Integer.parseInt(currentPageNo);
		
		HttpSession session = request.getSession();
		
		BoardgamePageInfoVO bpiVO = (BoardgamePageInfoVO)session.getAttribute("boardgamePageInfoVO");	
		bpiVO.setCurrentPageNo(cpn);
		bpiVO.adjPageInfo();
		
		BoardgameDAO boardgameDAO = new BoardgameDAO();
		BoardgameDTO boardgame = new BoardgameDTO();
	
		request.setAttribute("search", search);
		request.setAttribute("b_category", b_category);
		ArrayList<BoardgameDTO> boardgameList = boardgameDAO.searchBoardgame(b_category, search,bpiVO);
		request.setAttribute("boardgameList", boardgameList);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/yju/2wda/ryu/view/boardgame/boardgame_s.jsp");
		return forward;
	}

}
