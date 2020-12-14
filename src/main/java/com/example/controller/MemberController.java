package com.example.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Member;
import com.example.mapper.MemberMapper;
import com.example.repository.MemberRepository;
import com.example.vo.BoardVO;
import com.example.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	//@Autowired
	//MemberMapper mMapper;
	
	@Autowired
	MemberRepository mRepository;
	
	//xml파일을 이용하기!
	//@Autowired
	//UserMapper uMapper;
	//@Autowired
	//SqlSessionFactory SqlSessionFactory;
	//@Autowired
	//SqlSessionTemplate SqlSessionTemplate;
	
	
	
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String join() {
		System.out.println("JOIN PAGE");
		return"join";
	}
	
	@RequestMapping(value = "/join" , method = RequestMethod.POST)
	public String join1(HttpServletRequest request, Model model, @ModelAttribute Member mem) {
		Member member  = new Member()
				.setUserid(mem.getUserid())
				.setUsername(mem.getUsername())
				.setUserpw(mem.getUserpw())
				.setUserphone(mem.getUserphone())
				.setUserage(mem.getUserage())
				.setUserdate(LocalDateTime.now());
		Member newMember = mRepository.save(member);

		//어노테이션 사용방법
		//int ret = mMapper.memberJoin(mem); //0 or 1 결과값
		
		
		//템플릿 사용방법
		//SqlSessionTemplate.insert("com.example.mapper.UserMapper.memberJoin",mem);
		
		//sqlsession 사용방법
		//SqlSession sqlSession =  SqlSessionFactory.openSession();
		//sqlSession.insert("com.example.mapper.UserMapper.memberJoin", mem);
		
		//xml파일을 이용한 mapper 인터페이스 사용방법
		//uMapper.memberJoin(mem);
		
		return"redirect:"+request.getContextPath()+"/member/join";
		//return"redirect:join_ok";
	}
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String login() {
		return"login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginp(HttpServletRequest request,HttpSession session,@ModelAttribute MemberVO mem) {
		System.out.println(mem.toString());
		//MemberVO obj = mMapper.memberLogin(mem);
		
		//로그인실패
		//if(obj==null) {
		//	return "redirect:"+request.getContextPath()+"/member/login";
		//}
		//성공
		//session.setAttribute("USERID_SESSION", obj.getUserId());
		System.out.println(session.getAttribute("USERID_SESSION"));
		return "redirect:"+request.getContextPath()+"/home";		
		
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpSession session) {
		session.removeAttribute("USERID_SESSION");
		//session.invalidate();
		return "redirect:"+request.getContextPath()+"/home";
	}
}
