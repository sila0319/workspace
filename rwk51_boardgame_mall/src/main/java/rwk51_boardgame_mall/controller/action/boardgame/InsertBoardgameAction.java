package rwk51_boardgame_mall.controller.action.boardgame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import rwk51_boardgame_mall.controller.Action;
import rwk51_boardgame_mall.controller.ActionForward;
import rwk51_boardgame_mall.model.boardgame.*;


public class InsertBoardgameAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("insertBoardgameAction");
		
		int maxSize = 1024*1024*5;
		/*
		 * String imgDirPath
		 * ="D:\\dev\\workspace\\rwk51_boardgame_mall\\src\\main\\webapp\\image\\original\\";
		 * String thumbimgDirPath=
		 * "D:\\dev\\workspace\\rwk51_boardgame_mall\\src\\main\\webapp\\image\\thumb\\";
		 */
	
		ServletContext application = request.getServletContext();
	      String path = application.getRealPath("/");
	     // String imgDirPath = path + "com\\yju\\2wda\\team1\\image\\memberImage\\";
	      String imgDirPath = path + "image\\original\\";
	      
	      // 파일 관련
	      //String imgDirPath = "D:\\project\\WorkOutNow-Page\\kjw59_project\\src\\main\\webapp\\com\\yju\\2wda\\team1\\image\\memberImage\\";
	      String thumbimgDirPath = path +"image\\thumb\\";
	      
		BoardgameDAO boardgameDAO = new BoardgameDAO();
		BoardgameDTO boardgame = new BoardgameDTO();
		MultipartRequest multi = new MultipartRequest(request,imgDirPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		boardgame.setB_code(multi.getParameter("b_code"));
		boardgame.setB_category(multi.getParameter("b_category"));
		boardgame.setB_name(multi.getParameter("b_name"));
		boardgame.setB_price(Integer.parseInt(multi.getParameter("b_price")));
		boardgame.setB_age(Integer.parseInt(multi.getParameter("b_age")));
		boardgame.setB_agestate(multi.getParameter("b_agestate"));

		
		int player1 = Integer.parseInt(multi.getParameter("b_player1"));
		int player2 = Integer.parseInt(multi.getParameter("b_player2"));
		String temp = "";
		if(player1<0)  //혹시 모를 음수 대비
			player1 = player1*-1;
		if(player2<0)
			player2 = player2*-1;
		
		if(player1> player2) {
			temp=player2+"~"+player1;
		}else if(player1<player2) {
			temp=player1+"~"+player2;
		}else {
			temp= player1+"";
		}
		boardgame.setB_player(temp);
		
		
		int time1 = Integer.parseInt(multi.getParameter("b_time1"));
		int time2 = Integer.parseInt(multi.getParameter("b_time2"));
	
		if(time1<0)  //혹시 모를 음수 대비
			time1 = time1*-1;
		if(time2<0)
			time2 = time2*-1;
		
		if(time1> time2) {
			temp=time2+"~"+time1;
		}else if(time1<time2) {
			temp=time1+"~"+time2;
		}else {
			temp= time1+"";
		}
		boardgame.setB_time(temp);
		
		boardgame.setB_content(multi.getParameter("b_content"));
		boardgame.setB_charge(Integer.parseInt(multi.getParameter("b_charge")));
		
		
		/**기본사진 넣는 부분*/
		Enumeration<?> files = multi.getFileNames();
		if(files.hasMoreElements()) {
			String element = (String)files.nextElement();
			System.out.println(element);
			
			String t = multi.getOriginalFileName(element);
			
			if(t!=null) {
				boardgame.setB_image(t);
				System.out.println(boardgame.getB_image());
				String file_name = multi.getFilesystemName(element);
			
			String oPath = imgDirPath+file_name; // 원본 경로
			   File oFile = new File(oPath);
			   
			   int index = oPath.lastIndexOf(".");
			   String ext = oPath.substring(index + 1); // 파일 확장자;
			   
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
			}else {
				boardgame.setB_image("baseImage.jpg");
			}
			      
			
		}
	
		
	
		

		System.out.println(boardgame.toString());
		boolean result = boardgameDAO.insertboardgame(boardgame);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		if(result) {
			forward.setPath("/index.jsp");
		}else {
			forward.setPath("/com/yju/2wda/ryu/view/etc/userError.jsp");
		}
		System.out.println("insertUser");
		return forward;
	}

}
