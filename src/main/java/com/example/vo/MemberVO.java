package com.example.vo;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Component
@Data // lombok annotiation
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId = null;
	private String userPw = null;
	private String userName = null;
	private String userPhone = null;
	private int userAge = 0;
	private String userDate = null;

}
