package com.cy.rms.basedata.manager;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.util.PageModel;

public interface FactoryManager {

	/**
	 * 添加厂商信息
	 * @param factory
	 */
	public void addFactory(Factory factory);
	
	/**
	 * 获取厂商列表
	 * @param factorys
	 * @param pageNo
	 * @param pageSize
	 */
	public PageModel getFactorys( int pageNo, int pageSize,String searchId,String searchName);
	
	/**
	 * 获取所有厂商
	 * @return
	 */
	public List<Factory> getAllFactorys();
	
	/**
	 * 删除厂商信息
	 * @param id
	 * @return
	 */
	public void delete(String id);
	
	/**
	 * 更新厂商信息
	 * @param factory
	 */
	public void update(Factory factory);
	
	/**
	 * 通过id查找厂商
	 * @param id
	 * @return
	 */
	public Factory findFactoryById(String id);
}
