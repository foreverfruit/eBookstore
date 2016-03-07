<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manage/header.jsp"%>

<form enctype="multipart/form-data"
	action="${pageContext.request.contextPath}/manage/ManageServlet?op=addBook"
	method="post">
	<table border="1" width="438" align="center">
		<tr>
			<th>*书名：</th>
			<td><input type="text" name="name" id="name" /></td>
		</tr>
		<tr>
			<th>作者:</th>
			<td><input type="text" name="author" /></td>
		</tr>
		<tr>
			<th>价格:</th>
			<td><input type="text" name="price" /></td>
		</tr>
		<tr>
			<th>图片:</th>
			<td><input type="file" name="file" /></td>
		</tr>
		<tr>
			<th>描述：</th>
			<td><textarea rows="3" cols="38" name="description"></textarea>
			</td>
		</tr>
		<tr>
			<th>所属分类：</th>
			<td><select name="categoryid">
					<c:forEach items="${cs}" var="c">
						<option value="${c.id}">${c.name}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="保存" /></td>
		</tr>
	</table>
</form>
</body>
</html>