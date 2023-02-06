package rwk51_mvc_beer3.controller.beer;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;
import rwk51_mvc_beer3.model.beer.*;

public class BeerController extends HttpServlet implements Servlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		String actionType = request.getParameter("actionType");
		
		BeerDTO beer;
		BeerDAO beerDAO;
		ArrayList<BeerDTO> beerList;
		
		BeerImageDTO beerImage;
		BeerImageDAO beerImageDAO;
		ArrayList<BeerImageDTO> beerImageList;
		
		
		BeerPageInfoVO bpiVO;
		//BeerImagePageInfoVO beerImagePageInfoVO;
		
		if(session.getAttribute("beerPageInfoVO") ==null){
			bpiVO = new BeerPageInfoVO();
			session.setAttribute("beerPageInfoVO",bpiVO);
		}else {
			bpiVO = (BeerPageInfoVO)session.getAttribute("beerPageInfoVO");
		}
	
		beerDAO = new BeerDAO();
		//beerDAO.jdbcDriverLoad(); dbcp 적용해서 주석처리
		
		boolean result;
		String msg = "실행결과 : ";
		
		String displayRecordCnt;
		int drc;
		
		String currentPageNo;
		int cpn;
		
		System.out.println("액션 타입"+actionType);
		switch(actionType){
		case "C" :
			beer = new BeerDTO();
			
			beer.setB_code(request.getParameter("b_code"));
			beer.setB_category(request.getParameter("b_category"));
			beer.setB_name(request.getParameter("b_name"));
			beer.setB_country(request.getParameter("b_country"));
			beer.setB_price(Integer.parseInt(request.getParameter("b_price")));
			beer.setB_alcohol(request.getParameter("b_alcohol"));
			beer.setB_content(request.getParameter("b_content"));
			beer.setB_like(0);
			beer.setB_dislike(0);
			beer.setB_image("baseImage.jpg");
			System.out.println(request.getParameter("b_content"));
			
			result = beerDAO.insertBeer(beer);
			
			if(result == true){
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);

			}
			break;
		case "R": //페이징 기능 없는 R-모듈
			beerList = beerDAO.getBeerList();
		
			request.setAttribute("beerList", beerList);
			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_r.jsp").forward(request, response);
			break;
		case "R4":
			currentPageNo = request.getParameter("currentPageNo");
			cpn = (currentPageNo ==null)?0:Integer.parseInt(currentPageNo);
			
			bpiVO.setCurrentPageNo(cpn);
			bpiVO.adjPageInfo();
			
			beerList = beerDAO.getBeerListForPage(bpiVO);
			
			request.setAttribute("beerList",beerList);
			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_r4.jsp").forward(request, response);
			break;
		
		case "R_DRC":
			displayRecordCnt = request.getParameter("displayRecordCnt");
			drc = (displayRecordCnt == null) ? 10 : Integer.parseInt(displayRecordCnt);
			
			bpiVO.setLimitCnt(drc);
			bpiVO.setCurrentPageNo(0);
			bpiVO.adjPageInfo();
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			break;	
			
		case "D_ID":
			int b_id = Integer.parseInt(request.getParameter("b_id"));
			result = beerDAO.deleteBeer(b_id);
			
			if(result ==true){
				request.getRequestDispatcher("/com/yju/2wda/team2/BeerController.be?actionTypr=D").forward(request, response);
			}else{
				request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);
			}
			break;
		case "D":
			currentPageNo = request.getParameter("currentPageNo");
			cpn = (currentPageNo == null)?0:Integer.parseInt(currentPageNo);
			
			bpiVO.setCurrentPageNo(cpn);
			bpiVO.adjPageInfo();
			
			beerList = beerDAO.getBeerListForPage(bpiVO);
			request.setAttribute("beerList",beerList);
			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_d.jsp").forward(request, response);
			break;
			
		case "U" : //페이지당 디스플레이 레코드 갯수 출력 및 수정 처리 
			currentPageNo = request.getParameter("currentPageNo");
			cpn = (currentPageNo ==null) ?0:Integer.parseInt(currentPageNo);
			bpiVO.setCurrentPageNo(cpn);
			bpiVO.adjPageInfo();
			
			beerList = beerDAO.getBeerListForPage(bpiVO);
			request.setAttribute("beerList",beerList);
			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_u.jsp").forward(request, response);
		
		break;
		case "U2":
			b_id = Integer.parseInt(request.getParameter("b_id"));
			beer = beerDAO.getBeer(b_id);
			
			request.setAttribute("beer",beer);
			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_u2.jsp").forward(request, response);
			break;
		case "U_ID":
			beer = new BeerDTO();
			
			beer.setB_id(Integer.parseInt(request.getParameter("b_id")));
			beer.setB_code(request.getParameter("b_code"));
			beer.setB_category(request.getParameter("b_category"));
			beer.setB_name(request.getParameter("b_name"));
			beer.setB_country(request.getParameter("b_country"));
			beer.setB_price(Integer.parseInt(request.getParameter("b_price")));
			beer.setB_alcohol(request.getParameter("b_alcohol"));
			beer.setB_content(request.getParameter("b_content"));
			beer.setB_like(Integer.parseInt(request.getParameter("b_like") ));
			beer.setB_dislike(Integer.parseInt(request.getParameter("b_dislike")));
			beer.setB_image("baseImage.jpg");
			
			result = beerDAO.updateBeer(beer);
			
			if(result ==true){
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);
			}
			
			break;
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request,response);
	}
	

}
