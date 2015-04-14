package com.zl.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.dom4j.Document;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zl.domain.Article;
import com.zl.domain.NewsMessage;
import com.zl.domain.TextMessage;


public class HttpUtils {
	private static char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * httpClient请求
	 * @param url 请求地址
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getWeixinReturn(String url) throws JSONException{
		JSONObject json = null;
		CloseableHttpClient http = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			response = http.execute(post);
			HttpEntity entity = response.getEntity();
		    String tempStr = EntityUtils.toString(entity);
		    json = new JSONObject(tempStr);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
			}
		}
		return json;
	}
	/**
	 * 带参数的post请求
	 * @param url
	 * @param para
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getWeixinReturn(String url,String para) throws JSONException{
		JSONObject json = null;
		CloseableHttpClient http = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			List<BasicNameValuePair> paraList = new ArrayList<>();
			paraList.add(new BasicNameValuePair("wecha_id", ""));
			paraList.add(new BasicNameValuePair("tid", "42"));
			paraList.add(new BasicNameValuePair("chid", "310,306,277,288,"));
			paraList.add(new BasicNameValuePair("token", "jesbzi1427704547"));
			paraList.add(new BasicNameValuePair("action", "add_vote"));
			post.setEntity(new UrlEncodedFormEntity(paraList,"utf-8"));
//			StringEntity se = new StringEntity(para,"utf-8");
//			post.setEntity(se);
			response = http.execute(post);
			HttpEntity entity = response.getEntity();
			String tempStr = EntityUtils.toString(entity);
			json = new JSONObject(tempStr);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	
	/**
	 * xml解析
	 * @param request
	 * @return Map
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static Map<String,String> xmlPares(HttpServletRequest request) throws Exception{
		Map<String, String> map = new HashMap<>();
		
		InputStream inputStream = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		//获取xml的根节点
		Element root = document.getRootElement();
		//根据根节点获取xml的节点列表
		List<Element> elementList = root.elements();
		//将节点元素存入map
		for(Element e : elementList){
			map.put(e.getName(),e.getText());
		}
		inputStream.close();	
		
		return map;
	}
	
	/**
	 * 获取一个XStream实例
	 * @return
	 */
	public static XStream getXStreamInstance(){
		
		XStream xstream = new XStream(new XppDriver() {
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {
					// 对所有xml节点的转换都增加CDATA标记
					boolean cdata = true;
					public void startNode(String name, Class clazz) {
						super.startNode(name);
					}
					protected void writeText(QuickWriter writer, String text) {
						if (cdata) {
							writer.write("<![CDATA[");
							writer.write(text);
							writer.write("]]>");
						} else {
							writer.write(text);
						}
					}
				};
			}
		});
		
		return xstream;
	}
	
	/**
	 * 图文消息对象转换成xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		
		XStream xstream = getXStreamInstance();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * 文本消息对象转成xml
	 * @param newsMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		
		XStream xstream = getXStreamInstance();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	/**
	 * 获取accessToken
	 * @return
	 * @throws JSONException 
	 */
	public static String getAccessToken() throws JSONException{ 
		
//		String APPID = "wx2fc34da508ec05e1";
//		String APPSECRET = "4509dc791c36732242b56031965f38f2";
		
	    String APPID = ZlConstant.WEIXIN_APP_ID;
	    String APPSECRET = ZlConstant.WEIXIN_APP_SECRET;
	    
	    String url = ZlConstant.WEIXIN_ACC_TOKEN_URL;
	    url = url.replace("MY_APPID", APPID).replace("MY_SECRET", APPSECRET);
        JSONObject json = HttpUtils.getWeixinReturn(url);
        String accessToken = json.getString("access_token");
        
		return accessToken;
	}
	/**
	 * oauth2.0获取用户的基本信息： Scope=snsapi_base
	 * @param code
	 * @return
	 * @throws JSONException 
	 */
	public static Map<String, String> getOauthUserOpenid(String code) throws JSONException {
		String openId = null;
		String accessToken = null;
		Map<String, String> map = new HashMap<String, String>();
		
		String url = ZlConstant.WEIXIN_OPENID_URL;
		String appId = ZlConstant.WEIXIN_APP_ID;
		String secret = ZlConstant.WEIXIN_APP_SECRET;
		url = url.replace("MY_CODE", code).replace("MY_APPID", appId).replace("MY_SECRET", secret);
		
		JSONObject json = getWeixinReturn(url);
		if(json==null){
			return null;
		}
		openId = json.getString("openid");
		accessToken = json.getString("access_token");
		map.put("openId", openId);
		map.put("accessToken",accessToken);
		return map;
	}
	
	/**
	 * oauth2.0获取用户的基本信息： Scope为snsapi_userinfo
	 * @param code
	 * @return
	 * @throws JSONException 
	 */
	public static Map<String, String> getOauthUserInfo(String code) throws JSONException{
		Map<String, String> map = new HashMap<String, String>();
		String url = ZlConstant.WEIXIN_OAUTH_USERINFO_URL;
		Map<String, String> basicMap = getOauthUserOpenid(code);
		
		url = url.replace("OPENID", basicMap.get("openId")).replace("ACCESS_TOKEN", basicMap.get("accessToken"));
		JSONObject json = getWeixinReturn(url);
		
		if(json==null){
			return null;
		}
		map.put("nickName", json.getString("nickname"));
		map.put("headImgUrl", json.getString("headimgurl"));
		map.put("openId", json.getString("openid"));
		
		return map;
	}
	/**
	 * 获取jsApi接口签名相关信息
	 * @return map
	 * @throws JSONException 
	 */
	public static Map<String, String> getJsSign(String url) throws JSONException{
		
		Map<String, String> map = new HashMap<String, String>();
		String sign = null;
		
		String accToken = getAccessToken();
		String jsTicketUrl = ZlConstant.WEIXIN_JS_TOKEN_URL.replace("ACCESS_TOKEN", accToken);
		JSONObject json = getWeixinReturn(jsTicketUrl);
		String jsTicket = json.getString("ticket");
		
		System.out.println(jsTicket);
		//step2  组成要生成签名的字符串
		String timeStamp = String.valueOf(new Date().getTime()/1000);
		String nonceStr = ZlConstant.WEIXIN_NONCESTR;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("jsapi_ticket=")
		.append(jsTicket)
		.append("&noncestr=")
		.append(nonceStr)
		.append("&timestamp=")
		.append(timeStamp)
		.append("&url=")
		.append(url);
		
		//step3 SHA-1加密生成签名
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(sBuffer.toString().getBytes());
			sign = bytesToHexStr(digest);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		map.put("timeStamp", timeStamp);
		map.put("nonceStr", nonceStr);
		map.put("sign", sign);
		
		return map;
	}
	
	private static String bytesToHexStr(byte[] bytePara){
		char [] tempChar = new char[2];
		StringBuffer sb = new StringBuffer(); 
		for(byte b : bytePara){
			tempChar[0] = Digit[(b >>> 4) & 0X0F];
			tempChar[1] = Digit[b & 0X0F];
			sb.append(new String(tempChar));
		}
		return sb.toString();
	}
	/**
	 * 获取关注信息
	 * @param openId
	 * @return
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> getSubscribeInfo(String openId) throws JSONException, UnsupportedEncodingException{
		
		Map<String, String> map = new HashMap<String, String>(); 
		String accToken = getAccessToken();
		String url = ZlConstant.WEIXIN_SUB_USERINFO_URL;
		url = url.replace("ACCESS_TOKEN", accToken).replace("OPENID", openId);
		JSONObject json = getWeixinReturn(url);
		String subStatus = json.getString("subscribe");
		String nickname = json.getString("nickname");
		nickname = new String(nickname.getBytes("utf-8"));
		map.put("nickname", nickname);
		map.put("subscribe", subStatus);
		
		return map;
	}
	public static void main(String args[]) throws JSONException, UnsupportedEncodingException{
//		Map<String, String> map = getJsSign("http://zl-test.coding.io/weixin/test.do");
//		System.out.println(map.get("sign"));
//		System.out.println(map.get("timeStamp"));
//		System.out.println(map.get("nonceStr"));
		String url = "http://weixin.taipengvip.com/index.php?g=Wap&m=Vote&a=add_vote&token=jesbzi1427704547&wecha_id=";
		for(int i=0;i<100;i++){
			JSONObject jsonObject = getWeixinReturn(url,null);
			try{
				System.out.println(i+": "+jsonObject);
			}catch(Exception e){
				System.out.println("解析失败！");
			}
		}
		//Map<String, String> map = getSubscribeInfo("oSu1qt2q4K5Q6oEZLUfRgAtoOCWQ");
		//System.out.print(map.get("nickname"));
		
		
	}
	
}
