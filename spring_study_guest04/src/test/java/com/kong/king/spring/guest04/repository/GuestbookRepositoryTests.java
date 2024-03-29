package com.kong.king.spring.guest04.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kong.king.spring.guest04.entity.Guestbook;
import com.kong.king.spring.guest04.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class GuestbookRepositoryTests {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	//테스트 데이터 생성
//	@Test
//	public void insertDummies() {
//		IntStream.rangeClosed(1, 300).forEach(i -> {
//			Guestbook guestbook = Guestbook.builder()
//					.title("Title...." + i)
//					.content("Content....." +i%5)
//					.writer("user" + (i%10))
//					.build();
//			
//			System.out.println(guestbookRepository.save(guestbook));
//		});
//	}
	
	//테스트 데이터 업데이트
//	@Test
//	public void updateTest() {
//		Optional<Guestbook> result = guestbookRepository.findById(302L);
//		
//		if(result.isPresent()) {
//		Guestbook guestbook = result.get();	
//		
//		guestbook.changeTitle("Changed Title......");
//		guestbook.changeContent("Changed Content.....");
//		guestbookRepository.save(guestbook);
//		}
//	}
	
	//Querydsl 테스트 키워드조회 
//	@Test
//	public void testQuery1() {
//		Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());
//		QGuestbook qGuestbook = QGuestbook.guestbook;
//		
//		String keyword = "1";
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		BooleanExpression expression = qGuestbook.title.contains(keyword);
//		
//		builder.and(expression);
//		
//		Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);
//		
//		result.stream().forEach(guestbook -> {
//			System.out.println(guestbook);
//		});
//	}
	
	//Queryds2 테스트 다중항목 검색 
	@Test
	public void testQuery2() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
		
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		String keyword = "3";
		String keyword2 = "1";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		BooleanExpression exTitle = qGuestbook.title.contains(keyword);
		BooleanExpression exContent = qGuestbook.content.contains(keyword2);
		BooleanExpression exAll = exTitle.and(exContent);
		
		builder.and(exAll);
		builder.and(qGuestbook.gno.gt(250L));
		
		Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);
		
		result.stream().forEach(guestbook -> {
			System.out.println(guestbook);
		});
	}
	
	
	
}
