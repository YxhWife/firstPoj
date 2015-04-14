package com.zl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.zl.domain.Article;
import com.zl.domain.NewsMessage;
import com.zl.domain.TextMessage;
import com.zl.service.WeixinResponceService;
import com.zl.util.HttpUtils;
import com.zl.util.ZlConstant;
@Service
public class WeixinResponceServiceImpl implements WeixinResponceService{
	
	
	public String subscribeMessageReponse(String openId,String content){
		
		String respMessage = null;
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(openId);
		textMessage.setFromUserName(ZlConstant.WEIXIN_ACCOUNT);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType("text");
		textMessage.setContent(content);
		
		respMessage = HttpUtils.textMessageToXml(textMessage);
		return respMessage;
	}
	
	public String newsMessageReponse(String openId,String url, String imgUrl){
		String respMessage = null;
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(openId);
		newsMessage.setFromUserName(ZlConstant.WEIXIN_ACCOUNT);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType("news");

		List<Article> articleList = new ArrayList<Article>();
		// 单图文消息
		Article article = new Article();
		article.setTitle("比特币等你拿，最多可得1BTC");
		article.setDescription("比特币马上就是你的");
		article.setPicUrl(imgUrl);
		article.setUrl(url);
		articleList.add(article);
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		respMessage = HttpUtils.newsMessageToXml(newsMessage);
			
		return respMessage;
	}
	
	@Override
	public String processEvent(String event,String openId,Map<String, String> map){
		String responseMessage = null;
		String key = map.get("key");
		
		if(event==null||key==null){
			return null;
		}
		
		//1-点击发红包或者取现菜单
		if("VIEW".equalsIgnoreCase(event)){
			//Map<String, String> subMap = WeixinUtil.getSubscribeInfo(openId);
			//String nickName = subMap.get("nickname");
			//String userImg = subMap.get("headimgurl");
			//String ip = map.get("ip");
			//wxHbAccountService.insertHbAccount(openId, ip, nickName, userImg,"0");
		}
		
		//2-点击抢红包菜单
		if("CLICK".equalsIgnoreCase(event)&&"GRAB".equals(key)){
			//此处根据时间获取活动的outId，需要设计*********
			/*                                
			/*                                
			/*                                
			/*                                
			/********************************************/
			//String redirUrl = "https://www.oklink.com/hongbao/preGrab.do?activeId=43ee00ad-adad-11e4-aba7-d8490bd13cfc";
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11e71e028d193357&redirect_uri=https%3A%2F%2Fwww.oklink.com%2Fhongbao%2FpreGrab.do%3FactiveId%3D43ee00ad-adad-11e4-aba7-d8490bd13cfc&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
			//图片需加入
			String imgUrl = ZlConstant.WEIXIN_IMG_URL;
			responseMessage = newsMessageReponse(openId, url, imgUrl);
		}
		if("CLICK".equalsIgnoreCase(event)&&"GET".equals(key)){
			//此处根据时间获取活动的outId，需要设计*********
			/*                                
			/*                                
			/*                                
			/*                                
			/********************************************/
			//String redirUrl = "https://www.oklink.com/hongbao/preGrab.do?activeId=43ee00ad-adad-11e4-aba7-d8490bd13cfc";
			String url = ZlConstant.WEIXIN_GET_BTC;
			//图片需加入
			String imgUrl = ZlConstant.WEIXIN_IMG_URL;
			responseMessage = newsMessageReponse(openId, url, imgUrl);
		}
		
		return responseMessage;
	}

}
