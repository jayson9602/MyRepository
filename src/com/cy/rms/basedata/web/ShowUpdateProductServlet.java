package com.cy.rms.basedata.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cy.rms.basedata.domain.Category;
import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.basedata.domain.Product;
import com.cy.rms.basedata.manager.CategoryManager;
import com.cy.rms.basedata.manager.CategoryManagerImpl;
import com.cy.rms.basedata.manager.FactoryManager;
import com.cy.rms.basedata.manager.FactoryManagerImpl;
import com.cy.rms.basedata.manager.ProductManager;
import com.cy.rms.basedata.manager.ProductManagerImpl;

public class ShowUpdateProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		ProductManager productManager = new ProductManagerImpl();
		Product product = productManager.findProductById(id);
		request.setAttribute("product", product);
		
		CategoryManager categoryManager = new CategoryManagerImpl();
		List<Category> categoryList = categoryManager.getAllCategories();
		request.setAttribute("categoryList", categoryList);
		
		FactoryManager factoryManager = new FactoryManagerImpl();
		List<Factory> factoryList = factoryManager.getAllFactorys();
		request.setAttribute("factoryList", factoryList);
		
		request.getRequestDispatcher("/basedata/productUpdate.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	
}
