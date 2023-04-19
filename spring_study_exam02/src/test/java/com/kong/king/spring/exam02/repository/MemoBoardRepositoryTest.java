package com.kong.king.spring.exam02.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kong.king.spring.exam02.entity.MemoBoard;
import com.kong.king.spring.exam02.repository.MemoBoardRepository;

@SpringBootTest
public class MemoBoardRepositoryTest {
	@Autowired
	MemoBoardRepository memoBoardRepository;

	/*
	 * @Test void contextLoads() { }
	 */
	
	  //테이블 생성 테스트v 10
//	  @Test 
//	  public void testClass() { 
//		  System.out.println("객체생성 확인 ----" +
//		  memoBoardRepository.getClass().getName()); 
//	  }
	 

//	테이블 데이터 생성 12번 테스트
	
//	 @Test 
//	  public void testInsertDummies() {
//		  IntStream.rangeClosed(1,100).forEach(i -> { 
//			  MemoBoard memo = MemoBoard.builder()
//					  .content("메모...."+i) 
//					  .build();
//		  memoBoardRepository.save(memo); 
//		  }); 
//	}

	// 테이블 데이터 생성 13번테스트
//	@Test
//	public void testInsertDummies2() {
//		MemoBoard memo;
//		for(int i = 200; i<=250; i++) {
//			memo = new MemoBoard();
//			memo.setContent("메모x테스트..."+i);
//			memoBoardRepository.save(memo);
//		}
//	}

	//테이블 조회 테스트 14번
//	@Test 
//	public void testSelectById() {
//		Long mid = 3L;
//		Optional<MemoBoard> result = memoBoardRepository.findById(mid);
//		System.out.println("------------");
//		if(result.isPresent()) {
//			MemoBoard memo = result.get();
//			System.out.println(memo);
//		}
//		
//	}
	
	//테이블 조회 15번
//	@Test
//	public void testSelect2ById() {
//		Long mid = 30L;
//		MemoBoard memo = memoBoardRepository.getOne(mid);
//		System.out.println("------------");
//		System.out.println(memo);
//
//	}
	
	//테이블 조회 16번
//	@Test
//	public void testSelectAll() {
//		List<MemoBoard> list = new ArrayList<MemoBoard>();
//		list = memoBoardRepository.findAll();
//		System.out.println(list);
//	}
	
	//테이블 업데이트 17번
//	@Test
//	public void testUpdate() {
//		MemoBoard memo = MemoBoard.builder()
//				.mid(100L)
//				.content("100번 데이터 수정...")
//				.build();
//		System.out.println(memoBoardRepository.save(memo));
//	}
//	
	//테이블 삭제 테스트 18번
//	@Test
//	public void testDelete() {
//		Long mid = 100L;
//		memoBoardRepository.deleteById(mid);
//	}
	
	  //페이징 처리 조회 19번 
//	  @Test 
//	  public void testPageDefault() { 
//		  Pageable pageable =PageRequest.of(0,10); 
//		  Page<MemoBoard> result = memoBoardRepository.findAll(pageable);
//		  System.out.println(result); System.out.println("-----------------");
//		  System.out.println("Total Pages : " + result.getTotalPages());
//		  System.out.println("Total Count : " + result.getTotalElements());
//		  System.out.println("Page Number : " + result.getSize());
//		  System.out.println("Page Size : " + result.getSize());
//		  System.out.println("has next page?" + result.hasNext());
//		  System.out.println("first page? : " + result.isFirst());
//		  System.out.println("-----------------"); for(MemoBoard memo :
//		  result.getContent()) { System.out.println(memo); } 
//	  }
	
	//테이블 소트테스트 20번
//	@Test
//	public void testSort() {
//		Sort sort1 = Sort.by("mid").descending();
//		
//		Pageable pageable = PageRequest.of(0,10,sort1);
//		Page<MemoBoard> result = memoBoardRepository.findAll(pageable);
//		
//		result.get().forEach(memo -> {
//			System.out.println(memo);
//		});
//	}
	
	//테이블 소트테스트 21번
//	@Test
//	public void testSort2() {
//		Sort sort1 = Sort.by("content").ascending();
//		Sort sort2 = Sort.by("mid").descending();
//		Sort sortAll = sort1.and(sort2);
//		
//		Pageable pageable = PageRequest.of(0,10,sortAll);
//		Page<MemoBoard> result = memoBoardRepository.findAll(pageable);
//		
//		result.get().forEach(memo -> {
//			System.out.println(memo);
//		});
//	}

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
	
	// 테이블 쿼리메서트 테스트 22번 70~80사이 mid값 거꾸로 정렬해서 나타내기.
//	@Test
//	public void testQueryMethods() {
//		List<MemoBoard> list =  memoBoardRepository.findByMidBetweenOrderByMidDesc(70L, 80L);
//		for(MemoBoard memo : list) {
//			System.out.println(memo);
//		}
//	}
	
	//테이블 쿼리메서드 테스트 23번
//	@Test
//	public void testQUertMethodWithPageable() {
//		Pageable pageable = PageRequest.of(0,10,Sort.by("mid").descending());
//		Page<MemoBoard> result = memoBoardRepository.findByMidBetween(10L,50L,pageable);
//		result.get().forEach(memo -> System.out.println(memo));
//	}


//	//테이블 Query 어노테이션으로 한번실행하기  24번
//	@Test
//	public void testAtQueryGetAllData() {
//		List<MemoBoard> list = memoBoardRepository.getMemoBoardListAllDesc();
//		for(MemoBoard memo : list) {
//			System.out.println(memo);
//		}
//		
//	}

//	//Qeury 파라미터 바인딩 1 25번
//	@Test
//	public void testAtQueryUpdateById() {
//		int result = memoBoardRepository.updateMemoBoardContentWithMid(2L, "@Query를 활용한 수정 실습");
//		System.out.println("수정 결과 : " + result);
//	}

	
	// Query의 파라미터 바인딩2 26번
//	@Test
//	public void testAtQueryUpdateByObj() {
//		MemoBoard memo = new MemoBoard();
//		
//		memo.setMid(2L);
//		memo.setContent("@Query를 이용한 수정 - Object전달 방식");
//		
//		int result = memoBoardRepository.updateMemoBoardContentWithObj(memo);
//		System.out.println("수정 결과 : " + result);
//	}
	
	//Query 어노테이션 연습 27번 
//  @Test
//  public void testAtQueryandPaging() {
//     Pageable pageable = PageRequest.of(0,10, Sort.by("mid").descending());
//     Page<MemoBoard> result = memoBoardRepository
//           .getListWithQueryandPaging(1L, pageable);
//     
//     System.out.println("---------");
//     System.out.println("result="+result);
//     result.get().forEach(memo -> System.out.println(memo));
//     
//  }
  
	//Query 어노테이션 연습 28번
//  @Test
//  public void test() {
//     Pageable pageable = PageRequest.of(0,10, Sort.by("mid").descending());
//     Page<Object[]> result = memoBoardRepository
//           .getListWitrhQueryObject(1L, pageable);
//     System.out.println("----------------");
//     System.out.println("result= "+result);
//     result.get().forEach(memo -> System.out.println(Arrays.toString(memo)));
//  }
  
	//Query 어노테이션 연습 29번 
//  @Test
//  public void testNativeQuery() {
//     List<MemoBoard> list = memoBoardRepository.getNativeResult();
//     
//     for(MemoBoard m : list) {
//        System.out.println(m);
//     }
//  }

}
