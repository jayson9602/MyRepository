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

public class AddProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单输入信息并声称product对象
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPriceIn(Integer.parseInt(request.getParameter("priceIn")));
		product.setPriceOut(Integer.parseInt(request.getParameter("priceOut")));
		//创建category对象并放入product中
		Category category = new Category();
		category.setId(Integer.parseInt(request.getParameter("categoryId")));
		product.setCategory(category);
		//创建Factory对象并放入product中
		Factory factory = new Factory();
		factory.setId(Integer.parseInt(request.getParameter("factoryId")));
		product.setFactory(factory);
		
		//调用product管理方法进行添加
		ProductManager productManager = new ProductManagerImpl();
		productManager.addProduct(product);
		
		//重定向到productList页面
		response.sendRedirect(request.getContextPath()+"/servlet/product/SearchProductServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
