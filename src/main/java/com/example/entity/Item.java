package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name="ITEMTBL") //사용할 테이블 이름
@SequenceGenerator(name = "SEQ_JPA",
sequenceName = "SEQ_ITEM_ITMNO", //사용할 시퀀스 이름
initialValue = 1, //초기값
allocationSize = 1) //증가값
public class Item{
	
	@Id //기본키 지정
	@Column(name = "ITMNO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_JPA") //기본키에 사용할 시퀀스 선언!
	private Long no;
	
	@Column(name = "ITMNAME")
	private String name;
	
	@Column(name = "ITMCONTENT")
	private String content;
	
	@Column(name = "ITMPRICE")
	private Long price;
	
	//update시 시간 자동저장됨
	//@UpdateTimestamp 
	
	@Column(name = "ITMDATE")
	@CreationTimestamp //날짜 자동으로 timestamp 형식으로 들어가도록 지정
	private Date date;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Item [no=" + no + ", name=" + name + ", content=" + content + ", price=" + price + ", date=" + date
				+ "]";
	}
	
	
}
