package rwk51_boardgame_mall.controller.action.boardgame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.boardgame.*;

public class DeleteBoardgameForIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteBoardgameForIdAction");
		int b_id = Integer.parseInt(request.getParameter("b_id"));
		String category = request.getParameter("b_category");
		System.out.println(b_id +" " +category);
		BoardgameDAO boardgameDAO = new BoardgameDAO();
		boolean result = boardgameDAO.deleteBoardgame(b_id);
		ActionForward forward = new ActionForward();
		System.out.println(result);
		
		if(result){
			forward.setRedirect(false);
			forward.setPath("/index.jsp");
			//getBoardgameList.be?currentPageNo=0&category=strategy
		}else {
			forward.setRedirect(result);
			forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
		}
		return forward;
	}

}
