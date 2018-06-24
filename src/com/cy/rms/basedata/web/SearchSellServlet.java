package com.cy.rms.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.manager.SellManager;
import com.cy.rms.basedata.manager.SellManagerImpl;
import com.cy.rms.util.PageModel;

public class SearchSellServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收并设置页码
		int pageNo = 1;
		String strPageNo = request.getParameter("pageNo");
		if (strPageNo != null && !strPageNo.trim().equals("")) {
			try {
				pageNo = Integer.parseInt(strPageNo);
			} catch (NumberFormatException e) {
				pageNo = 1;
			}
		}
		//从application范围内取得page-size,application指的是ServletContext对象
		int pageSize = Integer.parseInt(this.getServletContext().getInitParameter("page-size"));
		//接收查询条件（id，name）
		String searchId = "";
		String searchName = "";
		searchId = request.getParameter("searchId");
		searchName = request.getParameter("searchName");
		//构造sellManager进行查询
		SellManager sellManager = new SellManagerImpl();
		PageModel pageModel = sellManager.getSells(pageNo, pageSize, searchId, searchName);
		//将pageModel设置到Attribute中
		request.setAttribute("pageModel", pageModel);
		//转发到sellList.jsp
		request.getRequestDispatcher("/basedata/sellList.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
