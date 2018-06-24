package com.cy.rms.basedata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cy.rms.basedata.domain.Category;
import com.cy.rms.util.ApplicationException;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;

public class CategoryDao4MySqlImpl implements CategoryDao {

	@Override
	public void addCategory(Connection conn, Category category) {
		String sql = "insert into category values (null , ? ,? )";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category.getCategoryName());
			pstmt.setString(2, category.getRemark());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

	@Override
	public void update(Connection conn, Category category) {
		String sql = "update category set categoryName = ? ,remark = ?  where id = ?";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category.getCategoryName());
			pstmt.setString(2, category.getRemark());
			pstmt.setInt(3, category.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

	@Override
	public List<Category> getAllCategories(Connection conn) {
		String sql = "select*from category";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Category> categoryList = null;
		try {
			categoryList = new ArrayList<Category>();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setCategoryName(rs.getString("categoryName"));
				category.setRemark(rs.getString("remark"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return categoryList;
	}

	@Override
	public PageModel getCategorys(Connection conn, int pageNo, int pageSize, String searchId,String searchName) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select*from category ");
		
		if (searchId != null && !"".equals(searchId)) {
			sbSql.append("and id = "+ searchId +" ");
		}
		if (searchName != null && !"".equals(searchName)) {
			sbSql.append("and categoryName like '%" + searchName + "%'");
		}
		
		sbSql.append(" limit ?,? ");
		
		String sql = sbSql.toString().replaceFirst("and","where");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel pageModel = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List categoryList = new ArrayList();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setCategoryName(rs.getString("categoryName"));
				category.setRemark(rs.getString("remark"));
				
				categoryList.add(category);
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(categoryList);
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
		sbSql.append("select count(*) from category ");
		
		if (searchId != null && !"".equals(searchId)) {
			sbSql.append("and id = "+ searchId +" ");
		}
		if (searchName != null && !"".equals(searchName)) {
			sbSql.append("and categoryName like '%" + searchName + "%'");
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
		String sql = "delete from category where id = ?";
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
	public Category findCategoryById(Connection conn, String id) {
		String sql = "select*from category where id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Category category = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				category = new Category();
				category.setId(rs.getInt("id"));
				category.setCategoryName(rs.getString("categoryName"));
				category.setRemark(rs.getString("remark"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return category;
	}

}
