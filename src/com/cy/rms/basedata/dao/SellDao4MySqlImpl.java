package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cy.rms.basedata.domain.Product;
import com.cy.rms.basedata.domain.Sell;
import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.util.ApplicationException;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;

public class SellDao4MySqlImpl implements SellDao {

	@Override
	public void addSell(Connection conn, Sell sell) {
		String sql = "insert into sell values (null , ? , ? , now() , ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sell.getAmount());
			pstmt.setString(2, sell.getRemark());
			pstmt.setInt(3, sell.getProduct().getId());
			pstmt.setInt(4, sell.getUser().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}

	}

	@Override
	public PageModel getSells(Connection conn, int pageNo, int pageSize, String searchId, String searchName) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select s.id , s.amount , s.remark ,s.createdate ,")
				.append("p.name as productName ,  u.userName as userName ")
				.append("from sell s, product p, user u ")
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
			List sellList = new ArrayList();
			while (rs.next()) {
				Sell sell = new Sell();
				sell.setId(rs.getInt("id"));
				sell.setAmount(rs.getInt("amount"));
				sell.setCreateDate(rs.getDate("createDate"));
				sell.setRemark(rs.getString("remark"));
				
				Product product = new Product();
				product.setName(rs.getString("productName"));
				sell.setProduct(product);
				
				User user = new User();
				user.setUserName(rs.getString("userName"));
				sell.setUser(user);
				
				sellList.add(sell);
	
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(sellList);
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
		sbSql.append("select count(*) ")
			 .append(" from sell s, product p, user u ")
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
		String sql = "delete from sell where id = ?";
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

	public List<Sell> getAllSells(Connection conn) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select s.id , s.amount , s.remark ,s.createdate ,")
		.append("p.name as productName ,  u.userName as userName ")
		.append("from sell s, product p, user u ")
		.append("where s.product_id = p.id and s.user_id = u.id  ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Sell> sellList = null;
		try {
			sellList = new ArrayList<Sell>();
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Sell sell = new Sell();
				sell.setId(rs.getInt("id"));
				sell.setAmount(rs.getInt("amount"));
				sell.setCreateDate(rs.getDate("createDate"));
				sell.setRemark(rs.getString("remark"));
				
				Product product = new Product();
				product.setName(rs.getString("productName"));
				sell.setProduct(product);
				
				User user = new User();
				user.setUserName(rs.getString("userName"));
				sell.setUser(user);
				
				sellList.add(sell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return sellList;
	}
}
