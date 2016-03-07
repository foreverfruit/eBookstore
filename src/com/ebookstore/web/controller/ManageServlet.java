package com.ebookstore.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.ebookstore.common.Page;
import com.ebookstore.domain.Book;
import com.ebookstore.domain.Category;
import com.ebookstore.service.BusinessService;
import com.ebookstore.service.impl.BusinessServiceImpl;
import com.ebookstore.utils.BeanUtil;
import com.ebookstore.utils.IdGenertor;

@SuppressWarnings("serial")
public class ManageServlet extends HttpServlet {
	
	private BusinessService s = new BusinessServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("addCategory".equals(op)){
			addCategory(request,response);
		}else if("showAllCategory".equals(op)){
			showAllCategory(request,response);
		}else if("addBookUI".equals(op)){
			addBookUI(request,response);
		}else if("addBook".equals(op)){
			addBook(request,response);
		}else if("showPageBooks".equals(op)){
			showPageBooks(request,response);
		}
	}

	public void showPageBooks(HttpServletRequest request,
			HttpServletResponse response)   throws ServletException, IOException {
		String pageIndex = request.getParameter("pageIndex");//用户要看的页码
		Page page = s.findPageBooks(pageIndex);
		page.setUrl("/manage/ManageServlet?op=showPageBooks");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manage/listBooks.jsp").forward(request, response);
	
	}

	public void addBook(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException {
		//判断表单是不是multipart/form-data类型的
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException("The form is not multipart/form-data");
		}
		
		//解析请求内容
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		} catch (Exception e) {
			throw new RuntimeException("解析上传文件异常",e);
		}
		
		Book book = new Book();//空对象
		for(FileItem item:items){
			//普通字段：把数据封装到Book对象中
			if(item.isFormField()){
				processFormFiled(item,book);
			}else{
			//上传字段：上传
				processUploadFiled(item,book);
			}
		}
		// 保存数据
		s.addBook(book);
		// 反馈页面
		response.sendRedirect(request.getContextPath()+"/common/message.jsp");
	}

	private void processUploadFiled(FileItem item, Book book) {
		// 存放路径：不要放在WEB-INF中
		String storeDirectory = getServletContext().getRealPath("/image");
		
		// 搞文件名
		String filename = item.getName();//  a.jpg
		if(filename!=null){
			//保留后缀名，获得唯一文件名保存
			filename = IdGenertor.genGUID()+"."+ FilenameUtils.getExtension(filename);
			book.setFilename(filename);
		}
		
		// 计算子目录·按照日期
		String childPath = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		File f = new File(storeDirectory,childPath);
		if(!f.exists()){
			f.mkdirs();
		}
		book.setPath(childPath);
		
		//文件上传
		try {
			item.write(new File(storeDirectory+File.separator+childPath,filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processFormFiled(FileItem item, Book book) {
		try {
			String fieldName = item.getFieldName();//name
			String fieldValue = item.getString("UTF-8");//jpm
			BeanUtils.setProperty(book, fieldName, fieldValue);
			//单独处理书籍所属的分类
			if("categoryid".equals(fieldName)){
				Category c = s.findCategoryById(fieldValue);
				book.setCategory(c);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addBookUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 这个种类为了给添加书籍时显示种类用的
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/addBook.jsp").forward(request, response);
	}

	public void showAllCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/listCategory.jsp").forward(request, response);
	}

	public void addCategory(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// 获取请求表单数据·封装成javaBean
		Category c = BeanUtil.fillBean(request,Category.class);
		// 保存数据
		s.addCategory(c);
		// 页面反馈
		response.sendRedirect(request.getContextPath()+"/common/message.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
