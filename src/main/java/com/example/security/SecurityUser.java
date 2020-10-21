package com.example.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {

	private static final long serialVersionUID = 1L;
	
	
	private String username =null;
	private String password =null;
	private String name =null;
	private String userdate=null;
	
	//상속받은 부모의 생성자
	public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserdate() {
		return userdate;
	}

	public void setUserdate(String userdate) {
		this.userdate = userdate;
	}

	//추가한 생성자
	public SecurityUser(String username, String password,String name,String userdate,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.username=username;
		this.password=password;
		this.name=name;
		this.userdate= userdate;
		
		
	}

}
