package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.domain.Product;
import com.cy.rms.util.PageModel;

/**
 * 资源数据访问接口
 * @author Variable
 *
 */
public interface ProductDao {
	
	/**
	 * 添加资源
	 * @param conn
	 * @param product
	 */
	public void addProduct(Connection conn,Product product);

	/**
	 * 查询资源列表
	 * @param conn
	 * @param pageNo
	 * @param pageSize
	 * @param searchId
	 * @param searchName
	 * @return
	 */
	public PageModel getProducts(Connection conn, int pageNo, int pageSize,String searchId,String searchName);
	
	/**
	 * 根据id删除资源
	 * @param conn
	 * @param id
	 * @return
	 */
	public void delete(Connection conn,String id);
	
	/**
	 * 更新资源
	 * @param conn
	 * @param product
	 */
	public void update(Connection conn,Product product);
	
	/**
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public Product findProductById(Connection conn,int id);
	
	/**
	 * 获取所有的资源
	 * @param conn
	 * @return
	 */
	public List<Product> getAllProducts(Connection conn);
}
