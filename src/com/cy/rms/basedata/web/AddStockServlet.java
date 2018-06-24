package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cy.rms.basedata.domain.Product;
import com.cy.rms.basedata.domain.Stock;
import com.cy.rms.basedata.manager.ProductManager;
import com.cy.rms.basedata.manager.ProductManagerImpl;
import com.cy.rms.basedata.manager.StockManager;
import com.cy.rms.basedata.manager.StockManagerImpl;
import com.cy.rms.sysmgr.domain.User;

public class AddStockServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession(); 
		User u = (User)session.getAttribute("user");

		Stock stock = new Stock();
		stock.setRemark(request.getParameter("remark"));
		stock.setAmount(Integer.parseInt(request.getParameter("amount")));
		
		ProductManager productManager = new ProductManagerImpl();
		Product product = productManager.findProductById(Integer.parseInt(request.getParameter("productId")));
		product.setStock(product.getStock() + Integer.parseInt(request.getParameter("amount")));
		productManager.update(product);
		
		stock.setProduct(product);
		
		stock.setUser(u);
		
		StockManager stockManager = new StockManagerImpl();
		stockManager.addStock(stock);
		
		
		response.sendRedirect(request.getContextPath() + "/servlet/stock/SearchStockServlet");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	
}
