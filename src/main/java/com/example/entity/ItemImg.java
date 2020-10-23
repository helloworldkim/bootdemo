package com.example.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "ITEM_IMG_TBL")
@SequenceGenerator(name = "SEQ_IMG",
sequenceName = "SEQ_ITEM_IMG_NO",
initialValue = 1,
allocationSize = 1)
public class ItemImg {

	@Id
	@Column(name = "NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_IMG")
	private long no;
	
	
	@Column(name = "ITMNO")
	private long itmno;
	
	//oracle blob타입이라 lob어노테이션 선언해줘야함
	@Lob
	@Column(name = "IMG")
	private byte[] img;
	
	@Column(name = "REGDATE")
	@UpdateTimestamp
	private Date regdate;
	
	@Transient //제외!! 임시로 사용할변수 선언함
	private String strimg;

	
	public String getStrimg() {
		return strimg;
	}

	public void setStrimg(String strimg) {
		this.strimg = strimg;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getItmno() {
		return itmno;
	}

	public void setItmno(long itmno) {
		this.itmno = itmno;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "ItemImg [no=" + no + ", itmno=" + itmno + ", img=" + Arrays.toString(img) + ", regdate=" + regdate
				+ "]";
	}
	
	
	
}
