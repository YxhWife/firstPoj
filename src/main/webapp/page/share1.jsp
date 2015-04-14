<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>比特币钱包服务 - 最安全的比特币在线钱包和支付平台 - OKLink.com</title>
<link href="${pageContext.request.contextPath}/css/active/weixin.css" rel="stylesheet" type="text/css" media="screen, projection"/>
</head>

<body>
 <div id="newHb" >
	<p>恭喜您获得一个红包</p>
	<p>您获得了已个面值为<strong>0.1 BTC</strong>的红包！</p>
	
	<div id="mcover" onclick="weChat()" style="display:none;">
          <img src="${pageContext.request.contextPath}/image/money.png" />
 	</div>
 	
	<div class="text" id="content">
	   <div id="mess_share">
	      <div id="share_1">
	         <button id="cashBtc" class="button2">
	                                 兑换BTC
	         </button>
	      </div>
	      <div id="share_2">
	         <button id="shareFriends" class="button2">
	            <img src="${pageContext.request.contextPath}/image/money.png" width="64" height="64" />
	                                 分享到朋友圈
	         </button>
	
	      </div>
	   </div>
	</div>
	</div>
	
	<div id="oldHb" style="display:none;">
		<p>不能重复获取红包！</p>
		<p>您已经获得了一个面值为<span color="red">0.1</span>BTC的红包！</p>
	</div>
	<div id="activeOver" style="display:none;">
		<p>非常抱歉，您参加的活动已经结束！</p>
	</div>
</body>
</html>