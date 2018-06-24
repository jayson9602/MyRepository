package com.cy.rms.sysmgr.manager;

import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.util.PageModel;

public interface UserManager {

	public void addUser(User user);
	
	public PageModel getUsers( int pageNo, int pageSize);
	
	public void delete(String id);
	
	public void update(User user);
	
	public User login(String userName, String password);
	
	public boolean checkExist(String userName);
}
