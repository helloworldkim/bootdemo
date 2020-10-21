package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.mapper.MemberMapper;
import com.example.vo.MemberVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	MemberMapper mMapper;

	@RequestMapping(value = "/home",method = {RequestMethod.GET})
	public String home(HttpServletRequest  request,
			Model model,
			@RequestParam(value = "menu",defaultValue = "0",required = false)int menu) {
		
		if (menu==0) {
			return "redirect:"+request.getContextPath()+"/admin/home?menu=1";
		}
		else if(menu==4) {
			List<MemberVO> list = mMapper.memberALL();
			model.addAttribute("list",list);
			
		}
		return "admin_home";
	}
	
	//@PostMapping(value = "/batchinsert")
	@RequestMapping(value = "/batchinsert",method =RequestMethod.POST)
	public String batchinsert(HttpServletRequest request,
			@RequestParam(value="id[]") String[] id,
			@RequestParam(value="pw[]") String[] pw,
			@RequestParam(value="name[]") String[] name,
			@RequestParam(value="phone[]") String[] phone,
			@RequestParam(value="age[]") int[] age) {

		List<MemberVO> list = new ArrayList<MemberVO>();
		
		for(int i=0; i<id.length;i++) {
			MemberVO tmp = new MemberVO();
			tmp.setUserId(id[i]);
			tmp.setUserPw(pw[i]);
			tmp.setUserName(name[i]);
			tmp.setUserPhone(phone[i]);
			tmp.setUserAge(age[i]);
			list.add(tmp);
			
		}
		//배치실행!
		mMapper.memberBatchInsert(list);

		return "redirect:"+request.getContextPath()+"/admin/home?menu=2";
	}
	
	@RequestMapping(value = "/batchupdatedelete", method = RequestMethod.POST)
	public String batchud(HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam("btn") String btnName,
			@RequestParam(value="chk[]", required=false) String[] id) {
		//System.out.println(btnName);
		
		if(btnName.equals("일괄삭제")) {
			mMapper.memberBatchdelete(id);
		}
		else if (btnName.equals("일괄수정")) {
				redirectAttributes.addFlashAttribute("ids", id);
				return "redirect:" + request.getContextPath() + "/admin/update";
			
		}
		
		//완료되면 menu=4로 화면 이동
		return "redirect:" + request.getContextPath() + "/admin/home?menu=4";
	}
	
	//업데이트 페이지 불러오는곳
	//@RequestMapping(value = "/update",method = {RequestMethod.GET})
	@GetMapping(value = "/update")
	public String updatePage(HttpServletRequest request,Model model) {
		
		Map<String,?> map = RequestContextUtils.getInputFlashMap(request);
		//redirect된 값이없을경우 다시 해당메뉴로 돌려보냄
		
		if(map==null) {
			return "redirect:"+request.getContextPath()+"/admin/home?menu=4";
		}
		String[] chk = (String[]) map.get("ids");
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = mMapper.memberListWhere(chk);
		
		model.addAttribute("list",list);
		return"admin_update";
	}
	
	//실제업데이트
	//@RequestMapping(value = "/update",method = {RequestMethod.POST})
	@PostMapping(value = "/update")
	public String batchupdate(HttpServletRequest request,
			@RequestParam(value = "userId") String[] id,
			@RequestParam(value = "userPw") String[] pw,
			@RequestParam(value = "userName") String[] name,
			@RequestParam(value = "userPhone") String[] phone,
			@RequestParam(value = "userAge") int[] age
			) {
			List<MemberVO> list = new ArrayList<MemberVO>();
			for(int i=0; i<id.length;i++) {
				MemberVO tmp = new MemberVO();
				tmp.setUserId(id[i]);
				tmp.setUserPw(pw[i]);
				tmp.setUserName(name[i]);
				tmp.setUserPhone(phone[i]);
				tmp.setUserAge(age[i]);
				list.add(tmp);
				
			}
			mMapper.memberBatchUpdate(list);
		
		return"redirect:"+request.getContextPath()+"/admin/home?menu=4";
	}
	
	//에러페이지
	@ExceptionHandler
	public String handleException(Exception e) {
		return "viewError";
	}
	
	
}
