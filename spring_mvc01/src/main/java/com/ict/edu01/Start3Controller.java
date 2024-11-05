package com.ict.edu01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 어노테이션으로 @Controller를 선언하면 Spring에서 자동으로 해당 클래스를 Controller로 인식한다.
@Controller
public class Start3Controller {
	// URL 매핑이란
	// @RequestMapping(value= "URL", method=RequestMethod.GET)
	// @RequestMapping(value= "URL", method=RequestMethod.POST)
	
	// @RequestMapping("URL") // get 방식인지 post 방식인지 모를 땐 해당 메서드 사용
	// @GetMapping("URL") // get 방식 해당 메서드 사용
	// @PostMapping("URL") // post 방식 해당 메서드 사용
	
	// 해당 메서드는 URL 매핑이 와야 실행된다.
	@GetMapping("/start3") // a 링크는 get 방식이다.
	// @RequestMapping("/start3")
	// @PostMapping("/start3") // a 링크는 get 방식이므로 오류 발생
	
	// exec(파라미터) : 파라미터는 필요할 때만 사용하면 된다.
	public ModelAndView exec(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("day01/result3");
		mv.addObject("city", "서울");
		return mv;
	}
}
