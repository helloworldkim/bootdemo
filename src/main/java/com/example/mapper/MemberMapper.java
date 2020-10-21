package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.vo.MemberVO;

//mybatis 3.3버전 이상부터 지원됨
@Mapper
public interface MemberMapper {
	//MemberVO mem을 받은부분을 obj라는 이름으로 사용하겠다고 선언함
	@Insert({"INSERT INTO MEMBERTBL(USERID,USERPW,USERNAME,USERPHONE,USERAGE,USERDATE) VALUES(#{obj.userId},#{obj.userPw},#{obj.userName},#{obj.userPhone},#{obj.userAge},SYSDATE)"})
	public int memberJoin(@Param("obj") MemberVO mem);
	
	@Select({"SELECT * FROM MEMBERTBL WHERE USERID=#{id}"})
	public MemberVO memberOne(@Param("id") String id);
	
	public int memberUpdate();
	
	@Select({"SELECT USERID,USERNAME,USERAGE FROM MEMBERTBL WHERE USERID=#{obj.userId} AND USERPW=#{obj.userPw}"})
	public MemberVO memberLogin(@Param("obj") MemberVO mem);
	
	@Select({"SELECT COUNT(*) FROM MEMBERTBL WHERE USERID=#{id}"})
	public int memberIDCheck(@Param("id") String id);
	

	@Insert({
		"<script>",
			"<foreach collection='list' item='obj' separator=' ' open='INSERT ALL' close='SELECT * FROM DUAL'>",
				"INTO MEMBERTBL(USERID, USERPW, USERNAME, USERPHONE, USERAGE, USERDATE) ",
				"VALUES(#{obj.userId}, #{obj.userPw},#{obj.userName},#{obj.userPhone}, #{obj.userAge}, SYSDATE)",
			"</foreach>",
	"</script>"
	})
	public int memberBatchInsert(@Param("list") List<MemberVO> list);

	@Delete({
		"<script>",
		"DELETE FROM MEMBERTBL WHERE USERID IN",
		"<foreach collection='array' item='str' separator=',' open='(' close=')'>#{str}</foreach>",
		"</script>"
	})
	public int memberBatchdelete(String[] id);
	
	@Select({
		"<script>SELECT * FROM MEMBERTBL WHERE USERID IN<foreach collection='array' item='tmp' separator=',' open='(' close=')'>#{tmp}</foreach></script>"
	})
	public List<MemberVO> memberListWhere(String[] id);
	
	@Update({
		"<script>",
		"UPDATE MEMBERTBL SET",
		"USERNAME=",
		"<foreach collection='list' item='obj' separator=' ' open='(CASE' close='END)'>WHEN USERID=#{obj.userId} THEN #{obj.userName}</foreach>,",
		"USERAGE=",
		"<foreach collection='list' item='obj' separator=' ' open='(CASE' close='END)'>WHEN USERID=#{obj.userId} THEN #{obj.userAge}</foreach>,",
		"USERPHONE=",
		"<foreach collection='list' item='obj' separator=' ' open='(CASE' close='END)'>WHEN USERID=#{obj.userId} THEN #{obj.userPhone}</foreach>",
		"WHERE USERID IN <foreach collection='list' item='obj' separator=',' open='(' close=')'>#{obj.userId}</foreach></script>"
	})
	public int memberBatchUpdate(@Param("list") List<MemberVO> list);
	
	@Select({"SELECT * FROM MEMBERTBL ORDER BY USERDATE DESC"})
	public List<MemberVO> memberALL();
}
