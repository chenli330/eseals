package cn.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

public class SecurityCodeFitle extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		String securityCode = httpServletRequest.getParameter("securityCode");
		String code = (String) httpServletRequest.getSession().getAttribute("securityCode");
		if (securityCode == null) {
			return true;
		}
		try {
			if (securityCode.toUpperCase().equals(code.toUpperCase())) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			httpServletRequest.getSession().setAttribute("error", "验证码错误");
			return false;
		}
		// 如果验证码失败了，存储失败key属性
		httpServletRequest.getSession().setAttribute("error", "验证码错误");
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
		httpServletResponse.sendRedirect("login");
		return false;
	}

}
