package com.ebookstore.service;

import java.util.List;

import com.ebookstore.common.Page;
import com.ebookstore.domain.Book;
import com.ebookstore.domain.Category;

public interface BusinessService {
	
	/**
	 * 添加种类
	 * @param category
	 */
	void addCategory(Category category);
	
	/**
	 * 查找所有种类
	 * @return
	 */
	List<Category> findAllCategories();
	
	/**
	 * 根据ID查找指定的种类
	 * @param categoryid
	 * @return
	 */
	Category findCategoryById(String categoryid);

	/**
	 * 添加书籍
	 * @param book
	 */
	void addBook(Book book);
	/**
	 * 根据当前页索引，分页查找所有书籍
	 * @param pageIndex
	 * @return
	 */
	Page findPageBooks(String pageIndex);
	/**
	 * 根据当前页索引，分页查找当前分类的书籍
	 * @param pageIndex
	 * @param categoryid
	 * @return
	 */
	Page findPageBooks(String pageIndex,String categoryid);
	/**
	 * 根据bookid查找书籍
	 * @param bookid
	 * @return
	 */
	Book findBookById(String bookid);
}
