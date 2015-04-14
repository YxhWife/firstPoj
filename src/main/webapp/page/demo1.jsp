<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发红包</title>
      <span >获取“分享到朋友圈”按钮点击状态及自定义分享内容接口</span>
      <span >获取“分享给朋友”按钮点击状态及自定义分享内容接口</span>
     <input type="button" value="解 绑" id="unbindId" />
     <input type="button" value="绑 定" id="bindId" />
</head>
<body>
<%-- <p>${user.password}</p>--%>
<p>${user}</p> 
 <p>Hello</p>
<script type="text/javascript" src="http://zl-test.coding.io/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
<script type="text/javascript">

function unbind() {
	$("#unbindId").css("background","#656d78")
	.css("border", "1px solid #656d78")
	.css("cursor", "wait")
	.attr("disabled", "disabled")
	.unbind("click");
}

function bind() {
	$("#unbindId").css("background","#fece00");
	$("#unbindId").removeAttr("disabled");
	$("#unbindId").on("click", unbind);
	
}
$(document).ready(function(){
	$("#unbindId").on("click",unbind);
	$("#bindId").on("click",bind);
});
</script>

</body>
</html>