package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSession객체를 제공하기 위한 클래스
 * @author PC-25
 *
 */
public class MyBatisUtil {
	private static SqlSessionFactory sessionFactory; //객체 변수 선언
	
	//sessionFactory 초기화
	static {
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
	}
	
	/**
	 * SqlSession객체를 생성하기 위한 팩토리 메서드
	 * @return SqlSession 객체
	 */
	public static SqlSession getInstance() {
		return sessionFactory.openSession(); //기본값: autoCommit => false
	}
	
	/**
	 * SqlSession객체를 생성하기 위한 팩토리 메서드
	 * @param autoCommit 오토커밋 여부
	 * @return SqlSession 객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sessionFactory.openSession(autoCommit); //기본값: autoCommit => false
	}
}
