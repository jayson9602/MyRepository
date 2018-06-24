package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.domain.Category;
import com.cy.rms.basedata.manager.CategoryManager;
import com.cy.rms.basedata.manager.CategoryManagerImpl;

public class AddCategoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category category = new Category();
		category.setCategoryName(request.getParameter("categoryName"));
		category.setRemark(request.getParameter("remark"));
		
		
		CategoryManager categoryManager = new CategoryManagerImpl();
		categoryManager.addCategory(category);
		
		response.sendRedirect(request.getContextPath() + "/servlet/category/SearchCategoryServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
