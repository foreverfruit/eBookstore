package com.ebookstore.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ebookstore.domain.Book;

@SuppressWarnings("serial")
public class Cart implements Serializable {
	
	private Map<String,CartItem> items = new HashMap<String, CartItem>();
	private int totalcount;
	private float totalAmount;
	
	public int getTotalcount() {
		totalcount=0;
		for(Map.Entry<String,CartItem> me: items.entrySet()){
			totalcount += me.getValue().getCount();
		}
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public float getTotalAmount() {
		totalAmount=0;
		for(Map.Entry<String,CartItem> me: items.entrySet()){
			totalAmount += me.getValue().getAmount();
		}
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Map<String, CartItem> getItems() {
		return items;
	}
	@Override
	public String toString() {
		return "Cart [totalcount=" + totalcount + ", totalAmount="
				+ totalAmount + "]";
	}
	
	public void addBook(Book  book){
		if(!items.containsKey(book.getId())){
			CartItem item = new CartItem(book);
			item.setCount(1);
			items.put(book.getId(), item);
		}else{
			CartItem item = items.get(book.getId());
			item.setCount(item.getCount()+1);
		}
	}
}
