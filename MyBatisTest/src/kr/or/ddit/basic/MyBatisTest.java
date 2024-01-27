package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVo;

public class MyBatisTest {
	public static void main(String[] args) {
		
		//myBatis를 이용하여 DB작업을 처리하는 작업 순서
		//1. myBatis 환경설정파일을 읽어와 SqlSessionFactory객체 생성하기
		
		SqlSessionFactory sessionFactory = null;
		
		try {
			//1-1. xml 설정파일 읽어오기
			//설정파일의 인코딩 정보 설정(한글처리를 위해서...)
			Charset charset = Charset.forName("UTF-8");
			
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			//1-2. 위에서 생성한 Reader 객체를 이용하여 SqlSessionFactory 객체 생성
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
		
		//2-1. insert 연습
//		System.out.println("insert 작업 시작...");
//		
//		//1) 저장할 데이터를 VO에 담는다.
//		MemberVo mv = new MemberVo();
//		mv.setMemId("d002");
//		mv.setMemName("자고싶어요");
//		mv.setMemTel("1111-1111");
//		mv.setMemAddr("LA");
//		
//		//2) SqlSession 객체변수를 이용하여 해당 쿼리문을 실행한다.
//		//형식) sqlSession객체.insert("namespace값.id값", 파라미터객체);
//		//반환값 : 성공한 레코드 수
//		SqlSession sqlSession = sessionFactory.openSession(false); //오토커밋여부 설정
//		
//		try {
//			
//			int cnt = sqlSession.insert("memberTest.insertMember", mv);
//			
//			if(cnt > 0) {
//				System.out.println("insert 작업 성공 : "+cnt);
//				sqlSession.commit(); //커밋
//			}else {
//				System.out.println("insert 작업 실패!!!");
//			}
//			
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//		}finally {
//			sqlSession.close(); //세션닫기(반납)
//		}
//		System.out.println("------------------------------------------------");
		
		//2-2. update 연습
//		System.out.println("update 작업 시작...");
//		
//		//1) 저장할 데이터를 VO에 담는다.
//		mv = new MemberVo();
//		mv.setMemId("d001");
//		mv.setMemName("이순신");
//		mv.setMemTel("7777-7777");
//		mv.setMemAddr("대구시 달서구");
//		
//		//2) SqlSession 객체변수를 이용하여 해당 쿼리문 실행한다
//		//형식) sqlSession객체.update("namespace값.id값", 파라미터객체);
//		//반환값 : 성공한 레코드 수
//		sqlSession = sessionFactory.openSession(); //안쓰면 false
//		
//		try {
//			
//			int cnt = sqlSession.update("memberTest.updateMember", mv);
//			
//			if(cnt>0) {
//				System.out.println("update 작업 성공 : "+cnt);
//				sqlSession.commit();
//			}else {
//				System.out.println("update 작업 실패!!!");
//			}
//			
//		} catch (PersistenceException e) {	//MyBatis가 제공하는 예외
//			e.printStackTrace();
//		} finally {
//			sqlSession.close();
//		}
//		System.out.println("------------------------------------------------");
		
		
		//2-3. delete 연습
//		System.out.println("delete 작업 시작...");
//		
//		sqlSession = sessionFactory.openSession();
//		
//		try {
//			int cnt = sqlSession.delete("memberTest.deleteMember","d001");
//			
//			if(cnt>0) {
//				System.out.println("delete 작업 성공 : "+cnt);
//				sqlSession.commit();
//			}else {
//				System.out.println("delete 작업 실패!!!");
//			}
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//		} finally {
//			sqlSession.close();
//		}
//		System.out.println("------------------------------------------------");
		
		
		//2-4. select 연습
		//1) 응답결과가 여러개일 경우...
		System.out.println("select 연습 시작(응답 결과가 여러 개일 경우)...");
		
		//응답 결과가 여러개일 경우에는 selectList()를 이용한다.
		//이 메서드는 여러개의 레코드를 VO에 담은 후 List에 추가해주는 작업을 대신 수행 해 준다.
		SqlSession sqlSession = sessionFactory.openSession(true);
		
		try {
			List<MemberVo> memList = sqlSession.selectList("memberTest.selectAll");
			
			for(MemberVo mv2 : memList) {
				System.out.println("ID : "+mv2.getMemId());
				System.out.println("이름 : "+mv2.getMemName());
				System.out.println("전화 : "+mv2.getMemTel());
				System.out.println("주소 : "+mv2.getMemAddr());
				System.out.println("=======================================");
			}
			System.out.println("전체자료 출력 끝...");
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		
		//2) 응답결과가 1개일 경우...
		System.out.println("select 연습 시작(응답 결과가 1개일 경우)...");
		
		sqlSession = sessionFactory.openSession(true);
		
		//응답결과가 1개일 경우에는 selectOne()메서드를 이용한다.
//		MemberVo mv = new MemberVo();
		MemberVo mv = (MemberVo)sqlSession.selectOne("memberTest.getMember", "b001");
		
		System.out.println("ID : "+mv.getMemId());
		System.out.println("이름 : "+mv.getMemName());
		System.out.println("전화 : "+mv.getMemTel());
		System.out.println("주소 : "+mv.getMemAddr());
		System.out.println("=======================================");
		System.out.println("(응답 결과가 1개일 경우) 출력 끝...");
		
		
	}		
}