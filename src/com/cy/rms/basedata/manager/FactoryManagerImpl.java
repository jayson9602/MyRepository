package com.cy.rms.basedata.manager;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.dao.FactoryDao;
import com.cy.rms.basedata.dao.FactoryDaoFactory;
import com.cy.rms.basedata.domain.Factory;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;
import com.cy.rms.util.XmlConfigReader;

public class FactoryManagerImpl implements FactoryManager {

	private FactoryDao factoryDao = null;

	public FactoryManagerImpl() {
		String className = XmlConfigReader.getInstance().getDaoFactory("factory-dao-factory");
		//System.out.println(className);
		FactoryDaoFactory factory = null;
		
		try {
			factory = (FactoryDaoFactory) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		factoryDao = factory.createFactoryDao();
	}
	
	
	@Override
	public void addFactory(Factory factory) {
		Connection conn = DbUtil.getConn();
		factoryDao.addFactory(conn, factory);
	}

	@Override
	public PageModel getFactorys(int pageNo, int pageSize, String searchId, String searchName) {
		Connection conn = DbUtil.getConn();
		return factoryDao.getFactorys(conn, pageNo, pageSize, searchId, searchName);
	}

	/**
	 * 获取所有的厂商信息
	 */
	public List<Factory> getAllFactorys() {
		List<Factory> factoryList = null;
		Connection conn = DbUtil.getConn();
		factoryList = factoryDao.getAllFactorys(conn);
		return factoryList;
	}

	@Override
	public void delete(String id) {
		Connection conn = DbUtil.getConn();
		factoryDao.delete(conn, id);
	}

	@Override
	public void update(Factory factory) {
		Connection conn = DbUtil.getConn();
		factoryDao.update(conn, factory);
	}

	@Override
	public Factory findFactoryById(String id) {
		Connection conn = DbUtil.getConn();
		return factoryDao.findFactoryById(conn, id);
	}
	



}
