package com.kong.king.spring;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kong.king.spring.exam02.entity.MemoBoard;
import com.kong.king.spring.exam02.repository.MemoBoardRepository;

@SpringBootTest
class SpringStudyExam02ApplicationTests {
	@Autowired
	MemoBoardRepository memoBoardRepository;
	
	/*
	 * @Test void contextLoads() { }
	 */
	/* //테이블 생성 테스트v 10
	 * @Test public void testClass() { System.out.println("객체생성 확인 ----" +
	 * memoBoardRepository.getClass().getName()); }
	 */
	 
//	테이블 데이터 생성 11번 테스트
//	 @Test 
//	  public void testInsertDummies() {
//		 MemoBoard memo = new MemoBoard();
//		 memo.setMid(1L);
//		  IntStream.rangeClosed(1,100).forEach(i -> { 
//			  MemoBoard memo =MemoBoard.builder()
//					  .content("메모...."+i) 
//					  .build();
//		  memoBoardRepository.save(memo); }); 
//		  
//		  }
	
	
	
	/*
	 * //페이징 처리 조회
	 * 
	 * @Test public void testPageDefault() { Pageable pageable =
	 * PageRequest.of(0,10); Page<MemoBoard> result =
	 * memoBoardRepository.findAll(pageable);
	 * 
	 * System.out.println(result); System.out.println("-----------------");
	 * System.out.println("Total Pages : " + result.getTotalPages());
	 * System.out.println("Total Count : " + result.getTotalElements());
	 * System.out.println("Page Number : " + result.getSize());
	 * System.out.println("Page Size : " + result.getSize());
	 * System.out.println("has next page?" + result.hasNext());
	 * System.out.println("first page? : " + result.isFirst());
	 * System.out.println("-----------------"); for(MemoBoard memo :
	 * result.getContent()) { System.out.println(memo); } }
	 */
	
	/*
	 * //Sort 거꾸로하기
	 * 
	 * @Test public void testOsrt() { Sort sort1 = Sort.by("mid").descending();
	 * Pageable pageable = PageRequest.of(0,10,sort1); Page<MemoBoard> result =
	 * memoBoardRepository.findAll(pageable);
	 * 
	 * result.get().forEach(memo -> { System.out.println(memo); });
	 * 
	 * }
	 */
	
	//70~80사이 mid값 거꾸로 정렬해서 나타내기.
//	@Test
//	public void testQueryMethods() {
//		List<MemoBoard> list =  memoBoardRepository.findByMidBetweenOrderByMidDesc(70L, 80L);
//		for(MemoBoard memo : list) {
//			System.out.println(memo);
//		}
//	}
	
//	//Query 어노테이션으로 한번실행하기 
//	@Test
//	public void testAtQUertGetAllData() {
//		List<MemoBoard> list = memoBoardRepository.getMemoBoardListAllDesc();
//		for(MemoBoard memo : list) {
//			System.out.println(memo);
//		}
//		
//	}
	
//	//Qeury 파라미터 바인딩 1
//	@Test
//	public void testAtQueryUpdateById() {
//		int result = memoBoardRepository.updateMemoBoardContentWithMid(2L, "@Query를 활용한 수정 실습");
//		System.out.println("수정 결과 : " + result);
//	}
	
	//Query의 파라미터 바인딩2
	
//	@Test
//	public void testAtQueryUpdateByObj() {
//		MemoBoard memo = new MemoBoard();
//		
//		memo.setMid(2L);
//		memo.setContent("@Query를 이용한 수정 - Object전달 방식");
//		
//		int result = memoBoardRepository.updateMemoBoardContentWithobj(memo);
//		System.out.println("수정 결과 : " + result);
//	}
	
	
}
