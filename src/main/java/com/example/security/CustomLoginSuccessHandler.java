package com.example.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.example.demo.Properties;
import com.example.redis.RedisUserMapper;


public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Value("${member.admin.name}") private String ADMINNAME;
	@Value("${member.manager.name}") private String MANAGERNAME;
	@Value("${member.user.name}") private String USERNAME;
	
	@Autowired
	RedisUserMapper redisMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if(httpSession!=null)System.out.println("세션 존재 id값:"+httpSession.getId());
		
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		String url = request.getContextPath()+"/security/home";
		if(user != null) {
			
			System.out.println("기본키:"+user.getId());
			System.out.println("아이디:"+user.getUsername());
			System.out.println("이름:"+user.getName());
			System.out.println("가입일자:"+user.getUserdate());
			Collection<GrantedAuthority> roles= user.getAuthorities();
			System.out.println("권한들:"+roles);
			//로그인 성공시 ! redis db에 해당 유저 아이디 저장 수정해야함
			//redisMapper.setUser(String.valueOf(user.getId()), user.getId());
			//System.out.println(redisMapper.getUser(String.valueOf(user.getId())));
			
			
			//여러개의 권한을 가질수있음
			for(GrantedAuthority role : roles) {
				System.out.println("권한:"+role.getAuthority());
				
				if(role.getAuthority().equals(Properties.ADMINNAME)) {
					url = request.getContextPath()+"/security/admin";
				}
				else if(role.getAuthority().equals(Properties.MANAGERNAME)) {
					url = request.getContextPath()+"/security/manager";
				}
				else if(role.getAuthority().equals(Properties.USERNAME)) {
					url = request.getContextPath()+"/security/user";
				}
			}
		}
		getRedirectStrategy().sendRedirect(request, response, url);
		//super.onAuthenticationSuccess(request, response, authentication);
	}
	
	

}
