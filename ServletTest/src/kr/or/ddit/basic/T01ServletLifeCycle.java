package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 라이프사이클을 확인하기 위한 예제
 * @author PC-25
 *
 */
public class T01ServletLifeCycle extends HttpServlet{
/*
 	서블릿(Servlet)이란?
 	
 	서블릿 컨테이너(서블릿엔진)에 의해 관리되는 자바기반 웹 컴포넌트로서, 
 	동적인 웹컨텐츠 생성을 가능하게 해준다.
 */
	
	public T01ServletLifeCycle() {
		System.out.println("T01ServletLifeCycle 생성자 호출됨");
	}
	
	@Override
	public void init() throws ServletException {
		// 초기화 코드 작성 위치...
		System.out.println("init() 호출됨");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//실제적인 작업수행이 시작되는 지점...(자바의 메인 메서드 역할)
		System.out.println("service() 메서드 호출됨");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//메서드 방식이 GET인 경우 호출됨 - 가져오고싶을 때. 게시물 목록조회, 주소를 직접 입력, 링크 타고 들어가는거
		System.out.println("doGet() 호출됨");
		
		throw new ServletException("서블릿 예외가 발생했어요!!!!!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//메서드 방식이 POST인 경우 호출됨 - form태그. 보내고 싶을 때.
		System.out.println("doPost() 호출됨");
	}
	
	@Override
	public void destroy() {
		//객체 소멸시(컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성 -close()
		System.out.println("destroy() 호출됨");
	}
}
