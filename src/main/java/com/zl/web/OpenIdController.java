package com.zl.web;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zl.annotation.Person;
import com.zl.service.RandomValidateCode;
import com.zl.service.UserService;
import com.zl.service.WeixinConnectionService;
import com.zl.service.WeixinResponceService;
import com.zl.util.HttpUtils;
import com.zl.util.ZlConstant;

@Controller
//@Scope("prototype")
@RequestMapping("/weixin")
public class OpenIdController {
	private static final Logger logger = Logger.getLogger(OpenIdController.class);
	
	@Autowired
	private WeixinConnectionService wcService;
	@Autowired 
	private RandomValidateCode rvc;
	@Autowired
	private WeixinResponceService wxResponceService;
	@Autowired
	private UserService userService;
//	@Autowired 
//	private Person person;
	
	@RequestMapping("/obtain.do")
	public String obtain(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		//String shareTag = request.getParameter("shareTag");
		String openId = wcService.getUserOpenId(code);
		String shareTag = "1";
//		String openId = "zhanglin";
		request.getSession().setAttribute("openid", openId);
		request.setAttribute("openId", openId);
		request.setAttribute("shareTag", shareTag);
		String language = request.getHeader("ACCEPT-LANGUAGE");
		System.out.println(language);
		return "/demo";
	}
	
	@RequestMapping("/lanuch.do")
	public String lanuchActive(HttpServletRequest request,HttpServletResponse response) throws IOException {
		logger.error("lanuch.do  callback !!!");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String responseMessage = null;
		
//此为微信后台绑定回调接口时使用的校验信息		
//		// 微信加密签名
//		String signature = request.getParameter("signature");
//		// 时间戳
//		String timestamp = request.getParameter("timestamp");
//		// 随机数
//		String nonce = request.getParameter("nonce");
//		// 随机字符串
//		String echostr = request.getParameter("echostr");
//		out.print(echostr);
		
		try {
			Map<String, String> xmlMap = HttpUtils.xmlPares(request);
			String openId = xmlMap.get("FromUserName");
			request.getSession().setAttribute("openId", openId);
			
			String msgType = xmlMap.get("MsgType");
			String content = xmlMap.get("Content");
			String event = xmlMap.get("Event");
			String key = xmlMap.get("EventKey");
			
			if("text".equals(msgType.trim())&&"oklink".equalsIgnoreCase(content)){
				//step3——发送活动入口链接
				String imgUrl = ZlConstant.WEIXIN_IMG_URL;
				String url = ZlConstant.WEIXIN_GET_BTC;
				responseMessage = wxResponceService.newsMessageReponse(openId,url,imgUrl);
				logger.info("text invoked openId= " +openId);
			}
			if("subscribe".equals(event)){
				responseMessage = wxResponceService.subscribeMessageReponse(openId, ZlConstant.WEIXIN_TEXT_CONTENT);
			}
//			if("VIEW".equals(event)){
//				Map<String, String> map = HttpUtils.getSubscribeInfo(openId);
//				logger.error("VIEW invoked openId= " +openId);
//				System.out.println("VIEW invoked openId= " +openId + ",昵称为：  "+map.get("nickname"));
//				
//			}
			if("CLICK".equals(event)){
				Map<String, String> map = new HashMap<String, String>();
				if("GRAB".equals(key)){
//					String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11e71e028d193357&redirect_uri=https%3A%2F%2Fwww.oklink.com%2Fhongbao%2FpreGrab.do%3FactiveId%3D43ee00ad-adad-11e4-aba7-d8490bd13cfc&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
//					response.sendRedirect(url);
					
					return "redirect:/weixin/validimg.do";
				}
				map.put("key", key);
				responseMessage = wxResponceService.processEvent(event, openId, map);
			}
			logger.info("XML Parse openId" + openId);
		} catch (Exception e) {
			logger.error("解析微信返回的xml时出错！");
			e.printStackTrace();
		}
		
		out.print(responseMessage);
		return null;
	}
	
