package com.zl.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class SpiderUtil {
	
	public static final String FILE_PATH_HTML = "F://spider//toumi.html";
	public static final String FILE_PATH_CSS = "F://spider//toumi_css.html";
	public static final String FILE_PATH_WEIXIN = "F://spider//weixin.html";
	
	public static void getHtml(String url,String filePath) throws IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		
		try {
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String htmlStr = stream2String(entity.getContent(),"utf-8");
			saveHtml(htmlStr,filePath );
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			httpClient.close();
		}
	}
	
	public static String stream2String(InputStream stream,String charSet) throws IOException{
		StringBuffer sBuffer = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(stream,charSet));
			String content = "";
			while((content=br.readLine())!=null){
				sBuffer.append(content);
			}
					
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				br.close();
			}
		}
		return sBuffer.toString();
	}
	
	public static void saveHtml(String content,String filePath) throws IOException{
		
		if(content == null||filePath == null){
			return;
		}
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath,true),"utf-8");
		try {
			osw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			osw.close();
		}
		
	}

	public static void main(String args[]){
		
		try {
			//String requestUrl = "http://www.itoumi.com/resources/css/newver/reg_base.css";
			String requestUrl = "http://mp.weixin.qq.com/s?__biz=MjM5MjkwMTc4Nw==&mid=204181022&idx=1&sn=75f40ece7c7a1a4206856da450cc6abe&scene=1&from=singlemessage&isappinstalled=0&key=fbe9f9f4b565962cc7d03813edd5344d8420e5675da5619f0f72fb4d4dc6f2b9362f5caea8b21f44933e3ff3078b1c73&ascene=1&uin=ODk1NTQwNzI4&devicetype=webwx&version=70000001&pass_ticket=xqRohrnUxIgUOeLFL6xyuTeHEroYqOai2t9HkGJ2FE0n8BmgiaP9zqILA26vueac";
			//String requestUrl = "https://www.itoumi.com/login.shtml";
			getHtml(requestUrl,FILE_PATH_WEIXIN);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
