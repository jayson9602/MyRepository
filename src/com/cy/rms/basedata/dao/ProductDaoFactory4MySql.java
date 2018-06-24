package com.cy.rms.basedata.dao;

public class ProductDaoFactory4MySql implements ProductDaoFactory {

	@Override
	public ProductDao createProductDao() {
		return new ProductDao4MySqlImpl();
	}

}
