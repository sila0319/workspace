package rwk51_mvc_beer4.model.beer;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.ArrayList;

import rwk51_mvc_beer4.model.beer.BeerDTO;



public class BeerDAO {
		
	private PreparedStatement pstmt = null;
	private Connection con =null;
	
	Context init = null;
	DataSource ds = null;
	ResultSet rs = null;
	

	public BeerDAO() {
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
	
	/**게시판의 모든 레코드를 반환 메서드 - R */
	public ArrayList<BeerDTO> getBeerList(){
	
		ArrayList<BeerDTO> list = new ArrayList<>();
		String SQL = "select * from beer";
		try {
			pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BeerDTO beer = new BeerDTO();
				beer.setB_id(rs.getInt("b_id"));
				beer.setB_code(rs.getString("b_code"));
				beer.setB_category(rs.getString("b_category"));
				beer.setB_name(rs.getString("b_name"));
				beer.setB_country(rs.getString("b_country"));
				beer.setB_price(rs.getInt("b_price"));
				beer.setB_alcohol(rs.getString("b_alcohol"));
				beer.setB_content(rs.getString("b_content"));
				beer.setB_like(rs.getInt("b_like"));
				beer.setB_dislike(rs.getInt("b_dislike"));
				beer.setB_image(rs.getString("b_image"));
				list.add(beer);
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
	
	public ArrayList<BeerDTO> getBeerListForPage(BeerPageInfoVO bpiVO){
		
		ArrayList<BeerDTO> list = new ArrayList<>();
		
		String SQL = "select * from beer order by b_id limit ?,?";
		String SQL2 = "select count(*) from beer";
		ResultSet rs;
		
		try {
			pstmt = con.prepareStatement(SQL2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bpiVO.setRecordCnt(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		bpiVO.adjPageInfo();
		
		try {
			pstmt = con.prepareStatement(SQL);
			
			pstmt.setInt(1, bpiVO.getStartRecord());
			pstmt.setInt(2, bpiVO.getLimitCnt());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BeerDTO beer = new BeerDTO();
				beer.setB_id(rs.getInt("b_id"));
				beer.setB_code(rs.getString("b_code"));
				beer.setB_category(rs.getString("b_category"));
				beer.setB_name(rs.getString("b_name"));
				beer.setB_country(rs.getString("b_country"));
				beer.setB_price(rs.getInt("b_price"));
				beer.setB_alcohol(rs.getString("b_alcohol"));
				beer.setB_content(rs.getString("b_content"));
				beer.setB_like(rs.getInt("b_like"));
				beer.setB_dislike(rs.getInt("b_dislike"));
				beer.setB_image(rs.getString("b_image"));
				list.add(beer);	
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
	
	/**주 키 b_id의 레코드를 반환하는 메서드 - R*/
	public BeerDTO getBeer(int b_id) {
		
		String SQL = "select * from beer where b_id = ?";
		BeerDTO beer = new BeerDTO();
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1,b_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			beer.setB_id(rs.getInt("b_id"));
			beer.setB_code(rs.getString("b_code"));
			beer.setB_category(rs.getString("b_category"));
			beer.setB_name(rs.getString("b_name"));
			beer.setB_country(rs.getString("b_country"));
			beer.setB_price(rs.getInt("b_price"));
			beer.setB_alcohol(rs.getString("b_alcohol"));
			beer.setB_content(rs.getString("b_content"));
			beer.setB_like(rs.getInt("b_like"));
			beer.setB_dislike(rs.getInt("b_dislike"));
			beer.setB_image(rs.getString("b_image"));
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return beer;
	}
	
	/**게시물 등록 메서드 - C*/
	public boolean insertBeer(BeerDTO beer) {
		boolean success = false;
	
		String sql = "insert into beer(b_id, b_code, b_category, b_name, b_country, b_price, b_alcohol, b_content, b_like, b_dislike, b_image) ";
		sql+= "values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  beer.getB_id());
			pstmt.setString(2, beer.getB_code());
			pstmt.setString(3, beer.getB_category());
			pstmt.setString(4, beer.getB_name());
			pstmt.setString(5, beer.getB_country());
			pstmt.setInt(6,  beer.getB_price());
			pstmt.setString(7, beer.getB_alcohol());
			pstmt.setString(8, beer.getB_content());
			pstmt.setInt(9,  beer.getB_like());
			pstmt.setInt(10,  beer.getB_dislike());
			pstmt.setString(11, beer.getB_image());

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
	
	/**데이터 갱신을 위한 메서드 - U*/
	public boolean updateBeer(BeerDTO beer) {
		boolean success = false;
		
		String sql = "update beer set  b_code=?, b_category=?, b_name=?, b_country=?, b_price=?, b_alcohol=?, b_content=?, b_like=?, b_dislike=?, b_image=? ";
		sql+= "where b_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 BeerBO 객체를 이용해 사용자가 수정한 값을 가져와 SQL문 완성
			
			pstmt.setString(1, beer.getB_code());
			pstmt.setString(2, beer.getB_category());
			pstmt.setString(3, beer.getB_name());
			pstmt.setString(4, beer.getB_country());
			pstmt.setInt(5,beer.getB_price());
			pstmt.setString(6, beer.getB_alcohol());
			pstmt.setString(7, beer.getB_content());
			pstmt.setInt(8,  beer.getB_like());
			pstmt.setInt(9,  beer.getB_dislike());
			pstmt.setString(10, beer.getB_image());
			pstmt.setInt(11,  beer.getB_id());

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
	
	public boolean deleteBeer(int b_id) {
		boolean success = false;
		
		String sql = "delete from beer where b_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 주 키인 id 값을 이용해 삭제
			pstmt.setInt(1 , b_id);
			pstmt.executeUpdate();
			success= true;
		}catch(SQLException e) {
			e.printStackTrace();
			return success;
		}finally {
			disConnect();
		}
		return success;
	}
//	public int nextIncrement(String b_code) {
//		String sql = "select b_id from beer where b_code = ?";
//		
//		BeerDTO beer = new BeerDTO();
//		int b_id=1;
//		System.out.println("b_code DAO부분입니다." +b_code);
//		
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1,b_code);
//			ResultSet rs = pstmt.executeQuery();
//			if(rs.next())
//				b_id=(rs.getInt("b_id"));
//			System.out.println("beerDAO b_id값"+b_id);
//			rs.close();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			disConnect();
//		}
//		return  b_id;
//	}
	
//	/**자동생성 코드*/
//	public BeerDTO createBeerCode(BeerCountryDTO BeerCountry,BeerCategoryDTO BeerCategory) {
//	      
//	      String b_code="BE";
//	      
//	      String SQL = "select * from b_category_code where b_category = ?";
//
//	      try {
//	         pstmt = con.prepareStatement(SQL);
//	         pstmt.setString(1,BeerCategory.getB_category());
//	         ResultSet rs = pstmt.executeQuery();
//	         while(rs.next()) {
//	         b_code= b_code + rs.getString("category_code");
//	         }
//	         System.out.println(b_code);
//
//	         rs.close();
//
//	      }catch(SQLException e) {
//	         e.printStackTrace();
//	      }
//	      
//	      SQL = "select * from b_country_code where b_country = ?";
//	      
//	      try {
//	         pstmt = con.prepareStatement(SQL);
//	         pstmt.setString(1,BeerCountry.getB_country());
//	         ResultSet rs = pstmt.executeQuery();
//	         while(rs.next()) {
//	         b_code= b_code + rs.getString("country_code");}
//	         rs.close();
//	         System.out.println(b_code);
//	         
//	         
//	      }catch(SQLException e) {
//	         e.printStackTrace();
//	      }
//	      
//	      
//	      
//	      SQL = "select b_code from beer where b_code like ? order by b_code desc";
//	      
//	      BeerDTO beer = new BeerDTO();
//	      try {
//	         pstmt = con.prepareStatement(SQL);
//	         pstmt.setString(1,b_code+"%");
//	         ResultSet rs = pstmt.executeQuery();
//	         
//	         if(rs.next()) {
//	            int b_code_tail = Integer.parseInt(rs.getString("b_code").substring(7)) + 1;
//	            b_code+=String.format("%04d",b_code_tail);
//	         }
//	         else {
//	            b_code+="0000";
//	         }
//	         rs.close();
//
//	      }catch(SQLException e) {
//	         e.printStackTrace();
//	         
//	      }
//	   
//	      System.out.println(b_code);
//	      beer.setB_code(b_code);
//
//	      return beer;
//	   }
	
}
