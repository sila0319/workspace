package rwk51_mvc_beer3.model.beer;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.ArrayList;
import rwk51_mvc_beer3.model.beer.BeerImageDTO;


public class BeerImageDAO {
	private PreparedStatement pstmt = null;
	private Connection con =null;
	
	Context init = null;
	DataSource ds = null;
	ResultSet rs = null;
	
	String imageDir = "/rwk51_mvc_beer3/image/";
	String orginalImageDir = imageDir+"original/";
	String thumbImageDir = imageDir+"thumb/";
	
	public BeerImageDAO() {
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
	
	/**이미지 넣는 메소드 */
	public boolean insertBeerImage(BeerImageDTO beerImage) {
		boolean success = false;
		String sql = "insert into beerimage (i_file_name, i_original_name, i_thumbnail_name, i_file_size, i_f_id)";
		sql += "values (?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  beerImage.getI_file_name());
			pstmt.setString(2, beerImage.getI_original_name());
			pstmt.setString(3, beerImage.getI_thumbnail_name());
			pstmt.setLong(4, beerImage.getI_file_size());
			pstmt.setInt(5, beerImage.getI_f_id());
			

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
	
	public ArrayList<BeerVO> getBeerListForPage(BeerImagePageInfoVO beerImagePageInfoVO){
		String SQL = null;
		String SQL2 = null;
		ArrayList<BeerVO> list = new ArrayList<>();
		
		String beerType = beerImagePageInfoVO.getDisplayBeer();
		System.out.println("beerType은"+beerType );
		switch(beerType) {
		case "all" :
			 SQL = "select * from beer as b join beerimage as bi on b.b_id = bi.i_f_id order by b_id limit ?,?";
			 SQL2 = "select count(*) from beer as b join beerimage as bi on b.b_id = bi.i_f_id";
			break;
		case "beerCounty":
			 SQL = "select * from beer as b join beerimage as bi on b.b_id = bi.i_f_id order by b_country limit ?,?";
			 SQL2 = "select count(*) from beer as b join beerimage as bi on b.b_id = bi.i_f_id";
			break;
		case "beerName":
			 SQL = "select * from beer as b join beerimage as bi on b.b_id = bi.i_f_id order by b_name limit ?,?";
			 SQL2 = "select count(*) from beer as b join beerimage as bi on b.b_id = bi.i_f_id ";
			break;
		
		}
		// SQL = "select * from beer as b join beerimage as bi on b.b_id = bi.i_f_id order by b_id limit ?,?";
		// SQL2 = "select count(*) from beer as b join beerimage as bi on b.b_id = bi.i_f_id";
		ResultSet rs;
		
		try {
			pstmt = con.prepareStatement(SQL2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				beerImagePageInfoVO.setRecordCnt(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		beerImagePageInfoVO.adjPageInfo();
		
		try {
			pstmt = con.prepareStatement(SQL);
			
			pstmt.setInt(1, beerImagePageInfoVO.getStartRecord());
			pstmt.setInt(2, beerImagePageInfoVO.getLimitCnt());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BeerVO beerVO = new BeerVO();
				beerVO.setB_id(rs.getInt("b_id"));
				beerVO.setB_code(rs.getString("b_code"));
				beerVO.setB_category(rs.getString("b_category"));
				beerVO.setB_name(rs.getString("b_name"));
				beerVO.setB_country(rs.getString("b_country"));
				beerVO.setB_price(rs.getInt("b_price"));
				beerVO.setB_alcohol(rs.getString("b_alcohol"));
				beerVO.setB_content(rs.getString("b_content"));
				beerVO.setB_like(rs.getInt("b_like"));
				beerVO.setB_dislike(rs.getInt("b_dislike"));
				beerVO.setB_image(rs.getString("b_image"));
				
				beerVO.setI_id(rs.getInt("i_id"));
				beerVO.setI_file_name(rs.getString("i_file_name"));
				beerVO.setI_original_name(rs.getString("i_original_name"));
				beerVO.setI_thumbnail_name(rs.getString("i_thumbnail_name"));
				beerVO.setI_file_size(rs.getLong("i_file_size"));
				beerVO.setI_f_id(rs.getInt("i_f_id"));
				list.add(beerVO);
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return list;
	}
	
	/**게시판의 모든 레코드를 반환 메서드 - R */
	public ArrayList<BeerVO> getBeerList(){
	
		ArrayList<BeerVO> list = new ArrayList<>();
		String SQL = "select * from beer as b join beerimage as bi on b.b_id = bi.i_f_id;";
		try {
			pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BeerVO beerVO = new BeerVO();
				beerVO.setB_id(rs.getInt("b_id"));
				beerVO.setB_code(rs.getString("b_code"));
				beerVO.setB_category(rs.getString("b_category"));
				beerVO.setB_name(rs.getString("b_name"));
				beerVO.setB_country(rs.getString("b_country"));
				beerVO.setB_price(rs.getInt("b_price"));
				beerVO.setB_alcohol(rs.getString("b_alcohol"));
				beerVO.setB_content(rs.getString("b_content"));
				beerVO.setB_like(rs.getInt("b_like"));
				beerVO.setB_dislike(rs.getInt("b_dislike"));
				beerVO.setB_image(rs.getString("b_image"));
				
				beerVO.setI_id(rs.getInt("i_id"));
				beerVO.setI_file_name(rs.getString("i_file_name"));
				beerVO.setI_original_name(rs.getString("i_original_name"));
				beerVO.setI_thumbnail_name(rs.getString("i_thumbnail_name"));
				beerVO.setI_file_size(rs.getLong("i_file_size"));
				beerVO.setI_f_id(rs.getInt("i_f_id"));
				list.add(beerVO);
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
	
	
}
