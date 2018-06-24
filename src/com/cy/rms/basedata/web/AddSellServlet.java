package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cy.rms.basedata.domain.Product;
import com.cy.rms.basedata.domain.Sell;
import com.cy.rms.basedata.manager.ProductManager;
import com.cy.rms.basedata.manager.ProductManagerImpl;
import com.cy.rms.basedata.manager.SellManager;
import com.cy.rms.basedata.manager.SellManagerImpl;
import com.cy.rms.sysmgr.domain.User;

public class AddSellServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession(); 
		User u = (User)session.getAttribute("user");

		int amount = Integer.parseInt(request.getParameter("amount"));
		
		ProductManager productManager = new ProductManagerImpl();
		Product product = productManager.findProductById(Integer.parseInt(request.getParameter("productId")));
		if(amount > product.getStock()) {
			String strError = "库存量不足";
			request.setAttribute("strError", strError);
			request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
		} else {
			product.setSell(product.getSell() + amount);
			product.setStock(product.getStock() - amount);
			productManager.update(product);
			
			Sell sell = new Sell();
			sell.setRemark(request.getParameter("remark"));
			sell.setAmount(amount);
			sell.setProduct(product);
			sell.setUser(u);
			
			SellManager sellManager = new SellManagerImpl();
			sellManager.addSell(sell);
			
			
			response.sendRedirect(request.getContextPath() + "/servlet/sell/SearchSellServlet");
		}

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	
}
