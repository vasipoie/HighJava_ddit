package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	//static 블럭 - JDBCUtil 클래스가 로딩될 때 맨 처음에 딱 한 번 사용
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //reflection. 객체만들때 사용한 클래스정보를 담기위한 
			//오류나면 빌드패스 오류
			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 커넥션 객체 생성하기
	 * @return 
	 */
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc02_highjava", "java");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 자원반납
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		if(stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
}
