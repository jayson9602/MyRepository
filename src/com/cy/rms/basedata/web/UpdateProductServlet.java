package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.domain.Category;
import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.basedata.domain.Product;
import com.cy.rms.basedata.manager.ProductManager;
import com.cy.rms.basedata.manager.ProductManagerImpl;

public class UpdateProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Category category = new Category();
		category.setId(Integer.parseInt(request.getParameter("categoryId")));
		
		Factory factory = new Factory();
		factory.setId(Integer.parseInt(request.getParameter("factoryId")));
		
		Product product = new Product();
		product.setId(Integer.parseInt(request.getParameter("id")));
		product.setName(request.getParameter("name"));
		product.setPriceIn(Float.parseFloat(request.getParameter("priceIn")));
		product.setPriceOut(Float.parseFloat(request.getParameter("priceOut")));
		product.setSell(Integer.parseInt(request.getParameter("sell")));
		product.setStock(Integer.parseInt(request.getParameter("stock")));
		product.setCategory(category);
		product.setFactory(factory);
		
		ProductManager productManager = new ProductManagerImpl();
		productManager.update(product);
		
		response.sendRedirect(request.getContextPath()+"/servlet/product/SearchProductServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	
}
