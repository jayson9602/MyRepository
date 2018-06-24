package com.cy.rms.basedata.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cy.rms.basedata.domain.Stock;
import com.cy.rms.basedata.manager.StockManager;
import com.cy.rms.basedata.manager.StockManagerImpl;
import com.cy.rms.util.ExcelUtil;

public class StockExportServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StockManager stockManager = new StockManagerImpl();
		List<Stock> stockList = stockManager.getAllStocks();
		
		List<Object> bookList = new ArrayList<Object>();// 拼装生成文件用的信息
		List<Object> sheetList = new ArrayList<Object>();
		sheetList.add("进货信息"); // 添加工作表名称 - 位置0
		List<String> list = new ArrayList<String>();
		list.add("编号");
		list.add("商品");
		list.add("数量");
		list.add("操作人");
		list.add("日期");
		list.add("备注");
		sheetList.add(list); // 添加工作表表头(和导入模版一致) - 位置1
		for (Stock stock : stockList) {
			List<String> rowList = new ArrayList<String>();
			rowList.add(String.valueOf(stock.getId()));
			rowList.add(stock.getProduct() == null ? "" : stock.getProduct().getName());
			rowList.add(String.valueOf(stock.getAmount()));
			rowList.add(stock.getUser() == null ? "" : stock.getUser().getUserName());
			rowList.add(stock.getCreateDate().toString());
			rowList.add(stock.getRemark());
			sheetList.add(rowList);
		}
		bookList.add(sheetList);
		// 设置response属性, 向页面传送文件输出流
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment;filename=\"stock.xls\"");
		OutputStream os = response.getOutputStream();
		ExcelUtil.writeExcel(os, bookList);// 生成xls文件
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
