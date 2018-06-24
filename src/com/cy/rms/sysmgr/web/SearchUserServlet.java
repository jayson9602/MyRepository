package com.cy.rms.sysmgr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.sysmgr.manager.UserManager;
import com.cy.rms.sysmgr.manager.UserManagerImpl;
import com.cy.rms.util.PageModel;

public class SearchUserServlet extends HttpServlet {

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
		//构造UserManager进行查询
		UserManager userManager = new UserManagerImpl();
		PageModel pageModel = userManager.getUsers(pageNo, pageSize);
		//将pageModel设置到Attribute中
		request.setAttribute("pageModel", pageModel);
		//转发到userList.jsp
		request.getRequestDispatcher("/sysmgr/userList.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
