package com.cy.rms.basedata.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.domain.Category;
import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.basedata.manager.CategoryManager;
import com.cy.rms.basedata.manager.CategoryManagerImpl;
import com.cy.rms.basedata.manager.FactoryManager;
import com.cy.rms.basedata.manager.FactoryManagerImpl;

public class ShowAddProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryManager categoryManager = new CategoryManagerImpl();
		FactoryManager factoryManager = new FactoryManagerImpl();
		
		List<Category> categoryList = categoryManager.getAllCategories();
		List<Factory> factoryList = factoryManager.getAllFactorys();
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("factoryList", factoryList);
		
		request.getRequestDispatcher("/basedata/productAddPage.jsp").forward(request, response);
	}

	
}
