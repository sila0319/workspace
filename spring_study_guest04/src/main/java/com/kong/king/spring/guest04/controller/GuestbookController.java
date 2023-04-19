package com.kong.king.spring.guest04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kong.king.spring.guest04.dto.GuestbookDTO;
import com.kong.king.spring.guest04.dto.PageRequestDTO;
import com.kong.king.spring.guest04.service.GuestbookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

	private final GuestbookService service;


    @GetMapping("/") public String index() {
    	log.info("list rot.................");
        return "redirect:/guestbook/list";
    } // index() 끝

    @GetMapping("/list") 
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list()가 동작 중 입니다!" + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));

    } // list() 끝

    @GetMapping("/register") 
    public void register() {

        log.info("regist get....");

    } // register() 끝

    @PostMapping("/register") 
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {

        log.info("dto......." + dto );


        // 새로 추가된 Entity Number
        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";

    } // registerPost() 끝

    @GetMapping({"/read", "/modify"}) 
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
      

        log.info("gno " + gno);
        
        GuestbookDTO dto = service.read(gno);
        model.addAttribute("dto", dto);
        

    } // read() 끝

    @PostMapping("/modify") 
    public String modify(GuestbookDTO dto, 
    		@ModelAttribute("requestDTO") PageRequestDTO requestDTO, 
    		RedirectAttributes redirectAttributes) {

        log.info("post modify..............");
        log.info("dto" + dto);

        service.modify(dto);
        
        redirectAttributes.addFlashAttribute("page", requestDTO.getPage());
        redirectAttributes.addFlashAttribute("type", requestDTO.getType());
        redirectAttributes.addFlashAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addFlashAttribute("gno", dto.getGno());
      

        return "redirect:/guestbook/list";

    } // modify() 끝
    
    @PostMapping("/remove") 
    public String remove(long gno, RedirectAttributes redirectAttributes) {

        log.info("글번호 : " + gno);
        
        service.remove(gno);
        
        redirectAttributes.addFlashAttribute("msg", gno);
        
        return "redirect:/guestbook/list";

    } // remove() 끝

} // class 끝