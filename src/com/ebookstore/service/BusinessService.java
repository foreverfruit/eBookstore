package com.ebookstore.service;

import java.util.List;

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

}
