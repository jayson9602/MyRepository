package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.basedata.manager.FactoryManager;
import com.cy.rms.basedata.manager.FactoryManagerImpl;

public class AddFactoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Factory factory = new Factory();
		factory.setEmail(request.getParameter("email"));
		factory.setFax(request.getParameter("fax"));
		factory.setFactoryName(request.getParameter("factoryName"));
		factory.setPhone(request.getParameter("phone"));
		factory.setPlace(request.getParameter("place"));
		
		FactoryManager factoryManager = new FactoryManagerImpl();
		factoryManager.addFactory(factory);
		
		response.sendRedirect(request.getContextPath() + "/servlet/factory/SearchFactoryServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	
}
