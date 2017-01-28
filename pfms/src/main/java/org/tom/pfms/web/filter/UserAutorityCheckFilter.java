package org.tom.pfms.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tom.pfms.common.utils.ConstantSettings;

public class UserAutorityCheckFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession(false);
		String url = request.getRequestURL().toString();
		if(!url.endsWith("signon")){
			if(session != null) {
				Object tmp = session.getAttribute(ConstantSettings.LOGIN_USER);
				if(tmp != null) {
					chain.doFilter(req, resp);
				} else {
					String contextPath = request.getContextPath();
					((HttpServletResponse)resp).sendRedirect(contextPath+"/signon");
					return;
				}
			} else {
				String contextPath = request.getContextPath();
				((HttpServletResponse)resp).sendRedirect(contextPath+"/signon");
				return;
			}
		} else {
		    chain.doFilter(req, resp);
		    return;
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
