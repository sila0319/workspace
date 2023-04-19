package com.kong.king.spring.guest04.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.kong.king.spring.guest04.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>,
							QuerydslPredicateExecutor<Guestbook>{
	
//	List<Guestbook> findAll(builder peageable);
}
//