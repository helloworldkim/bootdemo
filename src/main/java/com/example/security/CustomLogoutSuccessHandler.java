package com.example.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.redis.RedisUserMapper;

//위의 상속도 가능 인터페이스 상속도 가능! 선택1
//public class CustomLogoutSuccessHandler  extends ForwardLogoutSuccessHandler {
	public class CustomLogoutSuccessHandler  implements LogoutSuccessHandler{

	@Autowired
	RedisUserMapper redisMapper;
	
		
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		System.out.println("로그아웃!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("기본키:"+user.getId());
		System.out.println("아이디:"+user.getUsername());
		System.out.println("이름:"+user.getName());
		System.out.println("가입일자:"+user.getUserdate());
		Collection<GrantedAuthority> roles= user.getAuthorities();
		System.out.println("권한들:"+roles);
		//HttpSession session = request.getSession();
		//String id = session.getId();

		//redisMapper.delUser(user.getUsername());
		
		
		String url  = request.getContextPath()+"/security/home";
		response.sendRedirect(url);
		
	}
	


}
