package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mapper.MemberMapper;
import com.example.redis.RedisUserMapper;
import com.example.vo.MemberVO;

@Controller
@RequestMapping(value = "/redis")
public class RedisController {
	
	@Autowired
	MemberMapper mMapper;
	
	@Autowired
	RedisUserMapper redisMapper;
	
	@GetMapping(value = "/login")
	public String login() {
		
		return "redis_login";
	}
	@GetMapping(value = "/home")
	public String home(HttpSession httpSession) {
		MemberVO obj1 = redisMapper.getUser(httpSession.getId());
		if(obj1 != null) {
			System.out.println("session아이디값:"+httpSession.getId());
			System.out.println("redis객체값:"+obj1.toString());
			return "redis_home";
		}
		
		return "redis_home";
	}
	
	@PostMapping(value = "/login")
	public String loginp(HttpServletRequest request,@ModelAttribute MemberVO mem,
			HttpSession httpSession) {
		MemberVO obj = mMapper.memberLogin(mem);
		
		if(obj !=null) {
			//오라클 db에 사용자 아이디,암호 일치확인
			//있다면 redis session db에 추가
			//redisMapper.setUser("USERID", mem.getUserId);
			//MemberVO 객체를 저장!
			//session마다 가지는 고유한값으로 저장함
			redisMapper.setUser(httpSession.getId(), obj);
			System.out.println(redisMapper.getUser(httpSession.getId()));
			return "redirect:"+request.getContextPath()+"/redis/home";
		}
		
		return "redirect:"+request.getContextPath()+"/redis/home";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request,HttpSession httpSession) {
		if(httpSession.getId() != null)
		redisMapper.delUser(httpSession.getId());
		
		
		return "redirect:"+request.getContextPath()+"/redis/home";
	}
}
