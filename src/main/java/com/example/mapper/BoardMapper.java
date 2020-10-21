package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.example.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	//게시판 목록 전체개수
	@Select({"SELECT COUNT(*) FROM BOARDTBL WHERE BRD_TITLE LIKE '%'||#{txt}||'%'"})
	public int boardCount(@Param("txt") String txt);
	
	@Insert({"INSERT INTO BOARDTBL(BRD_NO,BRD_TITLE,BRD_CONTENT,BRD_HIT,BRD_WRITER,BRD_DATE,BRD_IMG) ",
		"VALUES(SEQ_BRD_NO.NEXTVAL, #{obj.brd_title},#{obj.brd_content},0,#{obj.brd_writer},sysdate,#{obj.brd_img})"})
	public int boardWrite(@Param("obj") BoardVO board);
	
	@Results({
		//vo객체랑 컬럼을 매칭하고 jdbcType과 java타입이 다르기때문에 변환을 위해 적어줘야함
		@Result(property = "brd_img",column = "BRD_IMG"
				,jdbcType = JdbcType.BLOB,javaType = byte[].class)
	})
	@Select({"SELECT BRD_IMG FROM BOARDTBL WHERE BRD_NO=#{no}"})
	public BoardVO boardImg(@Param("no")int no);
	
	@Select({"SELECT * FROM (SELECT BRD_NO, BRD_TITLE,BRD_HIT,BRD_WRITER,BRD_DATE,ROWNUM,ROW_NUMBER() OVER (ORDER BY BRD_NO DESC) ROWN FROM BOARDTBL WHERE BRD_TITLE LIKE '%'||#{txt}||'%') B1 WHERE ROWN BETWEEN #{start} AND #{end}"})
	public List<BoardVO> boardList(@Param("start") int start,@Param("end") int end, @Param("txt") String txt);
	
	@Select({"SELECT * FROM BOARDTBL WHERE BRD_NO=#{no}"})
	public BoardVO boardContent(@Param("no") int no);
	
	@Update({"UPDATE BOARDTBL SET BRD_HIT=BRD_HIT+1 WHERE BRD_NO=#{no}"})
	public int boardUpdateHit(@Param("no") int no);
	
	@Select({"SELECT * FROM BOARDTBL WHERE BRD_NO=#{no}"})
	public BoardVO boardOne(@Param("no") int no);
	
	@Select({"SELECT BRD_NO,BRD_TITLE,BRD_WRITER,BRD_HIT,BRD_DATE FROM BOARDTBL WHERE BRD_NO=#{no}"})
	public BoardVO boardOne1(@Param("no") int no);
	
	@Update({"UPDATE BOARDTBL SET BRD_TITLE=#{obj.brd_title},BRD_CONTENT=#{obj.brd_content},BRD_WRITER=#{obj.brd_writer} where BRD_NO=#{obj.brd_no}"})
	public int updateBoardOne(@Param("obj") BoardVO board);
	
	@Select({"SELECT NVL(MAX(BRD_NO),0) FROM BOARDTBL WHERE BRD_NO < #{no}"})
	public int boardPrevNo(@Param("no") int no);
	
	@Select({"SELECT NVL(MIN(BRD_NO),0) FROM BOARDTBL WHERE BRD_NO > #{no}"})
	public int boardNextNo(@Param("no") int no);
	
	@Delete({"DELETE FROM BOARDTBL WHERE BRD_NO=#{no}"})
	public int deleteBoardOne(@Param("no") int no);	
	
}
