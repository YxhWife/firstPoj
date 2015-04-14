package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zl.util.HttpUtils;
import com.zl.util.ZlConstant;

public class OtherTest {

	public static void main(String args[]) throws UnsupportedEncodingException, JSONException{
		String openUrl = "http://zzll.coding.io/weixin/obtain.do";
		String lanuchUrl = " ";
		openUrl = URLEncoder.encode(openUrl, "utf-8");
		lanuchUrl = URLEncoder.encode(lanuchUrl, "utf-8");
		System.out.println(openUrl);
		
		String oathUrl = ZlConstant.WEIXIN_OAUTH_URL;
		oathUrl = oathUrl.replace("MY_URI", openUrl);
		//System.out.println(oathUrl);
		
		String lanuch = ZlConstant.WEIXIN_OAUTH_URL;
		lanuch = lanuch.replace("MY_URI", lanuchUrl);
		//System.out.println(lanuch);
		
		Random rr = new Random();
		double zl = rr.nextInt();
		//System.out.println(zl);
		
		Calendar  cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		//cal.add(Calendar.DATE, 3);
		//cal.add(Calendar.HOUR, 0);
		//date = cal.getTime();
		System.out.println(cal.get(Calendar.HOUR));
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		
		Date now = new Date();
		String dateString = df.format(now);
		System.out.println(dateString);
		try {
			Date date2 = df.parse(dateString);
			if(date2.after(now)){
				System.out.println(date2);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
