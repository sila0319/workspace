package rwk51_mvc_beer3.controller.beer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import rwk51_mvc_beer3.model.beer.*;






public class BeerMultiController extends HttpServlet implements Servlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		int maxSize = 1024*1024*5;
		String imgDirPath ="D:\\dev\\workspace\\rwk51_mvc_beer3\\src\\main\\webapp\\image\\original\\";
		String thumbimgDirPath="D:\\dev\\workspace\\rwk51_mvc_beer3\\src\\main\\webapp\\image\\thumb\\";
		MultipartRequest multi = null;
		String actionType = null;
		if(session.getAttribute("ViewState").equals("c2")) {
			multi  = new MultipartRequest(request,imgDirPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
			actionType = multi.getParameter("actionType");
		}else {
			actionType = request.getParameter("actionType");
		}

		BeerDTO beer;
		BeerDAO beerDAO;
		//ArrayList<BeerDTO> beerList;
		
		BeerImageDTO beerImage;
		BeerImageDAO beerImageDAO;
		ArrayList<BeerVO> beerVOList;
		
	
		
		
		//BeerPageInfoVO bpiVO;
		BeerImagePageInfoVO beerImagePageInfoVO = null;
		
		if(session.getAttribute("beerImagePageInfoVO") ==null){
			beerImagePageInfoVO = new BeerImagePageInfoVO();
			session.setAttribute("beerImagePageInfoVO",beerImagePageInfoVO);
		}else{
			beerImagePageInfoVO = (BeerImagePageInfoVO)session.getAttribute("beerImagePageInfoVO");
		}
		//beerDAO = new BeerDAO();
		//beerImageDAO= new BeerImageDAO();
		//beerImageDAO = new BeerImageDAO();
		//beerDAO.jdbcDriverLoad(); dbcp 적용해서 주석처리
		
		boolean result;
		String msg = "실행결과 : ";
		
		String displayRecordCnt;
		int drc;
		
		String displayBeer;
		String drb;
		
		String currentPageNo;
		int cpn;
		
		System.out.println("액션 타입"+actionType);
		
		switch(actionType){
		case "C" :
			beerDAO = new BeerDAO();
			beer = new BeerDTO();
			BeerCategoryDTO beerCategory = new BeerCategoryDTO();
			BeerCountryDTO beerCountry = new BeerCountryDTO();
			beerCategory.setB_category(multi.getParameter("b_country"));
			beerCountry.setB_country(multi.getParameter("b_category"));
			beer =  beerDAO.createBeerCode(beerCountry,beerCategory);
			
			
			//beer.setB_code(multi.getParameter("b_code"));
			beer.setB_category(multi.getParameter("b_category"));
			beer.setB_name(multi.getParameter("b_name"));
			beer.setB_country(multi.getParameter("b_country"));
			beer.setB_price(Integer.parseInt(multi.getParameter("b_price")));
			beer.setB_alcohol(multi.getParameter("b_alcohol"));
			beer.setB_content(multi.getParameter("b_content"));
			beer.setB_like(0);
			beer.setB_dislike(0);
			beer.setB_image("baseImage.jpg");
			
			
			
			
			//System.out.println(request.getParameter("b_content"));
			
			result = beerDAO.insertBeer(beer);
			System.out.println("beer.getB_code테스트"+beer.getB_code());
			
			BeerDAO beerDAO2 = new BeerDAO();
			
			beer.setB_id(beerDAO2.nextIncrement(beer.getB_code()));
			System.out.println("beer.getB_id테스트"+beer.getB_id());
			
			if(result == true){
				System.out.println("beer삽입 성공");
			}else{
				System.out.println("beer삽입 실패");	
			}
			
			
			Enumeration<?> files = multi.getFileNames();
			
			for(int i = 0; i < 3 ;i++) {
				//System.out.println(files.hasMoreElements());
				if(files.hasMoreElements()) {
					
					String element = (String)files.nextElement();
					
					if(multi.getFile(element)==null) {
						continue;
					}
					beerImage = new BeerImageDTO();
					beerImageDAO = new BeerImageDAO();
					beerImage.setI_file_name(multi.getFilesystemName(element));
					beerImage.setI_original_name(multi.getOriginalFileName(element));
					
					String oPath = imgDirPath+beerImage.getI_file_name(); // 원본 경로
					   File oFile = new File(oPath);
					   
					   int index = oPath.lastIndexOf(".");
					   String ext = oPath.substring(index + 1); // 파일 확장자
					   
					   String tPath = thumbimgDirPath + "t_" + oFile.getName(); // 썸네일저장 경로
					   File tFile = new File(tPath);
					   
					   double ratio = 2; // 이미지 축소 비율
					   
					   BufferedImage oImage = ImageIO.read(oFile); // 원본이미지
					      int tWidth = (int) (oImage.getWidth() / ratio); // 생성할 썸네일이미지의 너비
					      int tHeight = (int) (oImage.getHeight() / ratio); // 생성할 썸네일이미지의 높이
					      
					      BufferedImage tImage = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_3BYTE_BGR); // 썸네일이미지
					      Graphics2D graphic = tImage.createGraphics();
					      Image image = oImage.getScaledInstance(tWidth, tHeight, Image.SCALE_SMOOTH);
					      graphic.drawImage(image, 0, 0, tWidth, tHeight, null);
					      graphic.dispose(); // 리소스를 모두 해제
					   
					      ImageIO.write(tImage, ext, tFile);
					      beerImage.setI_thumbnail_name("t_"+multi.getFilesystemName(element));					
					      beerImage.setI_file_size( (multi.getFile(element).length()));
					      beerImage.setI_f_id(beer.getB_id());
					      beerImageDAO.insertBeerImage(beerImage);
				}
			}

			
			
			if(result == true){
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);

			}
			break;
		case "R": //페이징 기능 없는 R-모듈
