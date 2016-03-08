<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	
	<a href="${pageContext.request.contextPath}">所有分类：</a>
	<c:forEach items="${cs }" var="c">
		<a href="${pageContext.request.contextPath}/client/ClientServlet?op=showCategoryBooks&categoryid=${c.id}">${c.name}</a>
	</c:forEach>
	<br/>
	
	<table border="0" width="438" align="center">
    	<tr>
	    	<c:forEach items="${page.records}" var="b" varStatus="vs">
	    		<td>
	    			<img src="${pageContext.request.contextPath}/image/${b.path}/${b.filename}" 
	    					alt="${b.filename}" width="45"/><br/>
		    		${b.name }<br/>
		    		${b.author }<br/>
		    		${b.price }<br/>
		    		<a href="${pageContext.request.contextPath}/client/ClientServlet?op=showBookDetail&bookid=${b.id}">去看看</a><br/>
		    	</td>
	    	</c:forEach>
    	</tr>
    	<tr>
    		<td align="center" colspan="3">
    			<%@include file="/common/page.jsp"%>
    		</td>
    	</tr>
    </table>
</body>
</html>