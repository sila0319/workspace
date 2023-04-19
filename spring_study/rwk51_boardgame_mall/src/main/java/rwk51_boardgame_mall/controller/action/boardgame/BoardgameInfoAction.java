package rwk51_boardgame_mall.controller.action.boardgame;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.boardgame.*;


public class BoardgameInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardgameDAO boardgameDAO = new BoardgameDAO();
		int b_id = Integer.parseInt(request.getParameter("b_id"));
		ArrayList<BoardgameDTO> boardgameList;
		boardgameList = boardgameDAO.getBoardgameInfo(b_id);
		request.setAttribute("boardgameList", boardgameList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/yju/2wda/ryu/view/boardgame/boardgame_info.jsp");
		return forward;
	}

}
