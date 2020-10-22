package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Item;
import com.example.repository.ItemRepository;

	

@Controller
@RequestMapping(value = "/jpa")
public class JPAController {
	@Autowired
	ItemRepository iRepository;
	
	@GetMapping(value = {"/item_home.do","/"})
	public String home(HttpServletRequest request,Model model) {
		
		return "/item/item_home";
	}
	//페이지호출
	@GetMapping(value = "/item_update.do")
	public String update(HttpServletRequest request,Model model,
			@RequestParam(value = "no",defaultValue = "0") long no) {
		Item obj = iRepository.findById(no).get();
		//List<Item> list = iRepository.sqlselectByNo(no);
		model.addAttribute("obj",obj);
		return "/item/item_update";
	}
	
	//업데이트 일어나는부분
		@PostMapping(value = "/item_update.do")
		public String updatep(HttpServletRequest request,Model model,
				@ModelAttribute Item obj,
				@Param("date1") String date1){
			System.out.println(obj.toString());	
			/*
			SimpleDateFormat transFomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = transFomat.parse(date1);
			obj.setDate(date);
			System.out.println(obj.toString());
			*/
			
			iRepository.sqlUpdateByNo(obj);
			//iRepository.save(obj);
			
			return "redirect:"+request.getContextPath()+"/jpa/item_select.do";
	}
		
	//링크타고 들어올때!
	@GetMapping(value = "/item_delete.do")
	public String delete(HttpServletRequest request,Model model,@RequestParam(value = "no",defaultValue = "0") long no) {
		//비정상적인 접근
		if(no==0) {
			//리스트페이지로 보냄
			return "redirect:"+request.getContextPath()+"/jpa/item_select.do";
		}
		System.out.println(no);
		iRepository.sqldeleteByNo(no);
		
		//iRepository.deleteByNo(no);
		
		return "redirect:"+request.getContextPath()+"/jpa/item_select.do";
	}
	//ajax로 삭제
	@PostMapping(value = "/item_delete.do")
	public String deletep(HttpServletRequest request,Model model,@RequestParam(value = "no",defaultValue = "0") long no) {
		//비정상적인 접근
		if(no==0) {
			//리스트페이지로 보냄
			return "redirect:"+request.getContextPath()+"/jpa/item_select.do";
		}
		//iRepository.deleteByNo(no);
		iRepository.sqldeleteByNo(no);
		return "redirect:"+request.getContextPath()+"/jpa/item_select.do";
	}
	
	//물품 리스트페이지
	@GetMapping(value = "/item_select.do")
	public String select(HttpServletRequest request,Model model) {
		//기존에 메서드 사용
		//Iterable<Item> list = iRepository.findAll();
		
		//커스텀한 메서드 사용
		//List<Item> list = iRepository.findAllByOrderByNoDesc();
		
		//네이티브 쿼리 직접적으로 선언해서 사용하는방법
		List<Item> list = iRepository.sqlOrderByNoDesc();
		model.addAttribute("list",list);
		return "/item/item_list";
	}
	
	//물품등록 페이지
	@GetMapping(value = "/item_insert.do")
	public String insert(HttpServletRequest request,Model model) {
		
		return "/item/item_insert";
	}
	//물품등록 서비스부분
	@PostMapping(value = "/item_insert.do")
	public String insertp(HttpServletRequest request,Model model,@ModelAttribute Item obj) {
		//insert에 해당하는부분
		iRepository.save(obj);
		System.out.println(obj.toString());
		
		return "redirect:"+request.getContextPath()+"/jpa/item_home.do";
	}
	
	
	
	
}
