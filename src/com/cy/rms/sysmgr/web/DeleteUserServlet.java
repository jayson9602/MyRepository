package com.cy.rms.sysmgr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.sysmgr.manager.UserManager;
import com.cy.rms.sysmgr.manager.UserManagerImpl;

public class DeleteUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager userManager = new UserManagerImpl();
		userManager.delete(request.getParameter("id"));
		response.sendRedirect(request.getContextPath() + "/servlet/user/SearchUserServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	
}
