package com.cy.rms.basedata.dao;

public class FactoryDaoFactory4MySql implements FactoryDaoFactory {

	@Override
	public FactoryDao createFactoryDao() {
		// TODO Auto-generated method stub
		return new FactoryDao4MySqlImpl();
	}

}
