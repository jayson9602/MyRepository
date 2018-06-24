package com.cy.rms.util.filter;

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

import com.cy.rms.sysmgr.domain.User;

public class ValidateFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		
		String requestURI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/"), req.getRequestURI().length());
		//System.out.println("requestURI=" + requestURI);
		
		if (!"/login.jsp".equals(requestURI) && !"/LoginServlet".equals(requestURI)) {
			if(user == null) {
				//如果session中的user为空，则无法访问页面
				String strError = "密码过期，请重新登录";
				req.setAttribute("strError", strError);
				req.getRequestDispatcher("/login.jsp").forward(req, res);
				//res.sendRedirect(req.getContextPath() + "/login.jsp");
				return;
			} else if((requestURI.contains("Add") || requestURI.contains("Up") || "/SearchUserServlet".equals(requestURI) ) && user.getUserType()==2) {
				//如果登录身份是老师则无权访问添加、修改页面和用户管理页面
				String strError = "您没有管理员权限";
				req.setAttribute("strError", strError);
				req.getRequestDispatcher("/errorPage.jsp").forward(req, res);
				return;
			}
		}
		
		//继续访问其他资源
		chain.doFilter(req, res);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
