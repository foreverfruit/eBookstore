// 返回ajax需要的xhr对象
function getXmlHttpRequest() {
	var xmlHttp;

	try {
		// Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest();
	} catch (e) {

		// Internet Explorer
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {

			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("您的浏览器不支持AJAX！");
				return false;
			}
		}
	}
	return xmlHttp;
}
// 通过原型为string类增加trim方法
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
