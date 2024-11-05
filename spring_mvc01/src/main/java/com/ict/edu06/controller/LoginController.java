package com.ict.edu06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu06.service.LoginService;
import com.ict.edu06.vo.LoginVO;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; // 암호화 시켜주는 클래스 오토와이어, passwordEncoder는 내가 지정한 ID와 같아야 함.
	
	//로그인 폼으로 이동
	@GetMapping("/loginForm")
	public ModelAndView getLogInForm() {
		return new ModelAndView("day06/login_loginForm");
	}
	//로그인 체크
	
	//회원가입 폼으로 이동
	@GetMapping("/login_join_form")
	public ModelAndView getLogInJoinForm() {
		return new ModelAndView("day06/login_joinForm");
	}
	// 회원가입하기
	@PostMapping("/login_join_ok")
	public ModelAndView getLogInJoinOk(LoginVO lvo) {
		ModelAndView mv = new ModelAndView("day06/login_loginForm");
		try {
			
			// 비번을 암호화 하자
			String m_pw = passwordEncoder.encode(lvo.getM_pw()); // 암호화 시킴
			lvo.setM_pw(m_pw);
			
			int result = loginService.LoginJoin(lvo);
			if(result>0) {
				mv.addObject("result", "1");
			}else {
				mv.addObject("result", "0");
			}
		} catch (Exception e) {
			System.out.println(e);
			mv.addObject("result", "0");
		}
		return mv;
	}
	
	// 로그인 하기
	@PostMapping("/login_login")
	public ModelAndView getLoginOK(LoginVO lvo) {
		ModelAndView mv = new ModelAndView();
		try {
			// 1. 아이디가지고 DB 갔다오기
			LoginVO loginVO = loginService.LoginChk(lvo);
			
			// 2. 아이디가 맞는지 확인
			if(loginVO != null) {
				
			// 3. 사용자가 입력한 암호를 추출
			String userM_pw = lvo.getM_pw();
			
			// 4. 두 암호를 비교해서 같으면 성공, 다르면 실패 
			// passwordEncoder.matches(암호화 X, 암호화 O) => 일치하면 True, 불일치하면 False
			if(passwordEncoder.matches(userM_pw, loginVO.getM_pw())) {
				mv.addObject("loginchk", "1");
				mv.setViewName("index");
				return mv;
			}else {
				mv.addObject("loginchk", "0");
				mv.setViewName("day06/login_loginForm");
				return mv;
				
			}
			
			}else {
				mv.addObject("loginchk", "0");
				mv.setViewName("day06/login_loginForm");
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
			mv.addObject("loginchk", "0");
			mv.setViewName("day06/login_loginForm");
			return mv;
		}
	}
}
