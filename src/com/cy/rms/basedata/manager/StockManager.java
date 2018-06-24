package com.cy.rms.basedata.manager;


import java.util.List;

import com.cy.rms.basedata.domain.Stock;
import com.cy.rms.util.PageModel;

public interface StockManager {

	public void addStock(Stock stock);
	
	public PageModel getStocks(int pageNo, int pageSize, String searchId,String searchName);
	
	public void delete(String id);
	
	public List<Stock> getAllStocks();
}
