<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	<c:if test="${!empty sessionScope.cart.items }">
		<table border="1" width="538" align="center">
			<tr>
				<th>书名</th>
				<th>数量</th>
				<th>单价</th>
				<th>小计</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
				<tr class="${vs.index%2==0?'odd':'even'}">
					<th>${me.value.book.name }</th>
					<th>
						<input type="text" name="count" value="${me.value.count }" size="2" onchange="changeCount(this,${me.value.count},'${me.key}')">
					</th>
					<th>${me.value.book.price }</th>
					<th>${me.value.amount }</th>
					<th><a href="javascript:delOneItem('${me.key }')">删除</a></th>
				</tr>	
			</c:forEach>
	    	<tr>
	    		<th align="right" colspan="5">
	    			<a href="javascript:delAll()">清空</a>&nbsp;&nbsp;
	    			总计：${sessionScope.cart.totalcount }件 &nbsp;&nbsp;总价：${sessionScope.cart.totalAmount }元
	    		</th>
	    	</tr>
	    </table>
	</c:if>
	<c:if test="${empty sessionScope.cart.items }" >
		<h3>当前购物车为空！</h3>
	</c:if>
	
	<script type="text/javascript">
		function delAll(){
			var bool = window.confirm("确定要删除吗");
			if(bool){
				window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=delAll";
			}
		}
		function delOneItem(bookid){
			var bool = window.confirm("确定要删除吗");
			if(bool){
				window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=delOne&bookid=" + bookid;
			}
		}
		function changeCount(inputObj,oldcount,bookid){
			var bool = window.confirm("确定要修改吗");
			if(bool){
				var newcount = inputObj.value;
				// 正则验证输入
				if(!/^[1-9][0-9]*$/.test(newcount)){
					alert("请输入正确的数值");
					inputObj.value=oldcount;
					return;
				}
				window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=changeCount&count="+newcount+"&bookid=" + bookid;
			}else{
				inputObj.value=oldcount;
			}
		}
	</script>
</body>
</html>