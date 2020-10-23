package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ItemImg;

public interface ItemImgRepsoitory extends JpaRepository<ItemImg, Long> {
		
		//SELECT * FROM ITEM_IMG_TBL WHERE ITMNO =:no;
		List<ItemImg> findAllByItmno(long itmno);
		
		ItemImg findAllByno(long no);
}
