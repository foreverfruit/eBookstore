package com.ebookstore.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebookstore.common.Page;
import com.ebookstore.domain.Book;
import com.ebookstore.domain.Category;
import com.ebookstore.service.BusinessService;
import com.ebookstore.service.impl.BusinessServiceImpl;
import com.ebookstore.web.beans.Cart;
import com.ebookstore.web.beans.CartItem;

@SuppressWarnings("serial")
public class ClientServlet extends HttpServlet {

	private BusinessService s = new BusinessServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("showAllBooks".equals(op)){
			showAllBooks(request,response);
		}else if("showBookDetail".equals(op)){
			showBookDetail(request,response);
		}else if("showCategoryBooks".equals(op)){
			showCategoryBooks(request,response);
		}else if("buy".equals(op)){
			buy(request,response);
		}else if("changeCount".equals(op)){
			changeCount(request,response);
		}else if("delOne".equals(op)){
			delOne(request,response);
		}else if("delAll".equals(op)){
			delAll(request,response);
		}
	}

	public void delAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.getSession().removeAttribute("cart");
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	public void delOne(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bookid");
		
		HttpSession hs = request.getSession();
		Cart cart = (Cart) hs.getAttribute("cart");
		cart.getItems().remove(bid);

		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	public void changeCount(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException   {
		String bid = request.getParameter("bookid");
		int count = Integer.parseInt(request.getParameter("count"));
		
		HttpSession hs = request.getSession();
		Cart cart = (Cart) hs.getAttribute("cart");
		CartItem cartItem = cart.getItems().get(bid);
		cartItem.setCount(count);

		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	public void buy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		String bid = request.getParameter("bookid");
		Book book = s.findBookById(bid);
		
		HttpSession hs = request.getSession();
		Cart cart = (Cart) hs.getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			hs.setAttribute("cart", cart);
		}
		
		cart.addBook(book);
		// 购物完成，返回主页
		response.getWriter().write("已加入购物车，正返回主页");
		response.setHeader("Refresh", "2;URL=" + request.getContextPath());
		
	}

	public void showCategoryBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		// 查询所有分类
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		
		String pageIndex = request.getParameter("pageIndex");
		String categoryid = request.getParameter("categoryid");
		Page page = s.findPageBooks(pageIndex,categoryid);
		page.setUrl("/client/ClientServlet?op=showCategoryBooks&categoryid=" + categoryid);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/showCategoryBooks.jsp").forward(request, response);
	}

	public void showBookDetail(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException {
		String bid = request.getParameter("bookid");
		Book book = s.findBookById(bid);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/bookdetail.jsp").forward(request, response);
	}

	// 分页显示所有书籍
	public void showAllBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 查询所有分类
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		
		String pageIndex = request.getParameter("pageIndex");
		Page page = s.findPageBooks(pageIndex);
		page.setUrl("/client/ClientServlet?op=showAllBooks");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/showAllBooks.jsp").forward(request, response);
	}

	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
