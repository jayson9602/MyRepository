package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cy.rms.basedata.domain.Product;
import com.cy.rms.basedata.domain.Stock;
import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.util.ApplicationException;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;

public class StockDao4MySqlImpl implements StockDao {

	@Override
	public void addStock(Connection conn, Stock stock) {
		String sql = "insert into stock values (null , ? , ? , now() , ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stock.getAmount());
			pstmt.setString(2, stock.getRemark());
			pstmt.setInt(3, stock.getProduct().getId());
			pstmt.setInt(4, stock.getUser().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}

	}

	@Override
	public PageModel getStocks(Connection conn, int pageNo, int pageSize, String searchId, String searchName) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select s.id , s.amount , s.remark ,s.createdate ,")
				.append("p.name as productName ,  u.userName as userName ")
				.append("from stock s, product p, user u ")
				.append("where s.product_id = p.id and s.user_id = u.id  ");
		
		if (searchId != null && !"".equals(searchId)) {
			sbSql.append("and s.id = "+ searchId +" ");
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
			List stockList = new ArrayList();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setAmount(rs.getInt("amount"));
				stock.setCreateDate(rs.getDate("createDate"));
				stock.setRemark(rs.getString("remark"));
				
				Product product = new Product();
				product.setName(rs.getString("productName"));
				stock.setProduct(product);
				
				User user = new User();
				user.setUserName(rs.getString("userName"));
				stock.setUser(user);
				
				stockList.add(stock);
	
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(stockList);
			// ��������ȡ�ü�¼��
			int totalRecords = getTotalRecords(conn, searchId,searchName);
			pageModel.setTotalRecords(totalRecords);
		} catch (SQLException e) {
			e.printStackTrace();
			// ��¼����־�ļ� error
			throw new ApplicationException("��ҳ��ѯʧ��");
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return pageModel;
	}
	
	private int getTotalRecords(Connection conn, String searchId,String searchName) throws SQLException {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select count(*) ")
			 .append(" from stock s, product p, user u ")
			 .append("where s.product_id = p.id and s.user_id = u.id ");
		if (searchId != null && !"".equals(searchId)) {
			sbSql.append("and s.id = "+ searchId +" ");
		}
		if (searchName != null && !"".equals(searchName)) {
			sbSql.append("and p.name like '%" + searchName + "%'");
		}
		
		//System.out.println(sbSql.toString());
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int temp = 0;
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
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
		String sql = "delete from stock where id = ?";
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
	
	public List<Stock> getAllStocks(Connection conn) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select s.id , s.amount , s.remark ,s.createdate ,")
		.append("p.name as productName ,  u.userName as userName ")
		.append("from stock s, product p, user u ")
		.append("where s.product_id = p.id and s.user_id = u.id  ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Stock> stockList = null;
		try {
			stockList = new ArrayList<Stock>();
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setAmount(rs.getInt("amount"));
				stock.setCreateDate(rs.getDate("createDate"));
				stock.setRemark(rs.getString("remark"));
				
				Product product = new Product();
				product.setName(rs.getString("productName"));
				stock.setProduct(product);
				
				User user = new User();
				user.setUserName(rs.getString("userName"));
				stock.setUser(user);
				
				stockList.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return stockList;
	}

}
