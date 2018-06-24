package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.util.ApplicationException;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;

public class FactoryDao4MySqlImpl implements FactoryDao {

	@Override
	public void addFactory(Connection conn, Factory factory) {
		String sql = "insert into factory values (null , ? , ? , ? , ?, ?  )";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, factory.getFactoryName());
			pstmt.setString(2, factory.getPlace());
			pstmt.setString(3, factory.getPhone());
			pstmt.setString(4, factory.getFax());
			pstmt.setString(5, factory.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}

	}

	@Override
	public List<Factory> getAllFactorys(Connection conn) {
		String sql = "select*from factory";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Factory> factoryList = null;
		try {
			factoryList = new ArrayList<Factory>();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Factory factory = new Factory();
				factory.setId(rs.getInt("id"));
				factory.setEmail(rs.getString("email"));
				factory.setFax(rs.getString("fax"));
				factory.setFactoryName(rs.getString("factoryName"));
				factory.setPhone(rs.getString("phone"));
				factory.setPlace(rs.getString("place"));
				factoryList.add(factory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return factoryList;
	}

	@Override
	public void update(Connection conn, Factory factory) {
		String sql = "update factory set factoryName = ? ,place = ? ,phone = ? ,fax = ? ,email = ? where id = ?";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, factory.getFactoryName());
			pstmt.setString(2, factory.getPlace());
			pstmt.setString(3, factory.getPhone());
			pstmt.setString(4, factory.getFax());
			pstmt.setString(5, factory.getEmail());
			pstmt.setInt(6, factory.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}

	}

	@Override
	public PageModel getFactorys(Connection conn, int pageNo, int pageSize, String searchId,String searchName) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select*from factory ");
		
		if (searchId != null && !"".equals(searchId)) {
			sbSql.append("and id = "+ searchId +" ");
		}
		if (searchName != null && !"".equals(searchName)) {
			sbSql.append("and factoryName like '%" + searchName + "%'");
		}
		
		sbSql.append(" limit ?,? ");
		
		String sql = sbSql.toString().replaceFirst("and","where");
		//System.out.println(sql);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel pageModel = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List factoryList = new ArrayList();
			while (rs.next()) {
				Factory factory = new Factory();
				factory.setId(rs.getInt("id"));
				factory.setFactoryName(rs.getString("factoryName"));
				factory.setPhone(rs.getString("phone"));
				factory.setFax(rs.getString("fax"));
				factory.setEmail(rs.getString("email"));
				factory.setPlace(rs.getString("place"));
				
				factoryList.add(factory);
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(factoryList);
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
		sbSql.append("select count(*) from factory ");
		
		if (searchId != null && !"".equals(searchId)) {
			sbSql.append("and id = "+ searchId +" ");
		}
		if (searchName != null && !"".equals(searchName)) {
			sbSql.append("and factoryName like '%" + searchName + "%'");
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
		String sql = "delete from factory where id = ?";
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
	public Factory findFactoryById(Connection conn, String id) {
		String sql = "select*from factory where id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Factory factory = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				factory = new Factory();
				factory.setId(rs.getInt("id"));
				factory.setFactoryName(rs.getString("factoryName"));
				factory.setEmail(rs.getString("email"));
				factory.setFax(rs.getString("fax"));
				factory.setPhone(rs.getString("phone"));
				factory.setPlace(rs.getString("place"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return factory;
	}

}
