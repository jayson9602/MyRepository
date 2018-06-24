package com.cy.rms.basedata.manager;

import java.sql.Connection;
import java.util.List;

import com.cy.rms.basedata.dao.ProductDao;
import com.cy.rms.basedata.dao.ProductDaoFactory;
import com.cy.rms.basedata.dao.StockDao;
import com.cy.rms.basedata.dao.StockDaoFactory;
import com.cy.rms.basedata.domain.Stock;
import com.cy.rms.util.DbUtil;
import com.cy.rms.util.PageModel;
import com.cy.rms.util.XmlConfigReader;

public class StockManagerImpl implements StockManager {

	private StockDao stockDao = null;
	private ProductDao productDao = null;
	
	public StockManagerImpl() {
		String className = XmlConfigReader.getInstance().getDaoFactory("stock-dao-factory");
		String productClassName = XmlConfigReader.getInstance().getDaoFactory("product-dao-factory");
		//System.out.println(className);
		StockDaoFactory factory = null;
		ProductDaoFactory productDaoFactory = null;
		try {
			factory = (StockDaoFactory) Class.forName(className).newInstance();
			productDaoFactory = (ProductDaoFactory) Class.forName(productClassName).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		stockDao = factory.createStockDao();
		productDao = productDaoFactory.createProductDao();
	}
	
	@Override
	public void addStock(Stock stock) {
		Connection conn = DbUtil.getConn();
		stockDao.addStock(conn, stock);
	}

	@Override
	public PageModel getStocks(int pageNo, int pageSize, String searchId, String searchName) {
		Connection conn = DbUtil.getConn();
		return stockDao.getStocks(conn, pageNo, pageSize, searchId, searchName);
	}

	@Override
	public void delete(String id) {
		Connection conn = DbUtil.getConn();
		stockDao.delete(conn, id);
	}

	@Override
	public List<Stock> getAllStocks() {
		Connection conn = DbUtil.getConn();
		return stockDao.getAllStocks(conn);
	}
	

}
