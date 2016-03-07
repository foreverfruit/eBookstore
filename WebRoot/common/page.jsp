<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
第${page.pageIndex }页/共${page.totalPageCount }页&nbsp;&nbsp;
    			<a href="${pageContext.request.contextPath}${page.url}&pageIndex=${page.prePageIndex}">上一页</a>
    			<a href="${pageContext.request.contextPath}${page.url}&pageIndex=${page.nextPageIndex}">下一页</a>
