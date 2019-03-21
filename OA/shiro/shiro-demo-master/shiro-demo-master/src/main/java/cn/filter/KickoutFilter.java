package cn.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class KickoutFilter extends AccessControlFilter {

	private String kickoutUrl; // 踢出后到的地址
	private SessionManager sessionManager;

	public String getKickoutUrl() {
		return kickoutUrl;
	}

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Subject subject = getSubject(request, response);
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			// 如果没有登录，直接进行之后的流程
			return true;
		}
		String username = (String) subject.getPrincipal();
		Session session = subject.getSession();
		String sessionId = (String) session.getId();

		HttpServletRequest req = WebUtils.toHttp(request);
		ServletContext application = req.getSession().getServletContext();
		Deque<Object> deque = deque = (Deque<Object>) application.getAttribute(username);
		if (deque == null) {
			deque = new LinkedList<>();
			deque.push(sessionId);
			application.setAttribute(username, deque);
			return true;
		}
		if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
			deque.push(sessionId);
		}
		while (deque.size() > 1) {
			String kickSessionId = (String) deque.removeLast();
			if (kickSessionId != null) {
				Session kickoutSession = null;
				try {
					kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickSessionId));
					kickoutSession.setAttribute("kickout", true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
				
			}
		}
		if (session.getAttribute("kickout") != null) {
			subject.logout();
			saveRequest(request);
			req.setAttribute("kickMsg", "该账号异地登录，您被强制下线");
			WebUtils.issueRedirect(request, response, kickoutUrl);
			return false;
		}
		return true;
	}

}
