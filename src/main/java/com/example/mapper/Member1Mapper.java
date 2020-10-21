package com.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.vo.Member1VO;

@Mapper
public interface Member1Mapper {

	@Insert({
		"INSERT INTO MEMBER1TBL(ID,USERNAME,PASSWORD,NAME,ROLE,USERDATE)",
		"VALUES(SEQ_MEMBER1_ID.NEXTVAL,#{obj.username},#{obj.password},#{obj.name},#{obj.role},sysdate)"
		
	})
	public int memberJoin(@Param("obj") Member1VO mem);
	
	@Select({"SELECT * FROM MEMBER1TBL WHERE USERNAME=#{str}"})
	public Member1VO memberLogin(@Param("str") String username);

}
