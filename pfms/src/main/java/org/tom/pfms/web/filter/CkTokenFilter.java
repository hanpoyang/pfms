package org.tom.pfms.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;


public class CkTokenFilter implements Filter {
    private  static final String actions = "DELETE|SAVE|UPDATE|INSERT|ADD";
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession(false);
		String url = request.getRequestURL().toString();
		if(!url.endsWith("signon")){
			String token = request.getParameter("token");
			System.out.println("##token::"+token);
			if(token == null) {
				token = request.getQueryString();
				System.out.println(token);
				token = token == null ? null : getToken(token);
			}
			
			System.out.println("============================token:::::"+token);
			if(session != null) {
				Object obj = session.getAttribute(ConstantSettings.KEY_TOKEN);
				System.out.println("============================obj:::::"+obj);
				
				if((token == null || obj == null) && instr(url)){
					ServletOutputStream out = null;
					try{
						out = resp.getOutputStream();
						resp.setContentType("text/html;charset=UTF-8");
						out.println("<!doctype html><html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\" /></head><body>");
						out.write(MessageUtils.getMessage(ConstantSettings.LABEL_BAN_REDO_WARNING).getBytes());
						out.println("</body></html>");
						out.flush();
					}catch(IOException e) {
						Logger.getLogger(CkTokenFilter.class).error("doFilter", e);
					}finally {
						if(null != out) {
						    out.close();
						}
					}
					return;
				}
				
				if(obj != null) {
					if(!obj.toString().equals(token)) {
						PrintWriter out = null;
						try{
							out = resp.getWriter();
							out.println("<script type=\"text/javascript\">");
							out.println("history.back(-1);");
							out.println("</script>");
						}catch(IOException e) {
							Logger.getLogger(CkTokenFilter.class).error("doFilter", e);
						}finally{
							if(null != out) {
							    out.close();
							}
						}
						session.setAttribute(ConstantSettings.KEY_TOKEN, null);
						return;
					}
					session.setAttribute(ConstantSettings.KEY_TOKEN, null);
				} 
			} else {
				String contextPath = request.getContextPath();
				((HttpServletResponse)resp).sendRedirect(contextPath+"/signon");
				return;
			}
		}
		
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	private static String getToken(String queryString) {
		Pattern ptn = Pattern.compile("token=([^&].*)");
		String result = null;
		Matcher m = ptn.matcher(queryString);
		if(m.find()) {
			result = m.group(1);
		}
		return result;
	}
	
	private static boolean instr(String str) {
		boolean result = false;
		result = str.toUpperCase().matches(".*?/"+actions+"$");
		return result;
	}
	

	public static void main(String[] a) {
		String url = "http://localhost:8080/pfms/otheraccounts/accounts";
		System.out.println(instr(url));
	}

}
