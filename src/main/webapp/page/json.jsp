<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSON TEST</title>
</head>
<body>


<p>zhanglin!</p>
<p>zhanglin!</p>
<p>zhanglin!</p>
<p>zhanglin 测试</p>
<p>zhanglin 测试</p>
<p>zhanglin 测试</p>
<p>zhanglin 测试</p>
<input type="button" value="ddd" id="ddd">

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		var aa ="dd";
		alert("meimei!");
	});
	
	$("#ddd").bind('click',function(){
		
		var str = <%=(String)request.getAttribute("json")%>
		var jsObject = eval('('+str+')');  
		alert(jsObject);
	});
</script>

</html>