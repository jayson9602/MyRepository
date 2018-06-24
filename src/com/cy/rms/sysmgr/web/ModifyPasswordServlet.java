package com.cy.rms.sysmgr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.sysmgr.manager.UserManager;
import com.cy.rms.sysmgr.manager.UserManagerImpl;

public class ModifyPasswordServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strError = "";
		String oldPassword = request.getParameter("oldPassword");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(!user.getPassword().equals(oldPassword)){
			strError = "原密码错误";
			request.setAttribute("strError", strError);
			request.getRequestDispatcher("/sysmgr/passwordModify.jsp").forward(request, response);
		} else if(user.getPassword().equals(oldPassword)){
			user.setPassword(request.getParameter("newPassword1"));
			UserManager userManager = new UserManagerImpl();
			userManager.update(user);
			session.setAttribute("user", user);
			strError = "恭喜您,修改成功";
			request.setAttribute("strError", strError);
			request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
