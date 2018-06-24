package com.cy.rms.sysmgr.dao;

import java.sql.Connection;

import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.util.PageModel;

public interface UserDao {

	public void addUser(Connection conn,User user);
	
	public PageModel getUsers(Connection conn, int pageNo, int pageSize);
	
	public void delete(Connection conn,String id);
	
	public void update(Connection conn,User user);
	
	public boolean checkExist(Connection conn,String userName);
	
	public User login(Connection conn, String userName, String password);
}
