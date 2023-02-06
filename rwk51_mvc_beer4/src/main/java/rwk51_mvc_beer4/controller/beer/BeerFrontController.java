package rwk51_mvc_beer4.controller.beer;

import rwk51_mvc_beer4.controller.beer.*;
import rwk51_mvc_beer4.model.beer.*;
import rwk51_mvc_beer4.controller.beer.action.*;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class BeerFrontController extends HttpServlet implements Servlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String RequestURI =request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		command = command.substring(command.lastIndexOf("/"));
		
		BeerPageInfoVO bpiVO;
		HttpSession session = request.getSession();
		if(session.getAttribute("beerPageInfoVO")== null) {
			bpiVO = new BeerPageInfoVO();
			session.setAttribute("beerPageInfoVO", bpiVO);
		}else {
			bpiVO = (BeerPageInfoVO)session.getAttribute("beerPageInfoVO");
		}
		
		ActionForward forward = null;
		Action action = null;
		
		System.out.println("command = " + command);
		
		switch(command) {
			/**(C)를 하기위한 경로다*/
		case "/insertBeer.be":
			action = new InsertBeerAction();
			break;
			
			/**(R)을 하기위한 경로다.*/
		case "/getBeerList.be":
			action = new GetBeerListAction();
			break;
			
			/**(R)의 페이징 하기위한 경로다.*/
		case "/getBeerListForPage.be":
			action = new GetBeerListForPageAction();
			break;
			
			/**(drc)를 설정하기위한 경로다.*/
		case "/adjustBPI.be":
			action = new AdjustBPIAction();
			break;
			
			/**(선택된 행 삭제하기 위한 경로)*/
		case "/deleteBeerForID.be":
			action = new DeleteBeerForIDAction();
			break;
			
			/**(d)을 하기위한 경로*/
		case "/deleteBeerListDisplay.be":
			action = new DeleteBeerListDisplayAction();
			break;
			
			/**업데이트페이지 페이징 기능을 위한 경로*/
		case "/updateBeerListDisplay.be":
			action = new UpdateBeerListDisplayAction();
			break;
			
			/**사용자가 업데이트를 하기위해 선택된 페이지로 가는 경로(업데이트작성페이지) */
		case "/updateBeerDisplay.be":
			action = new UpdateBeerDisplayAction();
			break;
			
			/**사용자가 작성한 업데이트를 하기위한 경로*/
		case "/updateBeerForID.be":
			action = new UpdateBeerForIDAction();
			break;
			
			/***/
		default : 
			action = new DefaultAction();
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
