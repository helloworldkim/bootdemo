package com.example.vo;

import java.util.Arrays;

public class BoardVO {

	private int brd_no=0;
	private String brd_title=null;
	private String brd_content=null;
	private int brd_hit=0;
	private String brd_writer=null;
	private String brd_date=null;
	private byte[] brd_img=null; //오라클 type이 BLOB
	
	
	public byte[] getBrd_img() {
		return brd_img;
	}
	public void setBrd_img(byte[] brd_img) {
		this.brd_img = brd_img;
	}
	public int getBrd_no() {
		return brd_no;
	}
	public void setBrd_no(int brd_no) {
		this.brd_no = brd_no;
	}
	public String getBrd_title() {
		return brd_title;
	}
	public void setBrd_title(String brd_title) {
		this.brd_title = brd_title;
	}
	public String getBrd_content() {
		return brd_content;
	}
	public void setBrd_content(String brd_content) {
		this.brd_content = brd_content;
	}
	public int getBrd_hit() {
		return brd_hit;
	}
	public void setBrd_hit(int brd_hit) {
		this.brd_hit = brd_hit;
	}
	public String getBrd_writer() {
		return brd_writer;
	}
	public void setBrd_writer(String brd_writer) {
		this.brd_writer = brd_writer;
	}
	public String getBrd_date() {
		return brd_date;
	}
	public void setBrd_date(String brd_date) {
		this.brd_date = brd_date;
	}
	@Override
	public String toString() {
		return "BoardVO [brd_no=" + brd_no + ", brd_title=" + brd_title + ", brd_content=" + brd_content + ", brd_hit="
				+ brd_hit + ", brd_writer=" + brd_writer + ", brd_date=" + brd_date + ", brd_img="
				+ Arrays.toString(brd_img) + "]";
	}
	
	
}
