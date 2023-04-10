package com.boardgame.mall.controller;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boardgame.mall.service.user.DeleteUserForIdAction;
import com.boardgame.mall.service.user.DeleteUserListDisplayAction;
import com.boardgame.mall.service.user.GetMyInfo;
import com.boardgame.mall.service.user.GetUserListAction;
import com.boardgame.mall.service.user.InsertUserAction;
import com.boardgame.mall.service.user.LoginUser;
import com.boardgame.mall.service.user.LogoutUser;
import com.boardgame.mall.service.user.UpdateUserDisplayAction;
import com.boardgame.mall.service.user.UpdateUserForIdAction;
import com.boardgame.mall.service.user.UpdateUserListDisplayAction;


@Controller
@RequestMapping("/user")
public class UserController {

		
	 @PostMapping("/join")
	 public String userjoin() {
		 
		 return "user/user_c";
	 }
	 
	 @PostMapping("/login")
		public String loginUser(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
			System.out.println("로그인시도");
			Action action = new LoginUser();
			ActionForward forward = null;
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			

			String s = forward.getPath();
			System.out.println(s);
		
			return s;
		}
		
		@PostMapping("/logout")
		public String logoutUser(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
			System.out.println("로그아웃시도");
			Action action = new LogoutUser();
			ActionForward forward = null;
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			String s = forward.getPath();
			return s;
			
		}
		
		@PostMapping("/UpdateUserDisplay")
		public String UpdateUserDisplay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Action action = new UpdateUserDisplayAction();
			ActionForward forward = null;
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			String s = forward.getPath();
			System.out.println(s);
		
			return s;
			
		} 
		
		//관리자가 일반유저 수정조회 리스트로 이동
		@GetMapping("/UpdateUserListDisplay/{cpn}")
		public String UpdateUserListDisplay(HttpServletRequest request, HttpServletResponse response,@PathVariable int cpn) throws ServletException, IOException {
			
			
			Action action = new UpdateUserListDisplayAction(cpn);
			ActionForward forward = null;
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			String s= null;
			try {
			s = forward.getPath();
			System.out.println(s);
			}catch(Exception e) {
				e.printStackTrace();
				s = "etc/userError";
			}
		
			return s;
		} 
		
	 //회원 C 
	@PostMapping("/CreateUser")
	public String CreateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = new InsertUserAction();
		ActionForward forward = null;
		try {
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		String s = forward.getPath();
		System.out.println(s);
	
		return s;
		
	}
	
//	본인 정보 조회
	@GetMapping("/GetMyInfo")
	public String GetUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = new GetMyInfo();
		ActionForward forward = null;
		try {
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String s = forward.getPath();
		System.out.println(s);
		System.out.println("유저 정보조회");
		return s;
		
	}
	//관리자 회원정보 전체조회
	@GetMapping("/GetUserList")
	public String GetUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = new GetUserListAction();
		ActionForward forward = null;
		try {
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String s = forward.getPath();
		System.out.println(s);
		System.out.println("유저 정보조회");
		return s;
		
	}
	
	//본인 정보 수정
	@PostMapping("/UpdateUserForId")	
	public String PutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = new UpdateUserForIdAction();
		ActionForward forward = null;
		try {
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String s = forward.getPath();
		System.out.println(s);
		System.out.println("유저 업데이트");
		return s;
		
	}
	
	//회원 탈퇴
	@PostMapping("/DeleteUserForId")
	public String DeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = new DeleteUserForIdAction();
		ActionForward forward = null;
		try {
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String s = forward.getPath();
		System.out.println(s);
		System.out.println("유저 삭제");
		if(s.equals("logout")) {
			s = logoutUser(request,response);
		}else if(s.equals("index")) {
			s= "index";
		}
		return s;
		
	}
	//관리자 회원삭제 목록조회
	@GetMapping("/DeleteUserListDisplay/{cpn}")
	public String DeleteUserListDisplay(HttpServletRequest request, HttpServletResponse response,@PathVariable int cpn) throws ServletException, IOException {
		Action action = new DeleteUserListDisplayAction(cpn);
		ActionForward forward = null;
		try {
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String s= null;
		try {
		s = forward.getPath();
		System.out.println(s);
		}catch(Exception e) {
			e.printStackTrace();
			s = "etc/userError";
		}
	
		return s;
	} 
	
	
	

	
	


}