//			beerList = beerDAO.getBeerList();
//		
//			request.setAttribute("beerList", beerList);
//			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_r.jsp").forward(request, response);
//			break;
		case "R5":
			beerImageDAO= new BeerImageDAO();
			currentPageNo = request.getParameter("currentPageNo");
			cpn = (currentPageNo ==null)?0:Integer.parseInt(currentPageNo);
			
			beerImagePageInfoVO.setCurrentPageNo(cpn);
			beerImagePageInfoVO.adjPageInfo();
			
			beerVOList = beerImageDAO.getBeerListForPage(beerImagePageInfoVO);
			System.out.println("R5영역입니다.");
			
			request.setAttribute("beerVOList",beerVOList);
			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_r5.jsp").forward(request, response);
			break;
		case "R_DRC":
			displayRecordCnt = request.getParameter("displayRecordCnt");
			drc = (displayRecordCnt == null) ? 10 : Integer.parseInt(displayRecordCnt);
			displayBeer = request.getParameter("displayBeer");
			drb =(displayBeer==null) ? "all" : displayBeer;
			
			
			beerImagePageInfoVO.setLimitCnt(drc);
			beerImagePageInfoVO.setDisplayBeer(drb);
			beerImagePageInfoVO.setCurrentPageNo(0);
			beerImagePageInfoVO.adjPageInfo();
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			break;	
//			
//		case "D_ID":
//			int b_id = Integer.parseInt(request.getParameter("b_id"));
//			result = beerDAO.deleteBeer(b_id);
//			
//			if(result ==true){
//				request.getRequestDispatcher("/com/yju/2wda/team2/BeerController.be?actionTypr=D").forward(request, response);
//			}else{
//				request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);
//			}
//			break;
//		case "D":
//			currentPageNo = request.getParameter("currentPageNo");
//			cpn = (currentPageNo == null)?0:Integer.parseInt(currentPageNo);
//			
//			bpiVO.setCurrentPageNo(cpn);
//			bpiVO.adjPageInfo();
//			
//			beerList = beerDAO.getBeerListForPage(bpiVO);
//			request.setAttribute("beerList",beerList);
//			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_d.jsp").forward(request, response);
//			break;
//			
//		case "U" : //페이지당 디스플레이 레코드 갯수 출력 및 수정 처리 
//			currentPageNo = request.getParameter("currentPageNo");
//			cpn = (currentPageNo ==null) ?0:Integer.parseInt(currentPageNo);
//			bpiVO.setCurrentPageNo(cpn);
//			bpiVO.adjPageInfo();
//			
//			beerList = beerDAO.getBeerListForPage(bpiVO);
//			request.setAttribute("beerList",beerList);
//			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_u.jsp").forward(request, response);
//		
//		break;
//		case "U2":
//			b_id = Integer.parseInt(request.getParameter("b_id"));
//			beer = beerDAO.getBeer(b_id);
//			
//			request.setAttribute("beer",beer);
//			request.getRequestDispatcher("/com/yju/2wda/team2/view/beer/beer_u2.jsp").forward(request, response);
//			break;
//		case "U_ID":
//			beer = new BeerDTO();
//			
//			beer.setB_id(Integer.parseInt(request.getParameter("b_id")));
//			beer.setB_code(request.getParameter("b_code"));
//			beer.setB_category(request.getParameter("b_category"));
//			beer.setB_name(request.getParameter("b_name"));
//			beer.setB_country(request.getParameter("b_country"));
//			beer.setB_price(Integer.parseInt(request.getParameter("b_price")));
//			beer.setB_alcohol(request.getParameter("b_alcohol"));
//			beer.setB_content(request.getParameter("b_content"));
//			beer.setB_like(Integer.parseInt(request.getParameter("b_like") ));
//			beer.setB_dislike(Integer.parseInt(request.getParameter("b_dislike")));
//			beer.setB_image("baseImage.jpg");
//			
//			result = beerDAO.updateBeer(beer);
//			
//			if(result ==true){
//				request.getRequestDispatcher("/index.jsp").forward(request, response);
//			}else{
//				request.getRequestDispatcher("/com/yju/2wda/team2/view/etc/error.jsp").forward(request, response);
//			}
//			
//			break;
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request,response);
	}
}
