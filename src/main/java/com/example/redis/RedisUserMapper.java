package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.vo.MemberVO;

@Service
public class RedisUserMapper {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	public void setUser(String key, Object value) {
		ValueOperations<String, Object> obj = redisTemplate.opsForValue();
		obj.set(key, value);
	}
	
	public MemberVO getUser(String key) {
		ValueOperations<String, Object> obj = redisTemplate.opsForValue();
		//기존 String 에서 MemberVO로 value 지정값을 바꿈 
		//String value = (String) obj.get(key);
		MemberVO value = (MemberVO) obj.get(key);
		return value;
	}
	
	public void delUser(String key) {
		redisTemplate.delete(key);
	}
	
}
