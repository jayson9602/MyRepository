package com.cy.rms.basedata.dao;

/**
 * Productdao工厂接口
 * @author Variable
 *
 */
public interface ProductDaoFactory {

	/**
	 * 创建ProductDao
	 * @return
	 */
	public ProductDao createProductDao();
}
