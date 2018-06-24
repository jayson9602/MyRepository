package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.domain.Sell;
import com.cy.rms.util.PageModel;

public interface SellDao {

	/**
	 * 进货
	 * @param conn
	 * @param sell
	 */
	public void addSell(Connection conn,Sell sell);
	
	/**
	 * 获取进货单列表
	 * @param conn
	 * @param pageNo
	 * @param pageSize
	 * @param searchId
	 * @param searchName
	 * @return
	 */
	public PageModel getSells(Connection conn, int pageNo, int pageSize, String searchId,String searchName);
	
	/**
	 * 删除进货记录
	 * @param conn
	 * @param id
	 */
	public void delete(Connection conn,String id);
	
	public List<Sell> getAllSells(Connection conn);
}
