package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.MemberMapper;

@RestController
@RequestMapping(value = "/rest")
@CrossOrigin("*")//Access-Control-Allow-Origin 설정! 해야지만 외부파일에서 해당서버로 접근이 가능함
public class MemberRestController {
	
	@Autowired
	MemberMapper mMapper;
	
	//일반기준
	@RequestMapping(value = "/idcheck.json",
			method = {RequestMethod.GET ,RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.ALL_VALUE})
	public Map<String,Object> idcheck(
			@RequestParam(value = "userId",defaultValue = "") String userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		int check = mMapper.memberIDCheck(userId);

		map.put("data",check);
		return map;
	}
	
	//restful api기준
	@RequestMapping(value = "/idcheck/{userId}",
			method = {RequestMethod.GET ,RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.ALL_VALUE})
	public Map<String,Object> idcheck1(@PathVariable(value = "userId") String userId) {
		System.out.println(userId);
		Map<String,Object> map = new HashMap<String, Object>();
		
		int check = mMapper.memberIDCheck(userId);

		map.put("data",check);
		return map;
	}

}
