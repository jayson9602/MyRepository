package com.cy.rms.basedata.dao;

public class CategoryDaoFactory4MySql implements CategoryDaoFactory {

	@Override
	public CategoryDao createCategoryDao() {
		return new CategoryDao4MySqlImpl();
	}

}
