package com.cy.rms.basedata.manager;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.dao.CategoryDao;
import com.cy.rms.basedata.dao.CategoryDaoFactory;
import com.cy.rms.basedata.domain.Category;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;
import com.cy.rms.util.XmlConfigReader;

public class CategoryManagerImpl implements CategoryManager {
	
	private CategoryDao categoryDao = null;
	
	public CategoryManagerImpl() {
		String className = XmlConfigReader.getInstance().getDaoFactory("category-dao-factory");
		//System.out.println(className);
		CategoryDaoFactory factory = null;
		try {
			factory = (CategoryDaoFactory) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		categoryDao = factory.createCategoryDao();
	}

	@Override
	public void addCategory(Category category) {
		Connection conn = DbUtil.getConn();
		categoryDao.addCategory(conn, category);
	}

	@Override
	public PageModel getCategorys(int pageNo, int pageSize, String searchId,String searchName) {
		Connection conn = DbUtil.getConn();
		return categoryDao.getCategorys(conn, pageNo, pageSize, searchId, searchName);
	}

	@Override
	public void delete(String id) {
		Connection conn = DbUtil.getConn();
		categoryDao.delete(conn, id);
	}

	@Override
	public void update(Category category) {
		Connection conn = DbUtil.getConn();
		categoryDao.update(conn, category);
	}

	/**
	 * 获取所有的物料类别
	 */
	public List<Category> getAllCategories() {
		Connection conn = DbUtil.getConn();
		return categoryDao.getAllCategories(conn);
	}

	@Override
	public Category findCategoryById(String id) {
		Connection conn = DbUtil.getConn();
		return categoryDao.findCategoryById(conn, id);
	}
	

	


	
}
