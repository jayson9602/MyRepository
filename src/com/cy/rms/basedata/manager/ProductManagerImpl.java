package com.cy.rms.basedata.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.cy.rms.basedata.dao.ProductDao;
import com.cy.rms.basedata.dao.ProductDaoFactory;
import com.cy.rms.basedata.domain.Product;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;
import com.cy.rms.util.XmlConfigReader;

public class ProductManagerImpl implements ProductManager {

	private ProductDao productDao = null;
	
	public ProductManagerImpl() {
		String className = XmlConfigReader.getInstance().getDaoFactory("product-dao-factory");
		//System.out.println(className);
		ProductDaoFactory factory = null;
		try {
			factory = (ProductDaoFactory) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		productDao = factory.createProductDao();
	}
	
	/**
	 * 添加产品
	 */
	public void addProduct(Product product) {

		Connection conn = DbUtil.getConn();
		productDao.addProduct(conn, product);
	}

	@Override
	public PageModel getProducts(int pageNo, int pageSize, String searchId,String searchName) {
		Connection conn = DbUtil.getConn();
		return productDao.getProducts(conn, pageNo, pageSize, searchId,searchName);
	}

	@Override
	public void delete(String id) {
		Connection conn = DbUtil.getConn();
		productDao.delete(conn, id);
	}

	@Override
	public void update(Product product) {
		Connection conn = DbUtil.getConn();
		productDao.update(conn, product);
	}

	@Override
	public Product findProductById(int id) {
		Connection conn = DbUtil.getConn();
		return productDao.findProductById(conn, id);
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList = null;
		Connection conn = DbUtil.getConn();
		productList = productDao.getAllProducts(conn);
		return productList;
	}



}
