package com.ebookstore.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ebookstore.dao.BookDao;
import com.ebookstore.domain.Book;
import com.ebookstore.domain.Category;
import com.ebookstore.utils.DBCPUtil;

public class BookDaoImpl implements BookDao {

	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public void save(Book book) {
		try {
			String sql = "insert into books "
					+ "(id,name,author,price,path,filename,description,categoryid) "
					+ "values(?,?,?,?,?,?,?,?);";
			Object[] parameter = new Object[]{
					book.getId(),
					book.getName(),
					book.getAuthor(),
					book.getPrice(),
					book.getPath(),
					book.getFilename(),
					book.getDescription(),
					book.getCategory().getId()
			};
			qr.update(sql,parameter);
		} catch (Exception e) {
			throw new RuntimeException("save book exception!", e);
		}
	}

	@Override
	public int getTotalRecordsCount() {
		Object result = null;
		try {
			String sql = "select count(*) from books;";
			// 该结果Object实际类型是Long类型数据
			result = qr.query(sql, new ScalarHandler(1));
			return ((Long)result).intValue();
		} catch (Exception e) {
			throw new RuntimeException("find books count exception!", e);
		}
	}

	@Override
	public List<Book> findPageRecords(int startRecordIndex, int pageSize) {
		try {
			String sql = "select * from books limit ?,?;";
			Object[] parameter = new Object[]{startRecordIndex,pageSize};
			List<Book> books  = qr.query(sql, new BeanListHandler<Book>(Book.class),parameter);
			// 将结果中的书根据其种类序号，找到其种类，得到种类对象，存入该书对象中：此处可以极大优化算法，不应该每个书都查，而是每个新读取到的种类id查一次
			if(books!=null && !books.isEmpty()){
				for(Book b:books){
					sql = "select * from categories where id=(select categoryid from books where id=?);";
					Category c = qr.query(sql,new BeanHandler<Category>(Category.class),b.getId());
					b.setCategory(c);
				}
			}
			return books;
		} catch (Exception e) {
			throw new RuntimeException("find page books exception!", e);
		}
	}

	@Override
	public List<Book> findPageRecords(int startRecordIndex, int pageSize,String categoryid) {
		try {
			String sql = "select * from books where categoryid=? limit ?,?;";
			Object[] parameter = new Object[]{categoryid,startRecordIndex,pageSize};
			List<Book> books  = qr.query(sql, new BeanListHandler<Book>(Book.class),parameter);
			// 将结果中的书根据其种类序号，找到其种类，得到种类对象，存入该书对象中：此处可以极大优化算法，不应该每个书都查，而是每个新读取到的种类id查一次
			if(books!=null && !books.isEmpty()){
				sql = "select * from categories where id=?;";
				Category c = qr.query(sql,new BeanHandler<Category>(Category.class),categoryid);
				for(Book b:books){
					b.setCategory(c);
				}
			}
			return books;
		} catch (Exception e) {
			throw new RuntimeException("find page books by category exception!", e);
		}
	}

	@Override
	public Book findBookById(String bookid) {
		Book result = null;
		try {
			String sql = "select * from books where id=?;";
			result = qr.query(sql, new BeanHandler<Book>(Book.class),bookid);
		} catch (Exception e) {
			throw new RuntimeException("find book exception!", e);
		}
		return result;
	}

	@Override
	public int getTotalRecordsCount(String categoryid) {
		Object result = null;
		try {
			String sql = "select count(*) from books where categoryid=?;";
			// 该结果Object实际类型是Long类型数据
			result = qr.query(sql, new ScalarHandler(1),categoryid);
			return ((Long)result).intValue();
		} catch (Exception e) {
			throw new RuntimeException("find books count exception!", e);
		}
	}

}
