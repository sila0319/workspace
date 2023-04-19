package rwk51_boardgame_mall.controller.action.boardgame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.boardgame.*;

public class UpdateBoardgameDisplayAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UpdateBoardgameDisplayAction");
		int b_id = Integer.parseInt(request.getParameter("b_id"));
		
		BoardgameDAO boardgameDAO = new BoardgameDAO();
		BoardgameDTO boardgame = new BoardgameDTO();
		
		boardgame = boardgameDAO.getBoardgame(b_id);
		request.setAttribute("boardgame", boardgame);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("/com/yju/2wda/ryu/view/boardgame/boardgame_u2.jsp");
		
		return forward;
	}

}
