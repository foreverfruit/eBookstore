package com.ebookstore.dao;

import java.util.List;

import com.ebookstore.domain.Book;

public interface BookDao {

	void save(Book book);

	int getTotalRecordsCount();
	int getTotalRecordsCount(String categoryid);

	List<Book> findPageRecords(int startRecordIndex, int pageSize);

	List<Book> findPageRecords(int startRecordIndex, int pageSize,String categoryid);

	Book findBookById(String bookid);



}
