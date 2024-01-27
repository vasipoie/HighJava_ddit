package kr.or.ddit.member.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil3;

/*
 * MemberMain.java dao빼기 전
 */


/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력		---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class MemberMain2 {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan;
	
	//생성자가 호출되는 시점에 모아놓으려고
	public MemberMain2() {
		scan = new Scanner(System.in); 
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	private void printBar() {
		System.out.println("--------------------------------------------------------------");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice = 0;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					selectAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}

	/**
	 * 전체 회원정보를 조회하기 위한 메서드
	 */
	private void selectAll() {
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from mymember";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				//TimeStamp를 LocalDate타입으로 변환하기
				LocalDate regDate = rs.getTimestamp("reg_dt").toLocalDateTime().toLocalDate();
//				LocalDateTime regDate = rs.getTimestamp("reg_dt").toLocalDateTime(); //시간까지 필요
				
				printBar();
				System.out.println("회원ID\t등록일\t\t회원명\t\t전화번호\t\t주소");
				System.out.println(memId + "\t"+regDate+ "\t" + memName + "\t" + memTel+ "\t"+memAddr);
				printBar();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보를 삭제하기 위한 메서드
	 */
	private void deleteMember() {
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력해주세요");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "인 회워정보 삭제 성공!");
			}else {
				System.out.println(memId + "인 회워정보 삭제 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보를 수정하기 위한 메서드
	 */
	private void updateMember() {
		boolean isExist = false;
		String memId = null;
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력해주세요");
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			isExist = checkMember(memId);
			
			if(!isExist) {
				System.out.println("회원ID가 "+memId + "인 회원이 없습니다.\n다시 입력해주세요.");
			}
		} while (!isExist);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼에 있는 엔터키 비우기
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "UPDATE mymember \r\n" + 
						 " SET mem_name = ?, \r\n" + 
						 " mem_tel = ?, \r\n" + 
						 " mem_addr = ? \r\n" + 
						 " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "인 회원 정보 수정 성공!");
			}else {
				System.out.println(memId + "인 회원 정보 수정 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보를 등록하기 위한 메서드
	 */
	private void insertMember() {
		boolean isExist = false;
		String memId = null;
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력해주세요");
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			isExist = checkMember(memId);
			
			if(isExist) {
				System.out.println("회원ID가 "+memId + "인 회원이 이미 존재합니다.\n다시 입력해주세요.");
			}
		} while (isExist);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼에 있는 엔터키 비우기
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "INSERT INTO mymember(mem_id, mem_name, mem_tel, mem_addr)\r\n" + 
						 " VALUES (?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "인 회원 정보 추가 성공!");
			}else {
				System.out.println(memId + "인 회원 정보 추가 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원이 존재하는지 체크하기 위한 메서드
	 * @param memId 존재여부 체크하기 위한 회원ID
	 * @return 회원이 존재하면 true, 존재하지않으면 false 리턴
	 */
	private boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "select count(*) cnt\r\n" + 
						 " from mymember\r\n" + 
						 " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//꺼내서 한 줄 한 줄 씩 접근. count쿼리는 1개밖에 없으니까 if로. 여러개면 while
				int cnt = rs.getInt("cnt");
				
				if(cnt>0) {
					isExist = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}

	public static void main(String[] args) {
		MemberMain2 memObj = new MemberMain2();
		memObj.start();
	}
}






