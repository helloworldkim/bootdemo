package com.example.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.example.vo.MemberVO;

@Configuration
public class RedisConfig {
	
	//application.properties 등록한 변수들 가져와 사용
	private @Value("${spring.redis.host}") String HOST;
	private @Value("${spring.redis.port}") int PORT;
	private @Value("${spring.redis.password}") String PASSWORD;
	private @Value("${spring.redis.database}") int DATABASE;
	
	//연결설정
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(HOST);
		config.setPort(PORT);
		config.setPassword(PASSWORD);
		config.setDatabase(DATABASE);
		LettuceConnectionFactory lcf = new LettuceConnectionFactory(config);
		return lcf;
	}
	
	//입력하거나 가져오기 위한 데이터 타입정의
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		//redisTemplate.setKeySerializer(new StringRedisSerializer());
		//redis에 해당 VO객체 자체를 저장하고자 할때 어느클래스인지 명시해줘야함
		redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<>(MemberVO.class));
		//지정 클래스 바꿔봤는데 안됨 수정해야함 ㅡㅡ
		//redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<>(SecurityUser.class));
		return redisTemplate;
	}
	
	

}
