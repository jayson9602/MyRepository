package com.cy.rms.basedata.dao;

public class StockDaoFactory4MySql implements StockDaoFactory {

	@Override
	public StockDao createStockDao() {
		return new StockDao4MySqlImpl();
	}

}
