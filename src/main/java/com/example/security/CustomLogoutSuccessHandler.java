package com.example.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

//위의 상속도 가능 인터페이스 상속도 가능! 선택1
//public class CustomLogoutSuccessHandler  extends ForwardLogoutSuccessHandler {
	public class CustomLogoutSuccessHandler  implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		String url  = request.getContextPath()+"/security/home";
		response.sendRedirect(url);
		
	}
	


}
