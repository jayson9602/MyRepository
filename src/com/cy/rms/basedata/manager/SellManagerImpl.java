package com.cy.rms.basedata.manager;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.dao.SellDao;
import com.cy.rms.basedata.dao.SellDaoFactory;
import com.cy.rms.basedata.domain.Sell;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;
import com.cy.rms.util.XmlConfigReader;

public class SellManagerImpl implements SellManager {

	private SellDao sellDao = null;
	
	public SellManagerImpl() {
		String className = XmlConfigReader.getInstance().getDaoFactory("sell-dao-factory");
		//System.out.println(className);
		SellDaoFactory factory = null;
		try {
			factory = (SellDaoFactory) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		sellDao = factory.createSellDao();
	}
	
	@Override
	public void addSell(Sell sell) {
		Connection conn = DbUtil.getConn();
		sellDao.addSell(conn, sell);
	}

	@Override
	public PageModel getSells(int pageNo, int pageSize, String searchId, String searchName) {
		Connection conn = DbUtil.getConn();
		return sellDao.getSells(conn, pageNo, pageSize, searchId, searchName);
	}

	@Override
	public void delete(String id) {
		Connection conn = DbUtil.getConn();
		sellDao.delete(conn, id);
	}

	@Override
	public List<Sell> getAllSells() {
		Connection conn = DbUtil.getConn();
		return sellDao.getAllSells(conn);
	}
}
