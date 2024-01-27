package kr.or.ddit.basic;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener, HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeAdded 호출됨."
				+ hsbe.getName() + " => " +hsbe.getValue());
		
		if(hsbe.getName().equals("LOGIN_USER")) { //로그인 성공
			
			ServletContext ctx = hsbe.getSession().getServletContext();
			
			Integer cnt = (Integer) ctx.getAttribute("LOGIN_USER_CNT");
			if(cnt==null) {
				ctx.setAttribute("LOGIN_USER_CNT", 1);
			}else {
				ctx.setAttribute("LOGIN_USER_CNT", ++cnt);
			}
			
			System.out.println("현재 로그인 한 사용자 수 : " + ctx.getAttribute("LOGIN_USER_CNT"));
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeRemoved 호출됨."
				+ hsbe.getName() + " => " +hsbe.getValue());
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		System.out.println("[MyHttpSessionListener] attributeReplaced 호출됨."
				+ hsbe.getName() + " => " +hsbe.getValue());
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("[MyHttpSessionListener] sessionCreated 호출됨."
				+ "생성된 세션 ID => " + se.getSession().getId());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("[MyHttpSessionListener] sessionDestroyed 호출됨."
				+ "소멸된 세션 ID => " + se.getSession().getId());
		Object obj = se.getSession().getAttribute("LOGIN_USER");
		
		if(obj != null) { //로그인 했던 사용자라면,
			ServletContext ctx = se.getSession().getServletContext();
			
			Integer cnt = (Integer) ctx.getAttribute("LOGIN_USER_CNT");
			
			if(cnt == null) {
				ctx.setAttribute("LOGIN_USER_CNT", 0);
			}else {
				ctx.setAttribute("LOGIN_USER_CNT", --cnt);
			}
			System.out.println("현재 로그인 한 사용자 수 : " + ctx.getAttribute("LOGIN_USER_CNT"));
		}
	}
}
