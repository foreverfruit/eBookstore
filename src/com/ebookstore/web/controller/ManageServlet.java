package com.ebookstore.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebookstore.domain.Category;
import com.ebookstore.service.BusinessService;
import com.ebookstore.service.impl.BusinessServiceImpl;
import com.ebookstore.utils.BeanUtil;

public class ManageServlet extends HttpServlet {
	
	private BusinessService s = new BusinessServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("addCategory".equals(op)){
			addCategory(request,response);
		}
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
