package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Item;
import com.example.entity.ItemImg;
import com.example.repository.ItemImgRepsoitory;
import com.example.repository.ItemRepository;

	

@Controller
@RequestMapping(value = "/jpa")
public class JPAController {
	@Autowired
	ItemRepository iRepository;
	
	@Autowired
	ItemImgRepsoitory imgRepository;
	
	//배치할때 추가한부분 트랜젝션 관리 할때 씀
	@Autowired
	EntityManagerFactory emf;
	
	@GetMapping(value = {"/item_home.do","/"})
	public String home(HttpServletRequest request,Model model) {
		
		return "/item/item_home";
	}
	//배치 페이지이동
	@GetMapping(value = "/item_insert_batch.do")
	public String insertbatchget(HttpServletRequest request,Model model) {
		return "/item/item_insert_batch";
	}
	//batch insert 수행하는 곳
	@PostMapping(value = "/item_insert_batch.do")
	public String insertbatchpost(HttpServletRequest request,Model model,
			@RequestParam("names[]") String[] names,
			@RequestParam("contents[]") String[] contents,
			@RequestParam("prices[]") String[] prices) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		String sql = "INSERT INTO ITEMTBL(ITMNO,ITMNAME,ITMCONTENT,ITMPRICE,ITMDATE)"
				+" VALUES(SEQ_ITEM_ITMNO.NEXTVAL, :name, :content, :price, SYSDATE)";
		for(int i=0; i<names.length; i++) {
			em.createNativeQuery(sql)
				.setParameter("name", names[i])
				.setParameter("content", contents[i])
				.setParameter("price", prices[i])
				.executeUpdate();
		}
		tx.commit();
		
		return "redirect:"+request.getContextPath()+"/jpa/item_home.do";
	}
	@GetMapping(value = "/item_update_batch.do")
	public String updatebatchGet(HttpServletRequest request,Model model) {
		
		return "/item/item_update_batch";
	}
	//batch update 수행
	@PostMapping(value = "/item_update_batch.do")
	public String updatebatchpost(HttpServletRequest request,
			@RequestParam("names[]") String[] names,
			@RequestParam("contents[]") String[] contents,
			@RequestParam("prices[]") String[] prices,
			@RequestParam("nos[]") String[] nos) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		String sql = "UPDATE ITEMTBL SET ITMNAME=:name, ITMCONTENT =:content,ITMPRICE=:price WHERE ITMNO=:no";
		for(int i=0; i<names.length; i++) {
			em.createNativeQuery(sql)
				.setParameter("name", names[i])
				.setParameter("content", contents[i])
				.setParameter("price", prices[i])
				.setParameter("no", nos[i])
				.executeUpdate();
		}


		tx.commit();
		
		return "redirect:"+request.getContextPath()+"/jpa/item_home.do";
	}
	
	//이미지삭제
	@GetMapping(value = "/item_image_delete.do")
	public String imgDelete(HttpServletRequest request,Model model,
			@RequestParam(value = "no",defaultValue = "0")long no) {
		if(no>0) {
			imgRepository.deleteById(no);
		}
		
		return "redirect:"+request.getContextPath()+"/jpa/item_select.do";
	}
	
	//이미지 2개 업로드
	@PostMapping(value ="/item_image_insert.do")
	public String imgInsert(HttpServletRequest request,Model model,
			@RequestParam(value = "no",defaultValue = "0") long no,
			@RequestParam(value = "img1") MultipartFile[] files) throws IOException {
		
			System.out.println("이미지길이:"+files.length);
			//이미지 db에 저장하는부분
			if(files.length>0) {
				for(MultipartFile file : files) {
					if(file.getSize()>0) {
						ItemImg img = new ItemImg();
						img.setItmno(no);
						img.setImg(file.getBytes());
						imgRepository.save(img);
					}
				}
			}
			
			return "redirect:"+request.getContextPath()+"/jpa/item_select.do";
	}
	//상세페이지 내에서 이미지 물품있으면 해당이미지도 보여줌
	@GetMapping(value = {"/item_detail.do"})
	public String detail(HttpServletRequest request,Model model,
			@RequestParam(value = "no",defaultValue = "0") int no) {

		Item item = iRepository.findAllByNo(no);
		
		List<ItemImg> list =  imgRepository.findAllByItmno(no);
		//ItemImg entity에 임시변수 선언함
		//String[] imgs = new String[list.size()]; //3

			//for(int i =0; i<list.size(); i++) {
			for(ItemImg tmp:list) {
			//이미지 파일은 현재 byte[]로 되어있음 
			//byte[] -> encoding ->String형태로 전달할예정
			//imgs[i]= Base64.getEncoder().encodeToString(list.get(i).getImg());
			tmp.setStrimg(Base64.getEncoder().encodeToString(tmp.getImg()));
			tmp.setImg(null);
		}
		model.addAttribute("obj",item);
		//model.addAttribute("imgs",imgs);
		model.addAttribute("list",list);

		return "/item/item_detail";
	}
	//페이지로 연결만 시켜줌
	@RequestMapping(value = "/itm_img_view",method = RequestMethod.GET)
	public String itmImgView(HttpServletRequest request,Model model) {
		
		return "/item/item_detail_view";
	}
	
	//여러개를 함께보여줄때 //현재 수정중임
	@RequestMapping(value = "/itm_img_view",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> itmImgViewp(HttpServletRequest request,Model model,
			@RequestParam(value = "no" ,defaultValue = "0") int no) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(no>0) {
			List<ItemImg> list = imgRepository.findAllByItmno(no);
			map.put("result", list);
			System.out.println(list.size());
		}
		
		return map;
	}
	
	//127.0.0.1:8080/jpa/imgview?no=?
	//해당 이미지만 보여줄때
	@RequestMapping(value = "/imgview", method = RequestMethod.GET)
	public ResponseEntity<byte[]> imgview(HttpServletRequest request,
			@RequestParam(value = "no" ,defaultValue = "0") int no) throws IOException{
		try {
			if(no>0) {
				ItemImg obj = imgRepository.findAllByno(no);
				if(obj!=null) {//obj가 존재하면
					HttpHeaders header = new HttpHeaders();
					header.setContentType(MediaType.IMAGE_JPEG);
					byte[] img = obj.getImg();
					
					ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(img,header,HttpStatus.OK);
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
				@ModelAttribute Item obj){
			System.out.println(obj.toString());	

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
	public String select(HttpServletRequest request,Model model,
			@RequestParam(value = "page",defaultValue = "1") int page,
			@RequestParam(value = "name", defaultValue = "") String name) {
		//기존에 메서드 사용
		//Iterable<Item> list = iRepository.findAll();
		
		//커스텀한 메서드 사용
		//List<Item> list = iRepository.findAllByOrderByNoDesc();
		
		//네이티브 쿼리 직접적으로 선언해서 사용하는방법
		//List<Item> list = iRepository.sqlOrderByNoDesc();
		//게시물전체개수
		long total = iRepository.countByNameIgnoreCaseContaining(name);
		//0부터 시작함 기본값 1으로 설정해둬서 -1처리함 			//불러올 갯수
		PageRequest pageable = PageRequest.of(page-1, 10);
		
		List<Item> list = iRepository.findAllByNameIgnoreCaseContainingOrderByNoAsc(name,pageable);

		
		
		model.addAttribute("list",list);
		model.addAttribute("total",(total-1)/10+1);
		
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
