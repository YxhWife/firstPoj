package com.zl.util;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuUtil {
	
	private static String getAccessToken() throws JSONException{ 
	       //我的测试账号
		   String APPID="wx2fc34da508ec05e1";
		   String APPSECRET="4509dc791c36732242b56031965f38f2";
		   //生产上的账号
//		   String APPID="wx11e71e028d193357";
//		   String APPSECRET="091ebe036f4eee88e4d5c3184ddcaf86";
		   
	       String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ APPID + "&secret=" +APPSECRET;
	       String accessToken = null;
	       JSONObject json = HttpUtils.getWeixinReturn(url);
	       accessToken = json.getString("access_token");
		return accessToken;
	}
	/**
	 * 创建菜单
	 * @return
	 * @throws JSONException
	 */
    public static String createMenu() throws JSONException {
    	String access_token = getAccessToken();
    	String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
    	StringBuffer sb = new StringBuffer();
//        sb.append("{");
//        sb.append(" \"button\":[");
//        sb.append("     {");
//        sb.append("         \"name\":\"发红包\",");             //URL 连接
//        sb.append("         \"type\":\"view\",");
//        sb.append("         \"url\":\"http://zzll.coding.io/weixin/process.do\""); //连接地址  \"http://zl-test.coding.io/weixin/process.do\"
//        sb.append("     },");
//        sb.append("     {");
//        sb.append("         \"name\":\"活动记录\",");             //URL 连接
//        sb.append("         \"type\":\"view\",");
//        sb.append("         \"url\":\"http://www.baidu.com\"");    //连接地址
//        sb.append("     },");
//        sb.append("     {");
//        sb.append("         \"name\":\"提现\",");             //URL 连接
//        sb.append("         \"type\":\"view\",");
//        sb.append("         \"url\":\"http://www.baidu.com\" ");    //连接地址
//        sb.append("     }");
//        sb.append(" ]");
//        sb.append("}");
        
    	   
        String aboutUrl = "http://eqxiu.com/s/UuyaJZ";
        String aboutOK = "http://eqxiu.com/s/hUtpuh";
        String subMenu ="{\"button\":[{\"name\":\"行情\",\"sub_button\":[{\"type\":\"click\",\"name\":\"OKCoin中国\",\"key\":\"0\"},"
        		+ "{\"type\":\"click\",\"name\":\"OKCoin国际\",\"key\":\"1\"},{\"type\":\"click\",\"name\":\"Bitstamp\",\"key\":\"5\"},"
        		+ "{\"type\":\"click\",\"name\":\"Huobi\",\"key\":\"3\"}]},{\"type\":\"view\",\"name\":\"关于BTC\",\"url\":\""+aboutUrl+"\"},"
        				+ "{\"type\":\"view\",\"name\":\"关于OK\",\"url\":\""+aboutOK+"\"}]}";
        JSONObject json = HttpUtils.getWeixinReturn(url,subMenu);
        //System.out.println(subMenu);
		return json.toString();
    }
    
    /**
     * 删除菜单
     * @return
     * @throws JSONException
     */
	public static String deleteMenu() throws JSONException{
		String access_token = getAccessToken(); 
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+access_token;
		JSONObject json = HttpUtils.getWeixinReturn(url);
		System.out.println(access_token);
		return json.toString();
	}
	
	
	public static void main(String args[]) throws JSONException{
		//System.out.println(deleteMenu());
		System.out.println(getAccessToken());
		System.out.println(createMenu());
	}

}
