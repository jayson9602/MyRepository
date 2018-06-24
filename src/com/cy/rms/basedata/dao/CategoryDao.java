package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.domain.Category;
import com.cy.rms.util.PageModel;

public interface CategoryDao {

	/**
	 * 添加物料类别
	 * @param conn
	 * @param category
	 */
	public void addCategory(Connection conn,Category category);
	
	/**
	 * 查询物料列表
	 * @param conn
	 * @param categories
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel getCategorys(Connection conn, int pageNo, int pageSize,String searchId,String searchName);

	/**
	 * 根据id删除物料类别
	 * @param conn
	 * @param id
	 * @return
	 */
	public void delete(Connection conn,String id);
	
	/**
	 * 物料类别更新
	 * @param conn
	 * @param category
	 */
	public void update(Connection conn,Category category);
	
	/**
	 * 获取所有的物料类别
	 * @return
	 */
	public List<Category> getAllCategories(Connection conn);
	
	/**
	 * 通过id查找类别
	 * @param conn
	 * @param id
	 * @return
	 */
	public Category findCategoryById(Connection conn,String id);
}
