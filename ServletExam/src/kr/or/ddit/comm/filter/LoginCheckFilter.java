package kr.or.ddit.comm.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//로그인 없이 허용 가능한 URL 목록
		List<String> okUrlList = Arrays.asList("/login.do", "/member/list.do");
		
		String reqURI = req.getRequestURI();
		
		boolean isAllowed = okUrlList.contains(reqURI.substring(req.getContextPath().length()));
		
		HttpSession session = req.getSession(false);
		
		if(isAllowed || (session != null && session.getAttribute("LOGIN_USER") != null)) {
			//허용가능한 url이거나 로그인 한 사람이라면,
			
			//다시 서블릿 요청으로 날림
			chain.doFilter(req, resp);
			
		}else {
			
			Enumeration<String> paramNames = req.getParameterNames();
			
			//쿼리스트링으로 날라온 요청을 쓰려고
			StringBuffer queryStrBuff = new StringBuffer();
			
			int cnt = 0;
			
			while(paramNames.hasMoreElements()) {
				
				String paramName = paramNames.nextElement();
				
				if(cnt == 0) {
					queryStrBuff.append("?");
				}else {
					queryStrBuff.append("&");
				}
				queryStrBuff.append(paramName);
				queryStrBuff.append("=");
				queryStrBuff.append(req.getParameter(paramName));
				
				cnt++;
				
			}
			
			req.getSession().setAttribute("redirectURL", reqURI+queryStrBuff.toString());
			
			resp.sendRedirect(req.getContextPath()+"/login.do");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
