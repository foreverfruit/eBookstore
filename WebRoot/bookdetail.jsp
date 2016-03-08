<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	
	<table border="0" width="438" align="center">
    	<tr>
    		<th>
    			<img src="${pageContext.request.contextPath}/image/${book.path}/${book.filename}" 
    					alt="${book.filename}" width="45"/><br/>
	    		${book.name }<br/>
	    		${book.author }<br/>
	    		${book.price }<br/>
	    		<a href="${pageContext.request.contextPath}/client/ClientServlet?op=buy&bookid=${book.id}">加入购物车</a> 
	    		<a href="javascript:window.history.back();">返回</a> 
	    	</th>
    	</tr>
    </table>
</body>
</html>