package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.domain.Product;
import com.cy.rms.basedata.manager.ProductManager;
import com.cy.rms.basedata.manager.ProductManagerImpl;

public class ShowUploadProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProductManager productManager = new ProductManagerImpl();
		Product product = productManager.findProductById(id);
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("/basedata/productUpload.jsp").forward(request, response);
	}

}
