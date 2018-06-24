package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.cy.rms.basedata.domain.Product;
import com.cy.rms.util.PageModel;

public class ProductDao4OracleImpl implements ProductDao {

	@Override
	public void addProduct(Connection conn, Product product) {
		System.out.println("ProductDao4OracleImp.addProduct(Connection conn, Product product)");
	}

	@Override
	public void update(Connection conn, Product product) {
		// TODO Auto-generated method stub

	}



	@Override
	public void delete(Connection conn, String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Product findProductById(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel getProducts(Connection conn, int pageNo, int pageSize, String searchId, String searchName) {
		// TODO Auto-generated method stub
		return null;
	}

}
