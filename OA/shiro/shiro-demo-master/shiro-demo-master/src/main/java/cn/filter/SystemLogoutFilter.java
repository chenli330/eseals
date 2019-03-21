package cn.filter;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

public class SystemLogoutFilter extends LogoutFilter {

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/*Subject subject = getSubject(request, response);
		String username = (String) subject.getPrincipal();
		HttpServletRequest req = WebUtils.toHttp(request);
		ServletContext application = req.getSession().getServletContext();
		if (username != null) {
			application.setAttribute(username, null);
		}*/
		return super.preHandle(request, response);
	}

}
