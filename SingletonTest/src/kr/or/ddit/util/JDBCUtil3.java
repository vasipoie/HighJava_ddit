package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCUtil3 {
	/*
		db.properties파일을 이용하여 db설정 정보를 관리하는 방법
		방법2) ResourceBundle 객체 이용하기
	*/
	
	static ResourceBundle bundle;
	
	
	//static 블럭 - JDBCUtil 클래스가 로딩될 때 맨 처음에 딱 한 번 사용
	static {
		try {
			
			//로딩이 되는 시점에 초기화
			bundle = ResourceBundle.getBundle("db");
			
			Class.forName(bundle.getString("driver"));
			
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
			return DriverManager.getConnection(bundle.getString("url")
												, bundle.getString("username")
												, bundle.getString("password"));
			
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