	//验证码输入
	@RequestMapping("/validimg")
	public void getValidImg(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> map = rvc.getRandcode();
		String parm = request.getParameter("p");
		
		String rootUrl = request.getRequestURL().toString();
		String urlParm = request.getQueryString();
		String currentUrl = rootUrl + urlParm;
		
		System.out.println(rootUrl);
		System.out.println(currentUrl);
		try {
			request.setAttribute("validStr", map.get("valid"));
			ImageIO.write((RenderedImage) map.get("image"),"JPEG" ,response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/callback")
	public String receiveProcess(HttpServletRequest request,HttpServletResponse response) throws JSONException{
		
		//String openId = (String) request.getSession().getAttribute("openId");
		String code = request.getParameter("code");
		logger.error("code 值为：" + code);
		if(code!=null){
			Map<String, String> map = HttpUtils.getOauthUserInfo(code);
			request.setAttribute("openId", map.get("openId"));
			request.setAttribute("nickName", map.get("nickName"));
		}else{
			String openId = (String)request.getSession().getAttribute("openId");
			request.setAttribute("openId", openId);
		}
		return "/share";
	} 
	
	@RequestMapping("/test.do")
	public String test(HttpServletRequest request,HttpServletResponse response) throws JSONException{
		//userService.insertUser();
		//User user = userService.getAllUsers().get(1);
//		String url = request.getRequestURL().toString();
//		Map<String, String> map = HttpUtils.getJsSign(url);
//		System.out.println(url);
//		request.setAttribute("sign", map.get("sign"));
//		request.setAttribute("timeStamp", map.get("timeStamp"));
//		request.setAttribute("nonceStr", map.get("nonceStr"));
//		logger.error("sign= "+ map.get("sign")+" __timeStamp= "+ map.get("timeStamp")+"__nonceStr= "+map.get("nonceStr")+"app_id= "+ZlConstant.WEIXIN_APP_ID);
		return "/demo1";
	}
	
	@RequestMapping("/picture.do")
	public void picture(HttpServletRequest request,HttpServletResponse response) throws JSONException{
		
		String accToken = "Wr1EyDzC71wQ49FLJBrKnqwvjwwSq56qQCY9JjOz7f-MR3iHLRkreP7U8ATqwYB3cAK_2XbPzVHyS6YOcCfMlTnizaptbRJc3G_f9OmwmAw";
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		url = url.replace("TOKEN", accToken);
		String para = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}";
		JSONObject json = HttpUtils.getWeixinReturn(url, para);
		String ticket = json.getString("ticket");
		System.out.println(ticket);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pic.do")
	public String pic(HttpServletRequest request,HttpServletResponse response){
		String url = "http://mp.weixin.qq.com/s?__biz=MjM5MjkwMTc4Nw==&mid=204208476&idx=1&sn=d566a9de8ed1c8fd99e670b30c87fc01&scene=1&from=groupmessage&isappinstalled=0#rd";
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
//	@RequestMapping("/pic.do")
//	public String pic(HttpServletRequest request,HttpServletResponse response){
//		request.setAttribute("zl", "zhanglin");
//		return "redirect:/weibo/attribute.do";
//	}
	@RequestMapping("/attribute.do")
	public void attribute(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//String zl = (String) request.getAttribute("zl");
		String zla = request.getParameter("path");
		response.getWriter().print(zla);
	}
	
	@RequestMapping("/hbRedir.do")
	public void hbRedir(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String tag = request.getParameter("tag");
		
		String url = "";
		if("0".equals(tag)){
			url = ZlConstant.WEIXIN_HB_SEND_URL;
		}
		if("1".equals(tag)){
			url = ZlConstant.WEIXIN_HB_WITHDRAW_URL;
		}
		response.sendRedirect(url);
		
	}
	
	@RequestMapping("/aTest.do")
	public void aTest(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<Integer, String> map = new HashMap<Integer, String>();
		PrintWriter out = response.getWriter();
		map.put(1, "zhanglin");
		map.put(2, "Yangxianhuan");
		//JSONObject jsonObject = new JSONObject(map);
		//jsonObject.toString();
		//request.setAttribute("json", jsonObject.toString());
		out.print(map);
		//return "/json";
	}
	
}
