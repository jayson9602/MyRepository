package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.manager.FactoryManager;
import com.cy.rms.basedata.manager.FactoryManagerImpl;

public class DeleteFactoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		FactoryManager factoryManager = new FactoryManagerImpl();
		factoryManager.delete(id);
		
		response.sendRedirect(request.getContextPath() + "/servlet/factory/SearchFactoryServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	
}
