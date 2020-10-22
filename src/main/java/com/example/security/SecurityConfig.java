package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomeUserDetailsService myUserDetailsService;
	
	//@Autowired
	//CustomLoginSuccessHandler myLoginSuccessHadnler;
	
	
	//비밀번호 암호화에 사용된 클래스 선언
	@Bean
	public BCryptPasswordEncoder BCE() {
		return new BCryptPasswordEncoder();
	}
	
	//인증관련해서 사용할 부분!
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
						//인증관련해서 사용할 클래스 명시 			//비밀번호 암호화에 사용한부분 명시
		auth.userDetailsService(myUserDetailsService).passwordEncoder(BCE());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/css/**","/script/**","/image/**","/fonts/**","/lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//ip주소:8080/security/admin/* =>
		http.authorizeRequests()
		.antMatchers("/security/admin","/security/amdin/*").hasAuthority("ADMIN")
		.antMatchers("/security/manager","/security/manager/*").hasAnyAuthority("ADMIN","MANAGER")
		.anyRequest().permitAll().and()
		
		.formLogin().loginPage("/security/login")
		.loginProcessingUrl("/security/loginProcess")
		//.usernameParameter("username") //디폴트값!
		//.passwordParameter("password") // 디폴트값! input name값을 다르게 주면 해당부분에 사용한 name값 명시해줘야함
		.permitAll()
		//로그인 유저의 권한에따라 다른주소를 줘야한다면 handler를 사용해서!
		//.successHandler(myLoginSuccessHadnler)
		.successHandler(new CustomLoginSuccessHandler())
		//.defaultSuccessUrl("/security/home")
		.and()
		
		.logout().logoutUrl("/security/logout") //post로만 호출가능 get 안됨
		//.logoutSuccessUrl("/security/home")
		.logoutSuccessHandler(new CustomLogoutSuccessHandler())
		.invalidateHttpSession(true) //session clear
		.clearAuthentication(true) 
		.permitAll().and()
	
		.exceptionHandling().accessDeniedPage("/security/page403");
		
		http.csrf().disable();//비권장 다른파일도 같이 하고있어서 일단설정함
		
	}
	
	

	
}
