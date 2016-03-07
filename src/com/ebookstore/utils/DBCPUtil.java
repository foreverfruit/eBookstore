package com.ebookstore.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil {

	private static DataSource ds;
	
	static {
		try{
			InputStream in = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties p = new Properties();
			p.load(in);
			ds = BasicDataSourceFactory.createDataSource(p);
		}catch(Exception e){
			throw new ExceptionInInitializerError("初始化DBCPUtil失败");
		}
	}
	
	public static DataSource getDataSource(){
		return ds;
	}

	public static Connection getConnection(){
		try{
			return ds.getConnection();
		}catch(Exception e){
			throw new RuntimeException("获取connection对象失败");
		}
	}
	
}
