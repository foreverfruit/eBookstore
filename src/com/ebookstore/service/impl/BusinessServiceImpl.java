package com.ebookstore.service.impl;

import java.util.List;

import com.ebookstore.dao.CategoryDao;
import com.ebookstore.dao.impl.CategoryDaoImpl;
import com.ebookstore.domain.Category;
import com.ebookstore.service.BusinessService;
import com.ebookstore.utils.IdGenertor;

public class BusinessServiceImpl implements BusinessService {

	private CategoryDao cDao = new CategoryDaoImpl();
	
	@Override
	public void addCategory(Category category) {
		// 为category添加id
		category.setId(IdGenertor.genGUID());
		cDao.save(category);
	}

	@Override
	public List<Category> findAllCategories() {
		return cDao.findAll();
	}

	@Override
	public Category findCategoryById(String categoryid) {
		return cDao.findById(categoryid);
	}

}
