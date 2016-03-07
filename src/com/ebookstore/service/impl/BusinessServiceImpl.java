package com.ebookstore.service.impl;

import java.util.List;

import com.ebookstore.common.Page;
import com.ebookstore.dao.BookDao;
import com.ebookstore.dao.CategoryDao;
import com.ebookstore.dao.impl.BookDaoImpl;
import com.ebookstore.dao.impl.CategoryDaoImpl;
import com.ebookstore.domain.Book;
import com.ebookstore.domain.Category;
import com.ebookstore.service.BusinessService;
import com.ebookstore.utils.IdGenertor;

public class BusinessServiceImpl implements BusinessService {

	private CategoryDao cDao = new CategoryDaoImpl();
	private BookDao bDao = new BookDaoImpl();
	
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

	@Override
	public void addBook(Book book) {
		if(book==null){
			throw new IllegalArgumentException("The book can not be null");
		}
		if(book.getCategory()==null){
			throw new IllegalArgumentException("The book's category can not be null");
		}
		book.setId(IdGenertor.genGUID());
		bDao.save(book);
	}

	@Override
	public Page findPageBooks(String pageNum) {
		int pageIndex = 1;
		if(pageNum!=null&&!pageNum.equals("")){
			pageIndex = Integer.parseInt(pageNum);
		}
		int totalRecordsCount = bDao.getTotalRecordsCount();
		Page page = new Page(pageIndex, totalRecordsCount);
		List<Book> records = bDao.findPageRecords(page.getStartRecordIndex(),page.getPageSize());
		page.setRecords(records);
		return page;
	}

	@Override
	public Page findPageBooks(String pageNum, String categoryid) {
		int pageIndex = 1;
		if(pageNum!=null&&!pageNum.equals("")){
			pageIndex = Integer.parseInt(pageNum);
		}
		int totalRecordsCount = bDao.getTotalRecordsCount();
		Page page = new Page(pageIndex, totalRecordsCount);
		List<Book> records = bDao.findPageRecords(page.getStartRecordIndex(),page.getPageSize(),categoryid);
		page.setRecords(records);
		return page;
	}

	@Override
	public Book findBookById(String bookid) {
		return bDao.findBookById(bookid);
	}

}
