package com.ict.edu01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start4Controller {
	
	@PostMapping("/start4")
	// @RequestMapping("/start4")
	// @GetMapping("/start4") // form이 포스트 방식으로 전송해서 오류 발생
	public ModelAndView test01() {
		ModelAndView mv = new ModelAndView("day01/result4");
			String[] carName = {"제네시스", "K9", "그랜져", "K7"};
		
			mv.addObject("carName", carName);
		return mv;
	}
}
