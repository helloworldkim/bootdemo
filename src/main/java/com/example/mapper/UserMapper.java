package com.example.mapper;

import com.example.vo.MemberVO;

//xml 파일을 이용하기위해 만들어본 인터페이스
public interface UserMapper {
	public int memberJoin(MemberVO mem);
}
