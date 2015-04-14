package test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zl.util.HttpUtils;

public class ErWeiM {
	
	private static Double getRandomHbVal(Random random ,Double hbTotal,int hbNum){
		BigDecimal mulTen = new BigDecimal(10);
		int hbTotalVal = (int) (hbTotal * 10);
		
		if(hbNum == 1){
			BigDecimal tempVal = new BigDecimal(hbTotalVal);
			tempVal = tempVal.divide(mulTen).setScale(1, BigDecimal.ROUND_DOWN);
			return tempVal.doubleValue();
		}
		int avg = (int)(hbTotalVal/hbNum)*2;
		Long valTemp = Long.valueOf(random.nextInt(avg));
		if(valTemp==0){
			valTemp++;
		}
		BigDecimal tempVal = new BigDecimal(valTemp);
		tempVal = tempVal.divide(mulTen).setScale(1, BigDecimal.ROUND_DOWN);
		return tempVal.doubleValue();
	}
	
	public static boolean isLegalNum(String email){
//	 if (isEmpty(email)) {
//		  return Boolean.valueOf(false);
//	}
     String regex ="^[1-9][0-9]*$" ;
     Pattern p = Pattern.compile(regex);
	 Matcher m = p.matcher(email);
	 return Boolean.valueOf(m.matches());
	}
		 
	public static void main(String args[]) throws JSONException{
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		
		double val = getRandomHbVal(random ,3.5,3);
		//System.out.println(val);
		Double dd = 1.0115;
		BigDecimal bb = new BigDecimal(dd);
		bb = bb.multiply(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_DOWN);
		//System.out.println(bb.doubleValue());
//		System.out.println(isLegalNum("0"));
//		System.out.println(isLegalNum("1.0"));
//		System.out.println(isLegalNum("123.02"));
//		System.out.println(isLegalNum("023.02"));
//		System.out.println(isLegalNum("121"));
//		System.out.println(isLegalNum("1"));
		
//		Double dd = 1.01100;
//		String str = dd.toString();
//		System.out.println(str);
		
		SimpleDateFormat dformat = new SimpleDateFormat("MM-dd HH:mm:ss");
		System.out.println(dformat.format(new Date()));
		
//		String cc = "{\"touser\":\"OPENID\",\"msgtype\":\"text\", \"text\":{ \"content\":\"CON_VAL\" }}";
//		cc = cc.replace("CONTENE_VAL", "zhanglin");
//		System.out.println(cc);
		
	}

}
