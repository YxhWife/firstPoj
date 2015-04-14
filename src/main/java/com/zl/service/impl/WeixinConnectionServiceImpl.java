package com.zl.service.impl;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zl.service.WeixinConnectionService;
import com.zl.util.HttpUtils;
import com.zl.util.ZlConstant;

@Service
public class WeixinConnectionServiceImpl implements WeixinConnectionService {
	private static final Logger logger = Logger.getLogger(WeixinConnectionServiceImpl.class);
	public String getUserOpenId(String code) {
		logger.info("code=" + code );
		String openId = null;
		String url = ZlConstant.WEIXIN_OPENID_URL;
		url = url.replace("MY_CODE", code);
		try {
			JSONObject json = HttpUtils.getWeixinReturn(url);
			if(json==null){
				logger.error("页面授权获取用户的openid出错，返回为空！");
				return null;
			}
			openId = json.getString("openid");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return openId;
	}
	
	public String getAccessToken(String accessToken){
		return null;
	}

}
