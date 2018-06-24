package com.cy.rms.basedata.dao;

public class SellDaoFactory4MySql implements SellDaoFactory {

	@Override
	public SellDao createSellDao() {
		return new SellDao4MySqlImpl();
	}

}
