package com.cy.rms.sysmgr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.sysmgr.manager.UserManager;
import com.cy.rms.sysmgr.manager.UserManagerImpl;

public class AddUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setUserType(Integer.parseInt(request.getParameter("userType")));
		
		UserManager userManager = new UserManagerImpl();
		userManager.addUser(user);
		response.sendRedirect(request.getContextPath() + "/servlet/user/SearchUserServlet");
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
