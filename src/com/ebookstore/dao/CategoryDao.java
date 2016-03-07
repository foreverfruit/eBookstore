package com.ebookstore.dao;

import java.util.List;

import com.ebookstore.domain.Category;


public interface CategoryDao {

	void save(Category category);

	List<Category> findAll();

	Category findById(String categoryid);

}
