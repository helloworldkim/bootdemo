package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MEMBER_SEQ")
	@SequenceGenerator(initialValue = 1,sequenceName = "MEMBER_SEQ",name = "MEMBER_SEQ",allocationSize = 1)
	private long id;
	
	
	private String userid;
	private String userpw;
	private String username;
	private String userphone;
	private int userage;
	private LocalDateTime userdate;

}
