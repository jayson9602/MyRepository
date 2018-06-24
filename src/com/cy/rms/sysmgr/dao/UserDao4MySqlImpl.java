package com.cy.rms.sysmgr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.util.ApplicationException;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;

public class UserDao4MySqlImpl implements UserDao {

	@Override
	public void addUser(Connection conn, User user) {
		String sql = "insert into user values (null , ? ,? ,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getUserType());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}

	}

	@Override
	public PageModel getUsers(Connection conn, int pageNo, int pageSize) {
		String sql = "select * from user where username != 1 limit ?,?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel pageModel = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List userList = new ArrayList();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setUserType(rs.getInt("userType"));
				
				userList.add(user);
			}
			pageModel = new PageModel();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(userList);
			// 根据条件取得记录数
			int totalRecords = getTotalRecords(conn);
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
	
	private int getTotalRecords(Connection conn) throws SQLException {
		String sql = "select count(*) from user where username != 1  ";
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
		String sql = "delete from user where id = ?";
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
	public void update(Connection conn, User user) {
		String sql = "update user set password = ?  where id = ?";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}

	}

	@Override
	public User login(Connection conn,String userName, String password) {
		User u = null;
		String sql = "select * from user where username = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				throw new ApplicationException("用户名错误");
			} else {
				if(!password.equals(rs.getString("password"))) {
					throw new ApplicationException("密码错误");
				}
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUserName(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setUserType(rs.getInt("userType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return u;
	}
	
	public boolean checkExist(Connection conn,String userName) {
		String sql = "select * from user where username = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);;
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isExist = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return isExist;
	}

}
