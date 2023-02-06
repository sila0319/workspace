package rwk51_boardgame_mall.model.boardgame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import rwk51_boardgame_mall.model.boardgame.BoardgameDTO;
import rwk51_boardgame_mall.model.user.UserDTO;


public class BoardgameDAO {
	private PreparedStatement pstmt = null;
	private Connection con =null;
	
	Context init = null;
	DataSource ds = null;
	ResultSet rs = null;
	
	public BoardgameDAO() {
		super();
		dbConnect();
	}
	
	/**데이터 베이스 연결 메소드*/
	public void dbConnect(){
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc_mariadb");
			con =  ds.getConnection();
			System.out.println("DB연결 성공");
		}catch(Exception e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}
	
	}
	/**데이터 베이스 연결 해제 메소드*/
	public void disConnect(){
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		/**쿼리결과셋 객체 해제 부분 추가 */
		if(rs!= null) { 
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**상품 등록 메서드 - C*/
	public boolean insertboardgame(BoardgameDTO boardgame) {
		boolean success = false;
	
		String sql = "insert into boardgame values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, boardgame.getB_id());
			
			pstmt.setString(2, boardgame.getB_code());
			pstmt.setString(3, boardgame.getB_category());
			pstmt.setString(4, boardgame.getB_name());
			
			pstmt.setInt(5, boardgame.getB_price());
			pstmt.setInt(6,  boardgame.getB_age());
			
			pstmt.setString(7, boardgame.getB_agestate());
			pstmt.setString(8, boardgame.getB_player());
			pstmt.setString(9, boardgame.getB_time());
			pstmt.setString(10, boardgame.getB_content());
			
			pstmt.setInt(11, boardgame.getB_charge());
			pstmt.setString(12, boardgame.getB_image());
		
		System.out.println(boardgame.toString());
		System.out.println(sql);
			pstmt.executeUpdate();
			success= true;
		}catch(SQLException e) {
			e.printStackTrace();
			return success;
		}
		finally {
			disConnect();
		}
		return success;
	}
	
	/**상품 R메서드 페이징기능포함 카테고리에 따른 게시판분류*/
	public ArrayList<BoardgameDTO> getBoardgameListForPage(String category, BoardgamePageInfoVO bpiVO) {
		ArrayList<BoardgameDTO> list = new ArrayList<>();
		
		String SQL = "select * from boardgame where b_category = ? order by b_id limit ?,?";
		String SQL2 = "select count(*) from boardgame where b_category = ?";
		
		ResultSet rs;
		
			try {
				pstmt = con.prepareStatement(SQL2);
				pstmt.setString(1, category);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					bpiVO.setRecordCnt(rs.getInt(1));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			bpiVO.adjPageInfo();
		
		//String SQL = "select * from boardgame where b_category = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			
			pstmt.setString(1,category);
			pstmt.setInt(2, bpiVO.getStartRecord());
			pstmt.setInt(3, bpiVO.getLimitCnt());
			//ResultSet rs = pstmt.executeQuery();
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardgameDTO boardgame = new BoardgameDTO();
				
				boardgame.setB_id(rs.getInt("b_id"));
				
				boardgame.setB_code(rs.getString("b_code"));
				boardgame.setB_category(rs.getString("b_category"));
				boardgame.setB_name(rs.getString("b_name"));
				
				boardgame.setB_price(rs.getInt("b_price"));
				boardgame.setB_age(rs.getInt("b_age"));
				
				boardgame.setB_agestate(rs.getString("b_agestate"));
				boardgame.setB_player(rs.getString("b_player"));
				boardgame.setB_time(rs.getString("b_time"));
				boardgame.setB_content(rs.getString("b_content"));
				
				boardgame.setB_charge(rs.getInt("b_charge"));
				
				boardgame.setB_image(rs.getString("b_image"));
				
				list.add(boardgame);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			disConnect();
		}
		
		return list;
		
	}
	
	/**보드게임 상세페이지 이동*/
	public ArrayList<BoardgameDTO> getBoardgameInfo(int b_id) {
		ArrayList<BoardgameDTO> list = new ArrayList<>();

		String SQL = "select * from boardgame where b_id = ?";
		try {
			
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1,b_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardgameDTO boardgame = new BoardgameDTO();
				
				boardgame.setB_id(rs.getInt("b_id"));
				
				boardgame.setB_code(rs.getString("b_code"));
				boardgame.setB_category(rs.getString("b_category"));
				boardgame.setB_name(rs.getString("b_name"));
				
				boardgame.setB_price(rs.getInt("b_price"));
				boardgame.setB_age(rs.getInt("b_age"));
				
				boardgame.setB_agestate(rs.getString("b_agestate"));
				boardgame.setB_player(rs.getString("b_player"));
				boardgame.setB_time(rs.getString("b_time"));
				boardgame.setB_content(rs.getString("b_content"));
				
				boardgame.setB_charge(rs.getInt("b_charge"));
				
				boardgame.setB_image(rs.getString("b_image"));
				
				list.add(boardgame);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			disConnect();
		}
		
		return list;
		
	}
	
	
	public ArrayList<BoardgameDTO> searchBoardgame(String b_category, String search, BoardgamePageInfoVO bpiVO) {
		ArrayList<BoardgameDTO> list = new ArrayList<>();
		String s;
		String SQL;
		String SQL2;
		
		ResultSet rs;
		
		try {
			if(b_category.equals("all")) {
				SQL2 = "select count(*) from boardgame where b_name Like ?";
				pstmt = con.prepareStatement(SQL2);
				 s = "%"+search+"%";
				pstmt.setString(1, s);
				
			}else {
				SQL2 = "select count(*) from boardgame where b_name Like ? and b_category = ?";
				pstmt = con.prepareStatement(SQL2);
				 s = "%"+search+"%";
				pstmt.setString(1, s);
				pstmt.setString(2, b_category);
			}
		
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bpiVO.setRecordCnt(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		bpiVO.adjPageInfo();
		
		
		try {
			if(b_category.equals("all")) {
				 SQL = "select * from boardgame where b_name Like ? order by b_id limit ?,?";
				pstmt = con.prepareStatement(SQL);
				 s = "%"+search+"%";
				
				pstmt.setString(1, s);
				pstmt.setInt(2, bpiVO.getStartRecord());
				pstmt.setInt(3, bpiVO.getLimitCnt());
				
			}else {
				 SQL = "select * from boardgame where b_category = ? and b_name Like ? order by b_id limit ?,? ";
				pstmt = con.prepareStatement(SQL);
				 s = "%"+search+"%";
				pstmt.setString(1,b_category);
				pstmt.setString(2, s);
				pstmt.setInt(3, bpiVO.getStartRecord());
				pstmt.setInt(4, bpiVO.getLimitCnt());
			}
			System.out.println(b_category);
			System.out.println(search);
			System.out.println(s+"\n "+SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardgameDTO boardgame = new BoardgameDTO();
				
				boardgame.setB_id(rs.getInt("b_id"));
				
				boardgame.setB_code(rs.getString("b_code"));
				boardgame.setB_category(rs.getString("b_category"));
				boardgame.setB_name(rs.getString("b_name"));
				
				boardgame.setB_price(rs.getInt("b_price"));
				boardgame.setB_age(rs.getInt("b_age"));
				
				boardgame.setB_agestate(rs.getString("b_agestate"));
				boardgame.setB_player(rs.getString("b_player"));
				boardgame.setB_time(rs.getString("b_time"));
				boardgame.setB_content(rs.getString("b_content"));
				
				boardgame.setB_charge(rs.getInt("b_charge"));
				
				boardgame.setB_image(rs.getString("b_image"));
				
				list.add(boardgame);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			disConnect();
		}
		/*
		 * System.out.println(); System.out.println(list.get(0).b_name);
		 */
		return list;
			
	}
	/**상품 수정부분*/
	
	/**보드게임 pk를 통한 정보조회 */
	public BoardgameDTO getBoardgame(int b_id) {
			
			String SQL = "select * from boardgame where b_id = ?";
			BoardgameDTO boardgame = new BoardgameDTO();
			
			try {
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1,b_id);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
				boardgame.setB_id(rs.getInt("b_id"));
				
				boardgame.setB_code(rs.getString("b_code"));
				boardgame.setB_category(rs.getString("b_category"));
				boardgame.setB_name(rs.getString("b_name"));
				
				boardgame.setB_price(rs.getInt("b_price"));
				boardgame.setB_age(rs.getInt("b_age"));
				
				boardgame.setB_agestate(rs.getString("b_agestate"));
				boardgame.setB_player(rs.getString("b_player"));
				boardgame.setB_time(rs.getString("b_time"));
				boardgame.setB_content(rs.getString("b_content"));
				
				boardgame.setB_charge(rs.getInt("b_charge"));
				
				boardgame.setB_image(rs.getString("b_image"));
				
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				disConnect();
			}
			return boardgame;
		}
	
	public boolean updateBoardgame(BoardgameDTO boardgame) {
		boolean success = false;
		String sql = "update boardgame set b_code = ? , b_category = ? , b_name = ? , b_price = ? , b_age = ? , b_agestate = ? , b_player = ? , b_time = ? , b_content = ? , b_charge = ? , b_image = ? ";
		sql+= "where b_id =?";
		
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 BeerBO 객체를 이용해 사용자가 수정한 값을 가져와 SQL문 완성
			
			pstmt.setString(1, boardgame.getB_code());
			pstmt.setString(2, boardgame.getB_category());
			pstmt.setString(3, boardgame.getB_name());
			
			pstmt.setInt(4, boardgame.getB_price());
			pstmt.setInt(5,  boardgame.getB_age());
			
			pstmt.setString(6, boardgame.getB_agestate());
			pstmt.setString(7, boardgame.getB_player());
			pstmt.setString(8, boardgame.getB_time());
			pstmt.setString(9, boardgame.getB_content());
			
			pstmt.setInt(10, boardgame.getB_charge());
			pstmt.setString(11, boardgame.getB_image());
			
			pstmt.setInt(12, boardgame.getB_id());
			
			System.out.println("보드게임 업데이트 부분입니다."+boardgame.toString());
			int rowUdt = pstmt.executeUpdate();
			if(rowUdt == 1) success = true;
		}catch(SQLException e) {
			e.printStackTrace();
			return success;
		}
		finally {
			disConnect();
		}
		return success;
	}
	
	/**상품 삭제*/
	//추후 이부분에 장바구니에 담긴 해당항목 삭제하도록 추가.
	public boolean deleteBoardgame(int b_id) {
		boolean success = false;
		String sql = "delete from boardgame where b_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_id);
			pstmt.executeUpdate();
			success = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return success;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
