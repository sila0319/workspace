package com.boardgame.mall.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.boardgame.mall.dto.UserPageInfoVO;
import com.boardgame.mall.entity.UserDTO;


public class UserDAO {

	 	private PreparedStatement pstmt = null;
	    private Connection con = null;
	    private ResultSet rs = null;

	    public UserDAO() {
	        super();
	        dbConnect();
	    }

	    /** Database connection method */
	    public void dbConnect() {
	        try {
	            // Load MariaDB JDBC driver
	            Class.forName("org.mariadb.jdbc.Driver");

	            // Establish database connection
	            String url = "jdbc:mariadb://localhost:3306/rwk51_boardgame_mall";
	            String username = "root";
	            String password = "maria";
	            con = DriverManager.getConnection(url, username, password);

	            System.out.println("DB connection successful");
	        } catch (Exception e) {
	            System.out.println("DB connection failed");
	            e.printStackTrace();
	        }
	    }

	    /** Database disconnect method */
	    public void disConnect() {
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        /** Add query result set object release part */
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	
	/**회원가입 등록 메서드 - C*/
	public boolean insertUser(UserDTO user) {
		boolean success = false;
	
		String sql = "insert into user(user_num , user_id, user_passwd , user_name, user_RRN, user_email, user_phone ,user_address , user_addressdetail )";
		sql+= "values(?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, user.getUser_num() );
			pstmt.setString(2, user.getUser_id());
			pstmt.setString(3, user.getUser_passwd());
			pstmt.setString(4, user.getUser_name());
			pstmt.setString(5, user.getUser_RRN());
			pstmt.setString(6,  user.getUser_email());
			pstmt.setString(7, user.getUser_phone());
			pstmt.setString(8, user.getUser_address());
			pstmt.setString(9, user.getUser_addressdetail());
		
			System.out.printf("유저 아이디 :%s  유저 비번 : %s 유저 이름 : %s 유저 주번 : %s 유저 이메일 : %s 유저 폰 : %s 유저 주소 : %s 유저 상세주소 : %s \n"
					, user.getUser_id(), user.getUser_passwd(),user.getUser_name(),user.getUser_RRN(),user.getUser_email(),user.getUser_phone() ,user.getUser_address(), user.getUser_addressdetail() );

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
	
	/**유저 로그인 처리*/
	public UserDTO loginUser(String user_id, String user_passwd) {
		boolean success = false;
		UserDTO user = new UserDTO();
	
		
		String sql = "select * from user where user_id = ? and user_passwd = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_passwd);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("loginUser");
				user.setUser_num(rs.getInt("user_num"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_passwd(rs.getString ("user_passwd"));
				user.setUser_class(rs.getString("user_class"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_addressdetail(rs.getString("user_addressdetail"));
			}
			rs.close();

		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			disConnect();
		}
		return user;
	}
	
	/**유저 정보 조회*/
	public ArrayList<UserDTO> getUserList() {
		ArrayList<UserDTO> list = new ArrayList<>();
		String SQL = "select * from user";
		try {
			pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserDTO user = new UserDTO();
				
				user.setUser_num(rs.getInt("user_num"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_passwd(rs.getString("user_passwd"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_RRN(rs.getString("user_RRN"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_class(rs.getString("user_class"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_addressdetail(rs.getString("user_addressdetail"));
				list.add(user);
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
	
	/**유저 정보 수정 - U*/
	public boolean updateUser(UserDTO user) {
		boolean success = false;
		
		String sql = "update user set user_passwd =?, user_name=?, user_RRN=?, user_email=? , user_phone=? , user_class=? , user_address =? , user_addressdetail = ? ";
		sql+= "where user_num =?";
		
		try {
			pstmt = con.prepareStatement(sql);
			//인자로 받은 BeerBO 객체를 이용해 사용자가 수정한 값을 가져와 SQL문 완성
			System.out.println(sql);
			if(user.getUser_class()==null) {
				user.setUser_class("user");
			}
			
			pstmt.setString(1, user.getUser_passwd());
			pstmt.setString(2, user.getUser_name());
			pstmt.setString(3, user.getUser_RRN());
			pstmt.setString(4, user.getUser_email());
			pstmt.setString(5,user.getUser_phone());
			pstmt.setString(6, user.getUser_class());
			
			pstmt.setString(7 , user.getUser_address());
			pstmt.setString(8 , user.getUser_addressdetail());
			pstmt.setInt(9, user.getUser_num());
			
			System.out.println(user.toString());

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
	
	/**유저 정보 삭제*/
	public boolean deleteUser(int user_num) {
		boolean success = false;
		System.out.println("유저정보 삭제 : " );
		String sql = "delete from user where user_num = ?";
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
	
	public ArrayList<UserDTO> getUserListForPage(UserPageInfoVO upiVO){
		
		ArrayList<UserDTO> list = new ArrayList<>();
		
		String SQL = "select * from user order by user_num limit ?,?";
		String SQL2 = "select count(*) from user";
		ResultSet rs;
		
		try {
			pstmt = con.prepareStatement(SQL2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				upiVO.setRecordCnt(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		upiVO.adjPageInfo();
		
		try {
			pstmt = con.prepareStatement(SQL);
			
			pstmt.setInt(1, upiVO.getStartRecord());
			pstmt.setInt(2, upiVO.getLimitCnt());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserDTO user = new UserDTO();
				user.setUser_num(rs.getInt("user_num"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_passwd(rs.getString("user_passwd"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_RRN(rs.getString("user_RRN"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_class(rs.getString("user_class"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_addressdetail(rs.getString("user_addressdetail"));
				list.add(user);	
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
	
	/**유저 pk를 통한 정보조회 */
	public UserDTO getUser(int user_num) {
			
			String SQL = "select * from user where user_num = ?";
			UserDTO user = new UserDTO();
			
			try {
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1,user_num);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
				user.setUser_num(rs.getInt("user_num"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_passwd(rs.getString("user_passwd"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_RRN(rs.getString("user_RRN"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_class(rs.getString("user_class"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_addressdetail(rs.getString("user_addressdetail"));
				
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				disConnect();
			}
			return user;
		}
	
	
	/**회원가입 비동기 처리부분 메소드3개*/
	public boolean chkID(String user_id) {
		boolean success = false;
		String SQL = "select * from user where user_id = ?";
	
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,user_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				success = false;
			}else {
				success = true;
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return success;
	}
	public boolean chkRRN(String user_RRN) {
		boolean success = false;
		String SQL = "select * from user where user_RRN = ?";

		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,user_RRN);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				success = false;
			}else {
				success = true;
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return success;
	}
	
	public boolean chkemail(String user_email) {
		boolean success = false;
		String SQL = "select * from user where user_email = ?";

		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,user_email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				success = false;
			}else {
				success = true;
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return success;
	}
	
	public boolean chkphone(String user_phone) {
		boolean success = false;
		String SQL = "select * from user where user_phone = ?";

		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,user_phone);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				success = false;
			}else {
				success = true;
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return success;
	}
}
