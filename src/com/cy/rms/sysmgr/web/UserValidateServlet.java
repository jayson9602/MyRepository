package com.cy.rms.sysmgr.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.sysmgr.manager.UserManager;
import com.cy.rms.sysmgr.manager.UserManagerImpl;

public class UserValidateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charaet=UTF-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		UserManager userManager = new UserManagerImpl();
		//System.out.println(userName);
		if (userManager.checkExist(userName)==true) {
			out.println("Exist");
		}
		
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
