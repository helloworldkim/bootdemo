package com.example.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mapper.Member1Mapper;
import com.example.security.SecurityUser;
import com.example.vo.Member1VO;

@Controller
@RequestMapping(value = "/security")
public class SecurityController {
	@Value("${member.admin.name}") private String ADMINNAME;
	@Value("${server.myserver.addr}") private String SERVERADDR;
	@Value("${server.myserver.port}") private String SERVERPORT;
	@Value("${server.port}") private String SERVERPORTSPRING;
	
	@Autowired
	Member1Mapper member1Mapper;
		
		@GetMapping(value = "/manager")
		public String manager(HttpServletRequest request,Model model,
				Authentication auth) {
			if(auth !=null) {
				SecurityUser user =  (SecurityUser) auth.getPrincipal();
				if(user != null) {
					
					System.out.println("아이디:"+user.getUsername());
					System.out.println("이름:"+user.getName());
					System.out.println("가입일자:"+user.getUserdate());
					Collection<GrantedAuthority> roles= user.getAuthorities();
					//여러개의 권한을 가질수있음
					for(GrantedAuthority role : roles) {
						System.out.println("권한:"+role.getAuthority());
					}
				}
			}
			
			return "security_manager";
		}
	
		@GetMapping(value = "/page403")
		public String page403(HttpServletRequest request,Model model) {
			
			return "security_page403";
		}
		
		@GetMapping(value = "/admin")
		public String admin(HttpServletRequest request,Model model) {
			return "security_admin";
		}
		
		@GetMapping(value = "/home")
		public String home(HttpServletRequest request, Model model) {
			System.out.println("서버:"+SERVERADDR);
			System.out.println("port:"+SERVERPORT);
			System.out.println("spring properties:"+SERVERPORTSPRING);
			System.out.println("CONTROLLER:"+ADMINNAME);
		return "security_home";
		}
		
		@GetMapping(value = "/join")
		public String join(HttpServletRequest request, Model model) {
			
		return "security_join";
		}
		
		@PostMapping(value = "/join")
		public String joinp(HttpServletRequest request, Model model,@ModelAttribute Member1VO obj) {
			//암호화
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String pw = passwordEncoder.encode(obj.getPassword());
			obj.setPassword(pw);
			System.out.println(obj.toString());
			member1Mapper.memberJoin(obj);
		return "redirect:"+request.getContextPath()+"/security/home";
		}
		
		@GetMapping(value = "/login")
		public String login(HttpServletRequest request,Model model) {
			
			return "security_login";
		}
	
}
