package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")//Access-Control-Allow-Origin 설정! 해야지만 외부파일에서 해당서버로 접근이 가능함
@RequestMapping(value = "/rest")
public class ReactRestController {

	@RequestMapping(value = "/test1.json",method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Map<String,Object> test1(
			@RequestParam("name") String name,
			@RequestParam("age") int age){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "홍길동 "+ name);
		map.put("userage", 34+age);
		
		return map;
	}
	
	@RequestMapping(value = "/test1.json",method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Map<String,Object> test1p(
			@RequestBody Map<String,Object> requestMap){
		String name = (String) requestMap.get("name");
		int age = (int) requestMap.get("age");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "홍길동 "+ name);
		map.put("userage", 34+age);
		System.out.println(map);
		return map;
	}
}
