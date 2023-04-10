package com.boardgame.mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boardgame.mall.dto.UserPageInfoVO;

@Controller
public class MainController {

	@GetMapping("/")
	public String index(HttpSession session) {
		if( session.isNew()){ //새로운 객체가 만들어졌다면 세션을 저장한다. 
			session.setAttribute("loginState", "logout"); //로그인 상태저장
			session.setAttribute("viewState", null); //지금 보는 화면 저장
			session.setAttribute("user_num", null); //유저 번호 저장
			session.setAttribute("user_name", null); //유저 이름 저장
			session.setAttribute("user_id", null); //유저 아이디 저장
			session.setAttribute("user_passwd", null); //유저 비번 저장
			session.setAttribute("user_class", null); //유저 등급 저장
			

		}
		session.setAttribute("userPageInfoVO",new UserPageInfoVO());
		return "index";
	}
	@PostMapping("/")
	public String index2(HttpSession session) {
		if( session.isNew()){ //새로운 객체가 만들어졌다면 세션을 저장한다. 
			session.setAttribute("loginState", "logout"); //로그인 상태저장
			session.setAttribute("viewState", null); //지금 보는 화면 저장
			session.setAttribute("user_num", null); //유저 번호 저장
			session.setAttribute("user_name", null); //유저 이름 저장
			session.setAttribute("user_id", null); //유저 아이디 저장
			session.setAttribute("user_passwd", null); //유저 비번 저장
			session.setAttribute("user_class", null); //유저 등급 저장	
			
		}
		session.setAttribute("userPageInfoVO",new UserPageInfoVO());
		return "index";
	}
}
