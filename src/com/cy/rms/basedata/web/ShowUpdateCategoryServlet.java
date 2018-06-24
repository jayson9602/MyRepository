package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.domain.Category;
import com.cy.rms.basedata.manager.CategoryManager;
import com.cy.rms.basedata.manager.CategoryManagerImpl;

public class ShowUpdateCategoryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		CategoryManager categoryManager = new CategoryManagerImpl();
		Category category = categoryManager.findCategoryById(id);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/basedata/categoryUpdate.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
