package com.ebookstore.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ebookstore.dao.CategoryDao;
import com.ebookstore.domain.Category;
import com.ebookstore.utils.DBCPUtil;

public class CategoryDaoImpl implements CategoryDao {

	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public void save(Category category) {
		try {
			String sql = "insert into categories (id,name,description) values(?,?,?);";
			Object[] parameter = new Object[]{category.getId(),category.getName(),category.getDescription()};
			qr.update(sql,parameter);
		} catch (Exception e) {
			throw new RuntimeException("save category exception!", e);
		}
	}

	@Override
	public List<Category> findAll() {
		List<Category> result = null;
		try {
			String sql = "select * from categories";
			result = qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (Exception e) {
			throw new RuntimeException("find all category exception!", e);
		}
		return result;
	}

	@Override
	public Category findById(String categoryid) {
		Category result = null;
		try {
			String sql = "select * from categories where id=?;";
			result = qr.query(sql, new BeanHandler<Category>(Category.class),categoryid);
		} catch (Exception e) {
			throw new RuntimeException("find category of " + categoryid +" exception!", e);
		}
		return result;
	}

}
