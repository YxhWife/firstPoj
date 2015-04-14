package com.zl.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weibo")
public class WeiboController {
	
	@RequestMapping("/process.do")
	public String process(HttpServletRequest request,HttpServletResponse response){
		
		return null;
	}
	
	@RequestMapping("/attribute.do")
	public void attribute(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String zl = (String) request.getAttribute("zl");
		response.getWriter().print(zl);
	}

}
