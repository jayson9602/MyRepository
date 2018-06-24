package com.cy.rms.sysmgr.manager;

import java.sql.Connection;

import com.cy.rms.sysmgr.dao.UserDao;
import com.cy.rms.sysmgr.dao.UserDaoFactory;
import com.cy.rms.sysmgr.domain.User;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;
import com.cy.rms.util.XmlConfigReader;

public class UserManagerImpl implements UserManager{
	
	private UserDao userDao = null;
	
	public UserManagerImpl() {
		String className = XmlConfigReader.getInstance().getDaoFactory("user-dao-factory");
		//System.out.println(className);
		UserDaoFactory factory = null;
		try {
			factory =  (UserDaoFactory) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		userDao = factory.createUserDao();
	}

	@Override
	public void addUser(User user) {
		Connection conn = DbUtil.getConn();
		userDao.addUser(conn, user);
	}

	@Override
	public PageModel getUsers(int pageNo, int pageSize) {
		Connection conn = DbUtil.getConn();
		return userDao.getUsers(conn, pageNo, pageSize);
	}

	@Override
	public void delete(String id) {
		Connection conn = DbUtil.getConn();
		userDao.delete(conn, id);
	}

	@Override
	public void update(User user) {
		Connection conn = DbUtil.getConn();
		userDao.update(conn, user);
	}

	@Override
	public User login(String userName, String password) {
		Connection conn = DbUtil.getConn();
		return userDao.login(conn, userName, password);
	}

	@Override
	public boolean checkExist(String userName) {
		Connection conn = DbUtil.getConn();
		return userDao.checkExist(conn, userName);
	}


	
	
}
