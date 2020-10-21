package com.example.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mapper.Member1Mapper;
import com.example.vo.Member1VO;
//유저 로그인 담당하는부분 spring security!

@Service
public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	Member1Mapper member1Mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username을 넘겨서 일치하는 사용자 1명의 정보 받기
		Member1VO obj = member1Mapper.memberLogin(username);
		
		//잘못된 정보로 로그인시도시 nullexcetion 오류방지용
		if(obj ==null) return null;
		
		//ADMIN,MANAGER 여러개의 권한을 가질수도 있음
		String[] strRoles = {obj.getRole()};
		Collection<GrantedAuthority> roles = AuthorityUtils.createAuthorityList(strRoles);
		
		//사용자 객체 넘기기(아이디,암호,권한들 Collection)
		//다른기능을 더 추가원할시 상속해서 사용해야함!
		//return new User(obj.getUsername(), obj.getPassword(), roles);
		
		//User를 상속해서 다른클래스를 만듬(아이디,암호,이름,가입날짜,권한들)
		return new SecurityUser(obj.getUsername(), obj.getPassword(), obj.getName(), obj.getUserdate(), roles);
	}

}
