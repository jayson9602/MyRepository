package com.cy.rms.basedata.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadProductServlet extends HttpServlet {

	// 用于存放上传文件的目录
	private File uploadPath;
	// 用于存放临时文件的目录
	private File tempPath;

	public void init() throws ServletException {
		//获取上传文件的保存目录
		uploadPath = new File(getServletContext().getRealPath("upload"));
		// 如果目录不存在
		if (!uploadPath.exists()) {
			// 创建目录
			uploadPath.mkdir();
		}

		// 临时目录
		tempPath = new File(getServletContext().getRealPath("temp"));
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//使用Apache文件上传组件处理文件上传步骤：
        //1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置内存缓冲区的大小为4M。当上传文件大于4M时，fileupload组件将使用临时文件缓存上传文件。
		factory.setSizeThreshold(4096);
		//指定临时文件目录为tempPath
		factory.setRepository(tempPath);
		//2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置文件上传最大值为20M
		upload.setSizeMax(1000000 * 20);
		try {
			//解析request对象，并把表单中的每一个输入项包装成一个fileItem对象，并返回一个保存了所有FileItem的list集合
			List fileItems = upload.parseRequest(req);
			String productId = "";
			for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
				FileItem item = (FileItem) iter.next();

				//判断该item是否为普通表单类型
				if (item.isFormField()) {
					if ("productId".equals(item.getFieldName())) {
						productId = item.getString();
					}
				}else {
					//上传的是文件
					String fileName = item.getName();
					long size = item.getSize();
					if ((fileName == null || fileName.equals("")) && size == 0) {
						continue;
					}
					// 截取字符串 
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
					//写入文件到上传目录
					item.write(new File(uploadPath, productId + ".gif"));
				}
			}
			res.sendRedirect(req.getContextPath() + "/servlet/product/ShowUploadProductServlet?id=" + productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
