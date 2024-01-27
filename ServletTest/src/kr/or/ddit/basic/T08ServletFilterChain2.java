package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T08ServletFilterChain2 implements Filter{
	
	@Override
	public void destroy() {
		System.out.println("T08ServletFilterChain2 => destroy() 호출됨.");
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("T08ServletFilterChain2 => doFilter() 시작...");
		
		//서블릿 수행시간 계산
		
		long startTime = System.nanoTime();

		//필터체인 수행하기
		chain.doFilter(req, resp);
		
		long endTime = System.nanoTime();
		
		System.out.println("수행시간(ns) : "+(endTime - startTime));
		
		
		System.out.println("T08ServletFilterChain2 => doFilter() 끝...");
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("T08ServletFilterChain2 => init() 호출됨.");
		
		//초기화 파라미터 가져오기
		String initParam = filterConfig.getInitParameter("init-param");
		
		System.out.println("init-param : "+initParam);
		
	}
}
