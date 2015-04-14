<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发红包</title>
</head>
<body>
	<p>您的openID为：${zl}</p>
	<p><img id="validImg" alt="${validStr}" src="${pageContext.request.contextPath}/openid/validimg.do" onclick="javascript:refresh()"></p>
	<c:if test="${shareTag eq 0}" >
		<h2>这是转发的链接，不能领取红包！</h2>
	</c:if>
	<p>${shareTag}</p>
	<c:if test="${shareTag eq 1}" >
		<h2>恭喜您抢到了0.1个BTC</h2>
	</c:if>
	
	<input type="button" id="addContact" onclick="javascript:addWxContact('gh_65950ccb8413')" value="ADD">
	<a href="http://weixin.qq.com/r/n0jRydvEboYRrZ_q9x2N">关注</a>
	<p>${zl}</p>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.js"></script>

<script type="text/javascript">
 function refresh(){
	 $("#validImg").attr("src","${pageContext.request.contextPath}/openid/validimg.do?"+Math.random());
 }	
   var imgUrl = "http://zl-test.coding.io/image/money.png";
   var lineLink = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9c82a62af1d894c2&redirect_uri=http%3A%2F%2Fzl-test.coding.io%2Fweixin%2Fobtain.do&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
   var shareTitle = "快乐抢红包";
   
        function shareFriend() {
            WeixinJSBridge.invoke('sendAppMessage',{
                "img_url": imgUrl,
                "img_width": "200",
                "img_height": "200",
                "link": lineLink,
                "desc": descContent,
                "title": shareTitle
            	}, function(res) {
                })
        }

        function shareTimeline() {
            WeixinJSBridge.invoke('shareTimeline',{
                "img_url": imgUrl,
                "img_width": "200",
                "img_height": "200",
                "link": lineLink,
                "desc": descContent,
                "title": shareTitle
            }, function(res) {
            });

        }

        function shareWeibo() {

            WeixinJSBridge.invoke('shareWeibo',{
                "content": descContent,
                "url": lineLink,
            }, function(res) {

            });
        }

        // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
        document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
            // 发送给好友
            WeixinJSBridge.on('menu:share:appmessage', function(argv){
                shareFriend();
            });
            // 分享到朋友圈
            WeixinJSBridge.on('menu:share:timeline', function(argv){
                shareTimeline();
            });
        }, false);
        
        //添加关注
        function addWxContact(wxid,cb)
        {
        	
            if (typeof WeixinJSBridge == 'undefined') return false;
            alert("brgin！");   
            WeixinJSBridge.invoke('addContact', {
                    webtype: '1',
                    username: wxid
                }, function(d) {
                    // 返回d.err_msg取值，d还有一个属性是err_desc
                    // add_contact:cancel 用户取消
                    // add_contact:fail　关注失败
                    // add_contact:ok 关注成功
                    // add_contact:added 已经关注
                    WeixinJSBridge.log(d.err_msg);
                    cb && cb(d.err_msg);
                });
        };
        
</script>
</html>