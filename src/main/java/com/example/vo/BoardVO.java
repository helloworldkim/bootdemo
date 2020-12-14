package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {

	private int brd_no=0;
	private String brd_title=null;
	private String brd_content=null;
	private int brd_hit=0;
	private String brd_writer=null;
	private String brd_date=null;
	private byte[] brd_img=null; //오라클 type이 BLOB
	
	
}
