package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//실제 인터셉트를 하는 수행하는 bean
@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//session 사용시 선언해서 사용
				HttpSession httpSession = request.getSession();
		String path = request.getServletPath();
		String query = request.getQueryString();
		if(query ==null) {
			System.out.println("INTERCEPTOR:"+path);
			httpSession.setAttribute("SESSION_CURRENT_URL",path);
		}else {
			System.out.println("INTERCEPTOR:"+path+"?"+query);
			httpSession.setAttribute("SESSION_CURRENT_URL",path+"?"+query);
		}	
		
		
		
		
		//super.postHandle(request, response, handler, modelAndView);
	}
	
	

}
