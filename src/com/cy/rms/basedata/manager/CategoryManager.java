package com.cy.rms.basedata.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.cy.rms.basedata.domain.Category;
import com.cy.rms.util.PageModel;

public interface CategoryManager {

	/**
	 * 添加物料类别
	 * @param category
	 */
	public void addCategory(Category category);
	
	/**
	 * 查询物料列表
	 * @param categories
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel getCategorys(int pageNo, int pageSize,String searchId,String searchName);

	/**
	 * 根据id删除物料类别
	 * @param id
	 * @return
	 */
	public void delete(String id);
	
	/**
	 * 物料类别更新
	 * @param category
	 */
	public void update(Category category);
	
	/**
	 * 获取所有的物料类别
	 * @return
	 */
	public List<Category> getAllCategories();
	
	/**
	 * 通过id查找类别
	 * @param id
	 * @return
	 */
	public Category findCategoryById(String id);
}
