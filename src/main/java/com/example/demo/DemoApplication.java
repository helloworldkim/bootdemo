package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//properites 파일 사용위치 지정
@PropertySource("classpath:global.properties")

//jpa entity, repository 등록
@EntityScan(basePackages = {"com.example.entity"})
@EnableJpaRepositories(basePackages = {"com.example.repository"})

//@ComponentScan({"com.example.controller","com.example.member.vo"}) //컨트롤러 파일위치 명시
@SpringBootApplication
@ComponentScan({"com.example.controller",
	"com.example.restcontroller",
	"com.example.security"}) //컨트롤러 파일위치 명시
@MapperScan("com.example.mapper") //mapper위치 명시
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}    

}
