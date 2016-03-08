package com.ebookstore.web.beans;

import java.io.Serializable;

import com.ebookstore.domain.Book;

@SuppressWarnings("serial")
public class CartItem implements Serializable {

	private Book book;
	private int count;
	private float amount;
	
	public CartItem(Book book){
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getAmount() {
		return book.getPrice() * (float)this.count;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount="
				+ amount + "]";
	}
	
	
}
