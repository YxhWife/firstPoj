<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<p>openId: ${openId}</p>
	<p>昵称: ${nickName}</p>
	<p>openId 为：<%=request.getAttribute("openId") %></p>
	
	<div id="mcover" onclick="weChat()" style="display:none;">
          <img src="${pageContext.request.contextPath}/image/money.png" />
 	</div>
 	
	<div class="text" id="content">
	   <div id="mess_share">
	      <div id="share_1">
	         <button id="cashBtc" class="button2">
	            <%-- <img src="<%=CoinOkConstants.preUrl%>/link/image/gui.png" width="64" height="64" /> --%>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var imgUrl = "/image/money.png";
	var lineLink = "";
	var descContent = "";
	var shareTitle = "";
	var appid = ""; 
	var isShare = false;
	$(document).ready(function(){
		var shareUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2fc34da508ec05e1&redirect_uri=http%3A%2F%2Fzl-test.coding.io%2Fweixin%2Fprocess.do&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		var obtained = $("#flag").val();
		if(obtained=="1"){
			$("#newHb").show();
			$("#oldHb").hide();
			$("#activeOver").hide();
		}
		if(obtained=="2"){
			$("#newHb").hide();
			$("#oldHb").show();
			$("#activeOver").hide();
		}
		if(obtained=="3"){
			$("#newHb").hide();
			$("#oldHb").hide();
			$("#activeOver").show();
		}
		//朋友圈分享
		$("#shareFriends").click(function(){
			appid = $("#appId").val();
			isShare = true;
			$("#mcover").css("display","block")  // 分享给好友圈按钮触动函数
		});
		
		
		//兑换BTC操作
		$("#cashBtc").click(function(){
			if(isShare==false){
				$.messager.alert("操作提示","请先点击分享到朋友圈");
				return;
			}
			
 			<%-- window.location.href="<%=CoinOkConstants.preUrl%>/weixin/cashBtcAction.do"; --%>
		});
		
		 //分享朋友圈
		 wx.onMenuShareTimeline({
		    title: '我从OKLink领到比特币啦！这么好的事我怎能独享？迫不及待地告诉你：动一动你的手指，最多可的1个比特币！', // 分享标题
		    link: shareUrl, // 分享链接
		    imgUrl: '', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		}); 
		
		//分享给朋友
		 wx.onMenuShareAppMessage({
		    title: 'OkLink抽奖活动', // 分享标题
		    desc: '我从OKLink领到比特币啦！这么好的事我怎能独享？迫不及待地告诉你：动一动你的手指，最多可的1个比特币！', // 分享描述
		    link: shareUrl, // 分享链接
		    imgUrl: '', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		}); 
		
	});
	function button1(){
		$("#mcover").css("display","block")    // 分享给好友按钮触动函数
	}
	function button2(){
		/* $("#mcover").css("display","block")  // 分享给好友圈按钮触动函数 */
	}
	function weChat(){
		$("#mcover").css("display","none");  // 点击弹出层，弹出层消失
	}
function shareFriend() {
      WeixinJSBridge.invoke('sendAppMessage',{
        "appid": appid,
        "img_url": "http://zl-test.coding.io/image/money.png",
        "img_width": "200",
        "img_height": "200",
        "link": "http://zl-test.coding.io/weixin/process.do",
        "desc": "一年一度的OkCoin发送时间到了！小伙伴们抓紧时间抢啦！！！",
        "title": "OkCoin发红利啦"
    	}, function(res) {
        })
        
     
}

function shareTimeline() {
    WeixinJSBridge.invoke('shareTimeline',{
    	"appid": appid,
        "img_url": "http://zl-test.coding.io/image/money.png",
        "img_width": "200",
        "img_height": "200",
        "link": "http://zl-test.coding.io/weixin/process.do",
        "desc": "一年一度的OkCoin发送时间到了！小伙伴们抓紧时间抢啦！！！",
        "title": "OkCoin发红利啦"
    }, function(res) {
    });
}
/* // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 发送给好友
    WeixinJSBridge.on('menu:share:appmessage', function(argv){
        shareFriend();
    },false);
    
    // 分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function(argv){
        shareTimeline();
    });
}, false); */
</script>
</body>
</html>