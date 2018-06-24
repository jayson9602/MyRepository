package com.cy.rms.sysmgr.dao;

public class UserDaoFactory4MySql implements UserDaoFactory{

	@Override
	public UserDao createUserDao() {
		return new UserDao4MySqlImpl();
	}

}
