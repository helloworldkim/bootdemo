package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.BoardMapper;
import com.example.vo.BoardVO;


//restcontroller 동일한 이름으로 contorller 생성시 동일한 file이 존재한다는 오류발생함!
@RestController
@RequestMapping(value = "/rest")
@CrossOrigin("*")//Access-Control-Allow-Origin 설정! 해야지만 외부파일에서 해당서버로 접근이 가능함
public class BoardRestController {
	
	@Autowired
	BoardMapper bMapper;
	
	//http://localhost:8080/rest/boardone.json?no=13
	@RequestMapping(value = "/boardone.json",
			method = {RequestMethod.GET, RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.ALL_VALUE})
	public Map<String,Object> boardOne(@RequestParam(value = "no",defaultValue = "0") int no) {
		Map<String,Object> map = new HashMap<String, Object>();
		BoardVO obj = bMapper.boardOne1(no);
		//map.put("data",bMapper.boardOne1(no));
		map.put("data",obj);
		return map;
	}
	
	@RequestMapping(value = "/boardlist.json",
			method = {RequestMethod.GET,RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.ALL_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Map<String,Object> boardlist(
			@RequestParam(value = "page",defaultValue = "1",required = false) int page,
			@RequestParam(value = "txt",defaultValue = "",required = false) String txt,
			Model model){
		//게시물 1개 가져옴
		Map<String,Object> map = new HashMap<String, Object>();
		List<BoardVO> list = bMapper.boardList(page*10-9,page*10,txt);
		int cnt = bMapper.boardCount(txt);

		map.put("data", list);
		map.put("cnt", (cnt-1)/10+1);
		model.addAttribute("cnt",(cnt-1)/10+1);
		return map;
	}
	
	@RequestMapping(value = "/boardlist.json",
			method = {RequestMethod.DELETE},
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.ALL_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Map<String,Object> boarddelete(@RequestParam(value = "no",defaultValue = "0",required = false) int no) {
		System.out.println(no);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", bMapper.deleteBoardOne(no));
		return map;		
	}
}
