package com.kong.king.spring.exam02.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kong.king.spring.exam02.entity.MemoBoard;


public interface MemoBoardRepository extends JpaRepository<MemoBoard, Long> {
	
	List<MemoBoard> findByMidBetweenOrderByMidDesc(Long from, Long to);
	Page<MemoBoard> findByMidBetwenn(Long from, Long to, Pageable pageable);
	@Query("select m from MemoBoard m order by m.mid desc")
	List<MemoBoard> getMemoBoardListAllDesc();
	
	@Transactional
	@Modifying
	@Query("update MemoBoard m set m.content = :content where m.mid = :mid")
	int updateMemoBoardContentWithMid(@Param("mid") Long mid, @Param("content") String content);
	
	@Transactional
	@Modifying
	@Query("update MemoBoard m set m.content = :#{#param.content} where m.mid = :#{#param.mid}")
	int updateMemoBoardContentWithObj(@Param("param") MemoBoard memo);
	
	//insert 작업 : save(엔티티 객체)
	//Select 작업 : findById(키타입), getOne(키 타입)
	//Update 작업 : save(엔티티 객체)
	//Delete 작업 : deleteById(키타입), delete(엔티티 객체)
	
}