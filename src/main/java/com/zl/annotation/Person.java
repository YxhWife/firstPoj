package com.zl.annotation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

//@Component("person")   //此处通过注解的方式定义受管Bean  
public class Person {  
    private String userId = "cjm";  
    private String userName = "zhanglin";  
      
   // @VisitorRole   //自定义注解的使用。只有具有ADMIN角色才能调用本方法。  
    public String say(){  
        return "I'm " + userName;  
    } 
    //@VisitorRole   //自定义注解的使用。只有具有ADMIN角色才能调用本方法。  
    public String sayHi(){  
    	return "Hi, " + userName;  
    } 
    public static void main(String args[]) throws ParseException{
    	String dateStr = "2014-12-11 10:57:49";
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date now =new Date();
    	System.out.println(f.format(now));
    	Date date = f.parse(dateStr);
    	System.out.println(date.getTime());
    }
} 
