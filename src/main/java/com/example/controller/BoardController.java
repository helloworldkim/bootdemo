package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.mapper.BoardMapper;
import com.example.vo.BoardVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	BoardMapper boardMapper;

	@RequestMapping(value = "/list1",method = RequestMethod.GET)
	public String boardList1(@RequestParam(value = "txt",defaultValue = "",required = false) String txt,
			Model model) {
		int cnt = boardMapper.boardCount(txt);
		model.addAttribute("cnt",(cnt-1)/10+1);
		return "board_list1";
	}
	/*
	//주소만 method부분을 생략가능! get이니까
	@GetMapping(value = "/list2")
	public String boardList2(@RequestParam(value = "txt",defaultValue = "",required = false) String txt,
			Model model) {
		int cnt = boardMapper.boardCount(txt);
		model.addAttribute("cnt",(cnt-1)/10+1);
		return "board_list1";
	}
	*/
	
	//127.0.0.1:8080/board/imgview?no=?
	@RequestMapping(value = "/imgview", method = RequestMethod.GET)
	public ResponseEntity<byte[]> imgview(HttpServletRequest request,@RequestParam(value = "no" ,defaultValue = "0") int no) throws IOException{
		try {
			if(no>0) {
				BoardVO board = boardMapper.boardImg(no);
				if(board.getBrd_img().length>0) {//이미지가 null이 아니면
					HttpHeaders header = new HttpHeaders();
					header.setContentType(MediaType.IMAGE_JPEG);
					ResponseEntity<byte[]> ret = 
							new ResponseEntity<byte[]>(board.getBrd_img(),header,HttpStatus.OK);
					return ret;
				}
			}
			return null;
		} catch (Exception e) {
			InputStream in = request.getServletContext().getResourceAsStream("/resources/img/defaultimg.jpg");
			byte[] tmp = IOUtils.toByteArray(in);
				if(tmp.length>0) {//이미지가 null이 아니면
					HttpHeaders header = new HttpHeaders();
					header.setContentType(MediaType.IMAGE_JPEG);
					ResponseEntity<byte[]> ret = 
							new ResponseEntity<byte[]>(tmp,header,HttpStatus.OK);
					return ret;
				}
			System.out.println(request.getServletContext());
			System.out.println("ERROR:"+e.getMessage());
			return null;
		}
	}
	//http://localhost:8080/board/list?page=2
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Model model, HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "page",defaultValue = "0",required = false) int page,
			@RequestParam(value = "txt",defaultValue = "", required = false) String txt) {
		
		if(page==0) {//페이지번호가 없으면 1페이지로 이동시킴
			return "redirect:"+request.getContextPath()+"/board/list?page=1";
		}
		Map<String,Object> map = new HashMap<>();

		System.out.println("txt값:"+txt);
		//조회수 증가방지 세션값 1줌
		session.setAttribute("BOARDHIT_SESSION", 1);
		//map으로 한번에!
		List<BoardVO> list =boardMapper.boardList(page*10-9,page*10,txt);
		//총 게시물 갯수
		int cnt = boardMapper.boardCount(txt);
		
		model.addAttribute("list", list);
		model.addAttribute("cnt", (cnt-1)/10+1);
		model.addAttribute("txt",txt);
		
		
		return "board_list";
	}
	
	@RequestMapping(value = "/write" , method = RequestMethod.GET)
	public String write() {
		
		return"board_write";
	}
	
	@RequestMapping(value = "/write" , method = RequestMethod.POST)
	public String write1(HttpServletRequest request,
			@ModelAttribute BoardVO board,
			@RequestParam("tmp_img") MultipartFile[] imgs) {
		//이미지 첨부여부 확인 
		if(imgs != null && imgs.length>0) {
			for(MultipartFile tmp:imgs) {
				try {
					board.setBrd_img(tmp.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(board.toString());
		int ret = boardMapper.boardWrite(board);
		
		return"redirect:"+request.getContextPath()+"/board/list";
	}
	@RequestMapping(value = "/content/{no}", method = RequestMethod.GET)
	public String content(HttpServletRequest request, HttpSession session,Model model,
			//@RequestParam(value="no", defaultValue = "0") int no
			@PathVariable(value = "no") int no
			) {
		System.out.println(no);
		if(no==0) { //글번호 정보가 없다면 다시 목록화면으로 보냄
			return "redirect:"+ request.getContextPath()+"/board/list";
		}
		Integer chk = (Integer) session.getAttribute("BOARDHIT_SESSION");
		if(chk !=null) {
			if(chk==1) {
				boardMapper.boardUpdateHit(no); //성공0 실패1
				session.setAttribute("BOARDHIT_SESSION", 0);
			}
		}
		//게시물 1개 가져오기
		BoardVO obj = boardMapper.boardContent(no);
		
		//이전글,다음글번호 가져오기
		int prev = boardMapper.boardPrevNo(no);
		int next = boardMapper.boardNextNo(no);
		model.addAttribute("obj",obj);
		model.addAttribute("prev",prev);
		model.addAttribute("next",next);
		return "board_content";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model,@RequestParam(value="no", defaultValue = "0") int no) {
		try {
			BoardVO obj = boardMapper.boardOne(no);
			model.addAttribute("obj",obj);
			return "board_update";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
				
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update1(HttpServletRequest request, @ModelAttribute BoardVO board) {
		System.out.println(board.toString());
		boardMapper.updateBoardOne(board);
		
		return "redirect:"+ request.getContextPath()+"/board/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, @RequestParam("no") int no) {
		try {
			System.out.println(no);
			boardMapper.deleteBoardOne(no);
			return "redirect:"+request.getContextPath()+"/board/list";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}
	
	@ExceptionHandler
	public String handleException(Exception e) {
		return "viewError";
	}
	
}
