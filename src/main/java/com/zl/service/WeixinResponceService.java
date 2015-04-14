package com.zl.service;

import java.util.Map;

public interface WeixinResponceService {
	/**
	 * 微信响应推送图文消息
	 * @param openId
	 * @return
	 */
	public String newsMessageReponse(String openId,String url,String imgUrl);
	/**
	 * 微信响应推送关注消息
	 * @param openId
	 * @param content
	 * @return
	 */
	public String subscribeMessageReponse(String openId,String content);
	/**
	 * 
	 * @param event
	 * @param openId
	 * @param map
	 * @return
	 */
	public String processEvent(String event,String openId,Map<String, String> map);
	
}
