package com.kong.king.spring.board05.repository;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;
	
//	@Test
//	public void insertMembers() {
//		IntStream.rangeClosed(1,100).forEach(i -> {
//			Member member = Member.builder().email("user"+i+"King.kong.com").build();
//			
//			Board board = Board.builder()
//					.title("제목타이틀..." +(i%5))
//					.content("내용..." + (i&8))
//					.writer(member)
//					.build();
//			boardRepository.save(board);
//		});
//	}
	
//	@Test
//	public void testReadBoard() {
//		Optional<Board> result = boardRepository.findById(100L);
//		Board board = result.get();
//		System.out.println(board);
//		System.out.println(board.getWriter());
//	}
	
//	@Test
//	@Transactional
//	public void testReadBoard() {
//		Optional<Board> result = boardRepository.findById(100L);
//		Board board = result.get();
//		System.out.println(board);
//		System.out.println(board.getWriter());
//	}
	
//	@Test
//	public void testReadWithWriter() {
//		Object result = boardRepository.getBoardWithWriter(100L);
//		Object[] arr = (Object[]) result;
//		
//		System.out.println("------------------------------");
//		System.out.println(Arrays.toString(arr));
//		
//	}
//	@Test
//	public void testGetBoardWithReply() {
//		List<Object[]> result = boardRepository.getBoardWithReply(100L);
//		
//		for(Object[] arr : result) {
//			System.out.println(Arrays.toString(arr));
//		}
//	}
	
//	@Test
//	public void testWithReplyCount() {
//		Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
//		Page<Object[]> result =boardRepository.getBoardWithReplyCount(pageable);
//		
//		result.get().forEach(row -> {
//			Object[] arr = (Object[])row;
//			System.out.println(Arrays.toString(arr));
//		});
//		
//	}
	@Test
	public void testGetBoardByBno() {
		Object result = boardRepository.getBoardByBno(100L);
		Object[] arr = (Object[]) result;
		System.out.println(Arrays.toString(arr));
	}
}
