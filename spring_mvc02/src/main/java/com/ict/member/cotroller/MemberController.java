package com.ict.member.cotroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.member.service.MemberService;
import com.ict.member.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/member_login")
	public ModelAndView getMemberLogin(MemberVO mvo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			// 1. 아이디가지고 DB 갔다오기
			MemberVO memberVO = memberService.getMemberLogin(mvo);
			
			// 2. 아이디가 맞는지 확인
			if(memberVO != null) {
			// 아이디가 없어서 로그인 실패

				
			// 3. 사용자가 입력한 암호를 추출
			String userM_pw = mvo.getM_pw();
			
			// 4. 두 암호를 비교해서 같으면 성공, 다르면 실패 
			// passwordEncoder.matches(암호화 X, 암호화 O) => 일치하면 True, 불일치하면 False
			if(passwordEncoder.matches(userM_pw, memberVO.getM_pw())) {
				// 성공
				
				session.setAttribute("loginchk", "ok");
				// 관리자와 유저 구분
				if(memberVO.getM_name().equals("admin")) {
					session.setAttribute("admin", "ok");
				}
				
				mv.setViewName("redirect:/shop");
				session.setAttribute("mvo", memberVO);
				return mv;
				
			}else {
				// 실패
				// request에 값 저장해서 loginForm에 로그인 실패 alert 가능
				mv.addObject("loginchk", "0");
				return new ModelAndView("sns/login_error");
				
			}
			
			}else {
				mv.addObject("loginchk", "0");
				return new ModelAndView("sns/login_error");
			}
		} catch (Exception e) {
			System.out.println(e);
			mv.addObject("loginchk", "0");
			return new ModelAndView("sns/login_error");
		}
	}
	
	@GetMapping("/member_logout")
	public ModelAndView getMemberLogOut(HttpSession session) {
		// 세션 초기화 (전체 세션 초기화)
		/* session.invalidate(); */
		
		// 해당하는 세션만 초기화
		session.removeAttribute("loginchk");
		session.removeAttribute("adminchk");
		session.removeAttribute("mvo");
		
		return new ModelAndView("redirect:/shop");
	}
}
