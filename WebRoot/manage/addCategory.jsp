<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manage/header.jsp"%>

<form
	action="${pageContext.request.contextPath }/manage/ManageServlet?op=addCategory"
	method="post">
	<table border="1" width="438" align="center">

		<tr>
			<th>*分类名称：</th>
			<td><input type="text" name="name" id="name" /></td>
		</tr>

		<tr>
			<th>描述:</th>
			<td><textarea rows="3" cols="38" name="description"></textarea>
			</td>
		</tr>

		<tr>
			<th colspan="2"><input type="button" value="保存"
				onclick="toSubmit()" /></th>
		</tr>
	</table>
</form>
<script type="text/javascript">
	// 提交前的表单验证，采用js验证，此处只对空字符验证
	function toSubmit() {
		var nameValue = document.getElementById("name").value;
		if (nameValue.trim() == "") {
			alert("请输入分类名称");
			return;
		}
		document.forms[0].submit();
	}
</script>
</body>
</html>