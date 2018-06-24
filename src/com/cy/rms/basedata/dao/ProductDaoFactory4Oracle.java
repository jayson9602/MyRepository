package com.cy.rms.basedata.dao;

public class ProductDaoFactory4Oracle implements ProductDaoFactory {

	@Override
	public ProductDao createProductDao() {
		return new ProductDao4OracleImpl();
	}

}
