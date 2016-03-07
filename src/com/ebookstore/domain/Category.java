package com.ebookstore.domain;

import java.io.Serializable;

public class Category implements Serializable {
	// GUID生成逻辑主键
	private String id;
	// NOT NULL UNIQUE
	private String name;
	// 描述
	private String description;
	
	public Category(){}
	
	public Category(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
	
}
