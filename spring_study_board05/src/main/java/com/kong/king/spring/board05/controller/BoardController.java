package com.kong.king.spring.board05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kong.king.spring.board05.dto.BoardDTO;
import com.kong.king.spring.board05.dto.PageRequestDTO;
import com.kong.king.spring.board05.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list..............."+pageRequestDTO);
		model.addAttribute("result", boardService.getList(pageRequestDTO));
		
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("register get........");
		
	}
	
	@PostMapping("/register")
	public String registerPost(BoardDTO dto, RedirectAttributes redirecAttributes) {
		log.info("dto....."+dto);
		Long bno = boardService.register(dto);
		log.info("BNO : " + bno);
		redirecAttributes.addFlashAttribute("msg" , bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,Long bno, Model model) {
		log.info("bno : " + bno);
		BoardDTO boardDTO = boardService.get(bno);
		
		log.info(boardDTO);
		model.addAttribute("dto", boardDTO);
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes redirectAttributes) {
		log.info("bon : "+bno);
		boardService.removeWithReplies(bno);
		
		redirectAttributes.addFlashAttribute("msg", bno);
		
		return "redirect:/board/read";
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO , RedirectAttributes redirectAttributes) {
		log.info("post modify.............");
		log.info("dto : ", dto);
		boardService.modify(dto);
		
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("type", requestDTO.getType());
		redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
		
		return "redirect:/board/read";
	}
	
}