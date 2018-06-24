package com.cy.rms.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 采用单例模式解析sys-config.xml文件 
 * @author Administrator
 *
 */
public class XmlConfigReader {
	
//	//饿汉式(预先加载)
//	private static XmlConfigReader instance = new XmlConfigReader();
//	
//	private XmlConfigReader() {
//		
//	}
//	
//	public static XmlConfigReader getInstance() {
//		return instance;
//	}

	//懒汉式（延迟加载lazy）
	private static XmlConfigReader instance = null;
	
	//保存jdbc相关配置信息
	private JdbcConfig jdbcConfig = new JdbcConfig();
	
	//将读取的daoFactory信息以键值对的形式存储到Map中,key-标签名,value-类具体路径
	private Map<String,String> daoFactoryMap = new HashMap<>();
	
	private XmlConfigReader() {
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("sys-config.xml");
		try {
			Document doc = reader.read(in);
			
			//取得jdbc相关配置信息
			Element driverNameElt = (Element)doc.selectObject("/config/db-info/driver-name");
			Element urlElt = (Element)doc.selectObject("/config/db-info/url");
			Element userNameElt = (Element)doc.selectObject("/config/db-info/user-name");
			Element passwordElt = (Element)doc.selectObject("/config/db-info/password");
			
			//设置jdbc相关的配置
			jdbcConfig.setDriverName(driverNameElt.getStringValue());
			jdbcConfig.setUrl(urlElt.getStringValue());
			jdbcConfig.setUserName(userNameElt.getStringValue());
			jdbcConfig.setPassword(passwordElt.getStringValue());
			
			List daoFactoryList = doc.selectNodes("/config/dao-factory/*");
			for(int i=0;i<daoFactoryList.size();i++) {
				Element daoFactoryElt = (Element) daoFactoryList.get(i);
				String tagName = daoFactoryElt.getName();
				String tagText = daoFactoryElt.getText();
				//System.out.println("读取DaoFactory-->>" + tagText);
				//将读取的键值对放到Map中
				daoFactoryMap.put(tagName, tagText);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	public static synchronized XmlConfigReader getInstance() {
		if (instance == null) {
			instance = new XmlConfigReader();
		}
		return instance;
	}
	
	/**
	 * 返回jdbc相关配置
	 * @return
	 */
	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}
	
	/**
	 * 根据标签名获取daoFacotry的名字
	 * @param name
	 * @return daoFactory的完整路径
	 */
	public String getDaoFactory(String name) {
		return daoFactoryMap.get(name);
	}
	
	public static void main(String[] args) {
		JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
//		System.out.println(jdbcConfig.getDriverName());
//		System.out.println(jdbcConfig.getUrl());
//		System.out.println(jdbcConfig.getUserName());
		//System.out.println(jdbcConfig);
	}
}
