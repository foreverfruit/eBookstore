<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manage/header.jsp"%>
	<table border="1" width="438" align="center">
    	<tr>
    		<th>序号</th>
    		<th>分类名称</th>
    		<th>分类描述</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach items="${cs}" var="c" varStatus="vs">
    		<tr class="${vs.index%2==0?'odd':'even'}">
	    		<td>${vs.count}</td>
	    		<td>${c.name}</td>
	    		<td>${c.description}</td>
	    		<td>
	    			<a href="#">修改</a>
	    			<a href="#">删除</a>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
</body>
</html>