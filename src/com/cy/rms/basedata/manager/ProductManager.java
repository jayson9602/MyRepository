package com.cy.rms.basedata.manager;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.domain.Product;
import com.cy.rms.util.PageModel;

public interface ProductManager {

	/**
	 * 添加物料
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * 获取物料页面
	 * @param pageNo
	 * @param pageSize
	 * @param searchId
	 * @param searchName
	 * @return
	 */
	public PageModel getProducts( int pageNo, int pageSize,String searchId,String searchName);
	
	/**
	 * 根据id删除物料
	 * @param id
	 * @return
	 */
	public void delete(String id);
	
	/**
	 * 更新物料
	 * @param product
	 */
	public void update(Product product);
	

	/**
	 * 通过id查询物料
	 * @param id
	 * @return
	 */
	public Product findProductById(int id);

	/**
	 * 获取所有物料
	 * @param conn
	 * @return
	 */
	public List<Product> getAllProducts();
}
