package com.zl.util;

public class ZlConstant {
	
	public static final String WEIXIN_ACCOUNT = "gh_c4a91127966d";
	public static final String WEIXIN_APP_ID = "wx2fc34da508ec05e1";
	public static final String WEIXIN_APP_SECRET = "4509dc791c36732242b56031965f38f2";
	public static final String WEIXIN_SCOPE_DEFULT = "snsapi_base";
	public static final String WEIXIN_SCOPE_SPECIFIC = "snsapi_userinfo";
	//用户授权的url
	public static final String WEIXIN_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?"
			+ "appid=wx9c82a62af1d894c2&redirect_uri=MY_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	//获取用户openid的url
	public static final String WEIXIN_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?"
			+ "appid=MY_APPID&secret=MY_SECRET&code=MY_CODE&grant_type=authorization_code";
	
	public static final String WEIXIN_TEXT_CONTENT = "亲，欢迎加入OKLink！在这里，你将接收到比特币行业最新资讯；在这里，你将和我们一起追逐自由；在这里，梦想将向你走来！回复oklink，和我们一起呼吸比特币的空气吧！";
	public static final String WEIXIN_NONCESTR ="DnDgiWM7gCxhL8v0";
	public static final String WEIXIN_ACC_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=MY_APPID&secret=MY_SECRET";
	public static final String WEIXIN_SUB_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String WEIXIN_JS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	public static final String WEIXIN_OAUTH_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
//	public static final String WEIXIN_HB_SEND_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2fc34da508ec05e1&redirect_uri=http%3A%2F%2Flocaltest.oklink.com%2Fhongbao%2FpreSend.do&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
//	public static final String WEIXIN_HB_WITHDRAW_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2fc34da508ec05e1&redirect_uri=http%3A%2F%2Flocaltest.oklink.com%2Fwithdraw%2FshowWithdrawPage.do&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	public static final String WEIXIN_HB_SEND_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11e71e028d193357&redirect_uri=https%3A%2F%2Fwww.oklink.com%2Fhongbao%2FpreSend.do&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	public static final String WEIXIN_HB_WITHDRAW_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11e71e028d193357&redirect_uri=https%3A%2F%2Fwww.oklink.com%2FweixinHb%2Fwithdraw%2FshowWithdrawPage.do&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	
	public static final String WEIXIN_GET_BTC = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2fc34da508ec05e1&redirect_uri=http%3A%2F%2Fzl-test.coding.io%2Fweixin%2Fcallback.do&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	public static final String WEIXIN_IMG_URL = "http://zl-test.coding.io/image/active.png";
	public static final String WEIXIN_HB_STRATEGY_URL = "http://mp.weixin.qq.com/s?__biz=MzAxNTEwNzA3NQ==&mid=205370114&idx=1&sn=de11953d119de8e64b1baf553d07fe9e#rd";
	
	
}
