package rwk51_boardgame_mall.model.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import rwk51_boardgame_mall.model.boardgame.BoardgameDTO;

public class CartDAO {
	
	private PreparedStatement pstmt = null;
	private Connection con =null;
	
	Context init = null;
	DataSource ds = null;
	ResultSet rs = null;
	
	public CartDAO() {
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
	
	/**장바구니 추가 메서드 - C*/
	public boolean addCart(CartDTO cart) {
		boolean success = false;
	
		String sql = "insert into cart values(?,?,?,?,?,?,?)";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			
			
			pstmt.setInt(1, cart.getC_id());
			pstmt.setInt(2, cart.getUser_num());
			pstmt.setInt(3, cart.getB_id());
			
			pstmt.setInt(4, cart.getB_price());
			pstmt.setInt(5, cart.getB_charge());
			pstmt.setString(6, cart.getB_name());
			pstmt.setInt(7, cart.getC_cnt());
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
	
	/**장바구니 조회 메서드 */
	public ArrayList<CartDTO> getCartList(int user_num) {
		ArrayList<CartDTO> list = new ArrayList<>();

		String SQL = "select * from cart where user_num = ?";
		try {
			
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1,user_num);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartDTO cart = new CartDTO();
				
				cart.setC_id(rs.getInt("c_id"));
				cart.setUser_num(rs.getInt("user_num"));
				cart.setB_id(rs.getInt("b_id"));
				cart.setB_price(rs.getInt("b_price"));
				cart.setB_charge(rs.getInt("b_charge"));
				cart.setB_name(rs.getString("b_name"));
				cart.setC_cnt(rs.getInt("c_cnt"));
				list.add(cart);
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
	
	//전체초기화
	public boolean deleteCart(int user_num) {
		boolean success = false;
	
		String sql = "delete from cart where user_num =? ";
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 주 키인 id 값을 이용해 삭제
			pstmt.setInt(1 , user_num);
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
	
	//카트 부분삭제
	public boolean deleteCartForCID(int c_id) {
		boolean success = false;
	
		String sql = "delete from cart where c_id =? ";
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 주 키인 id 값을 이용해 삭제
			pstmt.setInt(1 , c_id);
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
	
	public boolean chkCart(int b_id, int user_num) {
		boolean success = false;
	
		String sql = "select * from cart where b_id = ? and user_num = ? ";
		System.out.println(sql+"카트중복체크부분입니다.");
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 주 키인 id 값을 이용해 삭제
			pstmt.setInt(1 , b_id);
			pstmt.setInt(2, user_num);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { //값이 존재한다면 false를 반환한다.
				success = false;
			}else {
				success = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return success;
		}finally {
			disConnect();
		}
		System.out.println(success+" 카트 중복체크 결과입니다.");
		return success;
	}

}
