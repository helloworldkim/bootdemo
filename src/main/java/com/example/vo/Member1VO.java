package com.example.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok annotiation
@AllArgsConstructor
@NoArgsConstructor
public class Member1VO implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private long id=0;
		private String username=null;
		private String password=null;
		private String name=null;
		private String role=null;
		private String userdate=null;
		
		
}
