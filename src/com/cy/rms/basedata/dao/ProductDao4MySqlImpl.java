package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cy.rms.basedata.domain.Category;
import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.basedata.domain.Product;
import com.cy.rms.util.ApplicationException;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;

public class ProductDao4MySqlImpl implements ProductDao {

	@Override
	public void addProduct(Connection conn, Product product) {
		String sql = "insert into product values (null , ? , ? , ? , 0, 0, ? , ?  )";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getName());
			pstmt.setFloat(2, product.getPriceIn());
			pstmt.setFloat(3, product.getPriceOut());
			pstmt.setInt(4, product.getCategory().getId());
			pstmt.setInt(5, product.getFactory().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

	@Override
	public void update(Connection conn, Product product) {
		String sql = "update product set name = ? ,priceIn = ? ,priceOut = ? ,category_id = ?,factory_id = ? ,stock = ? , sell = ? where id = ?";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getName());
			pstmt.setFloat(2, product.getPriceIn());
			pstmt.setFloat(3, product.getPriceOut());
			pstmt.setInt(4, product.getCategory().getId());
			pstmt.setInt(5, product.getFactory().getId());
			pstmt.setInt(6, product.getStock());
			pstmt.setInt(7, product.getSell());
			pstmt.setInt(8, product.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		
	}

	@Override
	public PageModel getProducts(Connection conn, int pageNo, int pageSize, String searchId,String searchName) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select p.id , p.name , p.priceIn ,p.priceOut , p.stock , p.sell ,")
				.append("c.categoryName as categoryName ,  f.factoryName as factoryName ")
				.append("from product p, category c, factory f ")
				.append("where p.category_id = c.id and p.factory_id = f.id  ");
		
		if (searchId != null && !"".equals(searchId)) {
			sbSql.append("and p.id = "+ searchId +" ");
		}
		if (searchName != null && !"".equals(searchName)) {
			sbSql.append("and p.name like '%" + searchName + "%'");
		}
		
		sbSql.append("limit ?,?");
		
		//System.out.println(sbSql.toString());

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel pageModel = null;
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, (pageNo-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List productList = new ArrayList();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPriceIn(rs.getFloat("priceIn"));
				product.setPriceOut(rs.getFloat("priceOut"));
				product.setSell(rs.getInt("sell"));
				product.setStock(rs.getInt("stock"));
				// 构造category并添加到product中
				Category category = new Category();
				category.setCategoryName(rs.getString("categoryName"));
				product.setCategory(category);
				// 构造factory并添加到factory中
				Factory factory = new Factory();
				factory.setFactoryName(rs.getString("factoryName"));
				product.setFactory(factory);
				// 将product添加到productList中
				productList.add(product);
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(productList);
			// 根据条件取得记录数
			int totalRecords = getTotalRecords(conn, searchId,searchName);
			pageModel.setTotalRecords(totalRecords);
		} catch (SQLException e) {
			e.printStackTrace();
			// 记录到日志文件 error
			throw new ApplicationException("分页查询失败");
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return pageModel;
	}

	private int getTotalRecords(Connection conn, String searchId,String searchName) throws SQLException {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select count(*) from product ");
		if (searchId != null && !"".equals(searchId)) {
			sbSql.append("and id = "+ searchId +" ");
		}
		if (searchName != null && !"".equals(searchName)) {
			sbSql.append("and name like '%" + searchName + "%'");
		}
		
		String sql = sbSql.toString().replaceFirst("and", "where");
		//System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int temp = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			temp = rs.getInt(1);
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
		}
		return temp;
	}

	@Override
	public void delete(Connection conn, String id) {
		String sql = "delete from product where id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

	@Override
	public Product findProductById(Connection conn, int id) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select p.id , p.name , p.priceIn ,p.priceOut , p.stock , p.sell ,p.category_id , p.factory_id ,")
				.append("c.categoryName as categoryName ,  f.factoryName as factoryName ")
				.append("from product p, category c, factory f ")
				.append("where p.category_id = c.id and p.factory_id = f.id  ")
				.append("and p.id = ? ");
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product product = null;
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPriceIn(rs.getFloat("priceIn"));
				product.setPriceOut(rs.getFloat("priceOut"));
				product.setSell(rs.getInt("sell"));
				product.setStock(rs.getInt("stock"));
				// 构造category并添加到product中
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("categoryName"));
				product.setCategory(category);
				// 构造factory并添加到factory中
				Factory factory = new Factory();
				factory.setId(rs.getInt("factory_id"));
				factory.setFactoryName(rs.getString("factoryName"));
				product.setFactory(factory);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts(Connection conn) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select p.id , p.name , p.priceIn ,p.priceOut , p.stock , p.sell ,p.category_id , p.factory_id , ")
				.append("c.categoryName as categoryName ,  f.factoryName as factoryName ")
				.append("from product p, category c, factory f ")
				.append("where p.category_id = c.id and p.factory_id = f.id  ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> productList = null;
		try {
			productList = new ArrayList<Product>();
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPriceIn(rs.getFloat("priceIn"));
				product.setPriceOut(rs.getFloat("priceOut"));
				product.setSell(rs.getInt("sell"));
				product.setStock(rs.getInt("stock"));
				// 构造category并添加到product中
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("categoryName"));
				product.setCategory(category);
				// 构造factory并添加到factory中
				Factory factory = new Factory();
				factory.setId(rs.getInt("factory_id"));
				factory.setFactoryName(rs.getString("factoryName"));
				product.setFactory(factory);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return productList;
	}

}
