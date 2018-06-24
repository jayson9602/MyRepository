package com.cy.rms.sysmgr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.sysmgr.manager.UserManager;
import com.cy.rms.sysmgr.manager.UserManagerImpl;
import com.cy.rms.util.ApplicationException;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String strError = "";
		User user = null;
		
		UserManager userManager = new UserManagerImpl();
		try {
			user = userManager.login(userName, password);
			session.setAttribute("user",user);
			response.sendRedirect(request.getContextPath() + "/UserLogin.jsp");
		} catch(ApplicationException e) {
			strError = e.getMessage();
			request.setAttribute("strError", strError);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} 
		
	}

}
