package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Item;


public interface ItemRepository extends CrudRepository<Item, Long>   {
	
	//SELECT * FROM ITEMTBL WHERE ITMNO=:no
	Item findAllByNo(long no);
	
	//SELECT COUNT(*) FROM ITEMTBL WHERE ITMNAME LIKE '%' ||'사과''||'%';
	long countByNameIgnoreCaseContaining(String name);
	
	//SELECT * FROM ITEMTBL WHERE ITMNAME LIKE '%' ||'사과''||'%'
	List<Item> findAllByNameIgnoreCaseContainingOrderByNoAsc(String name,Pageable pageable);
	
	//// 참고 : https://docs.spring.io/spring-data/jpa/docs/1.10.1.RELEASE/reference/html/#jpa.sample-app.finders.strategies
	//select * from itemtbl order by no desc
	List<Item> findAllByOrderByNoDesc();
	
	
	//만약 jpa에서 쿼리문을 명시하고 사용하길 원하는경우
	@Query(value = "SELECT * FROM ITEMTBL ORDER BY ITMNO DESC", nativeQuery = true)
	List<Item> sqlOrderByNoDesc();
	
	//DELETE FROM ITEMTBL WHERE ITMNO=:no	//jpa
	//DELETE FROM ITEMTBL WHERE ITMNO=#{no} //마이바티스
	//DELETE FROM ITEMTBL WHERE ITMNO=:#{#obj.no} //jpa object사용시
	//DELETE FROM ITEMTBL WHERE ITMNO=?
	
	//500error 발생하여 추가한부분
	@Transactional
	int deleteByNo(long no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM ITEMTBL WHERE ITMNO=:no",nativeQuery = true)
	int sqldeleteByNo(@Param("no") long no);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ITEMTBL SET ITMNAME=:#{#item.name}, ITMCONTENT=:#{#item.content}, ITMPRICE=:#{#item.price}, ITMDATE=SYSDATE WHERE ITMNO=:#{#item.no}"
	,nativeQuery = true)
	int sqlUpdateByNo(@Param("item") Item obj);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "SELECT * FROM ITEMTBL WHERE ITMNO=:no",nativeQuery = true)
	List<Item> sqlselectByNo(@Param("no") long no);
	
}
