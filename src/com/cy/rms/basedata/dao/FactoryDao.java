package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.util.PageModel;

public interface FactoryDao {

	/**
	 * 添加厂商信息
	 * @param conn
	 * @param factory
	 */
	public void addFactory(Connection conn,Factory factory);
	
	/**
	 * 获取厂商列表
	 * @param conn
	 * @param factorys
	 * @param pageNo
	 * @param pageSize
	 */
	public PageModel getFactorys(Connection conn, int pageNo, int pageSize,String searchId,String searchName);
	
	/**
	 * 获取所有厂商
	 * @param conn
	 * @return
	 */
	public List<Factory> getAllFactorys(Connection conn);
	
	/**
	 * 删除厂商信息
	 * @param conn
	 * @param id
	 * @return
	 */
	public void delete(Connection conn,String id);
	
	/**
	 * 更新厂商信息
	 * @param conn
	 * @param factory
	 */
	public void update(Connection conn,Factory factory);
	
	/**
	 * 通过id查找厂商
	 * @param conn
	 * @param id
	 * @return
	 */
	public Factory findFactoryById(Connection conn,String id);
}
