package com.example.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	HandlerInterceptor interceptor;

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//인터셉터 등록
		registry.addInterceptor(interceptor)
			.addPathPatterns("/**") //모든경로 인터셉터 통과시키도록 지정
			.excludePathPatterns("/login/**","/logout/**","/resources/**");	//제외경로 login logout 리소스폴더내 파일들(css등)
		//.excludePathPatterns("/jpa/item_home.do/**","/resources/**");	
		
	}

	
}
