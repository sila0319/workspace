package jwbook;

import java.sql.*;

import java.util.Scanner;


class test{
	
	
}
public class ConnectDatabase {
	static Scanner sc = new Scanner(System.in);
	public static Connection con;
	public static PreparedStatement pstmt;
	public static ResultSet rs;
	
	public ConnectDatabase() {
		String url = "jdbc:mariadb://localhost/book_db";
		String id = "root";
		String password = "maria";
		Connection con = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터 베이스 연결 성공");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		}catch (SQLException e ) {
			System.out.println("연결에 실패하였습니다.");
		}
	
		
	}
	
	public static void dbinsert() throws SQLException {
		String SQL = "INSERT into books(book_id , title, publisher, year, price) values(?,?,?,?,?)";
		try {
		pstmt = con.prepareStatement(SQL);
		System.out.println("book_id(int), title(String), publisher(String), year(String), price(int)형으로 입력해주세요");
		pstmt.setInt(1, sc.nextInt());
		pstmt.setString(2, sc.next());
		pstmt.setString(3, sc.next());
		pstmt.setString(4, sc.next());
		pstmt.setInt(5, sc.nextInt());
		
		pstmt.setInt(1, sc.nextInt());
		pstmt.setString(2, sc.next());
		pstmt.setString(3, sc.next());
		pstmt.setString(4, sc.next());
		pstmt.setInt(5, sc.nextInt());
		
		
		}catch(SQLException e) {
			System.out.println("SQL error"+e.getMessage());
		} finally {

            //사용순서와 반대로 close 함
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
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
        }
		
	}
	
	
}
