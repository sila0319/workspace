package com.kong.king.spring.board05.repository;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kong.king.spring.board05.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTest {

	@Autowired
	private ReplyRepository replyRepository;
	
//	@Test
//	public void insertMembers() {
//		IntStream.rangeClosed(1,300).forEach(i -> {
//			long bno  = (long)(Math.random()*100) +1;
//			Board board = Board.builder().bno(bno).build();
//			
//			Reply reply = Reply.builder()
//					.text("답글 Reply..." +i)
//					.board(board)
//					.replyer("guest")
//					.build();
//			replyRepository.save(reply);
//		});
//	}
	
	@Test
	public void testReadReply() {
		Optional<Reply> result = replyRepository.findById(3L);
		Reply reply = result.get();
		
		System.out.println(reply);
		System.out.println(reply.getBoard());
		
	}
}
