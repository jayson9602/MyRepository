package com.cy.rms.basedata.manager;


import java.util.List;

import com.cy.rms.basedata.domain.Sell;
import com.cy.rms.util.PageModel;

public interface SellManager {

	public void addSell(Sell sell);
	
	public PageModel getSells(int pageNo, int pageSize, String searchId,String searchName);
	
	public void delete(String id);

	public List<Sell> getAllSells();
}
