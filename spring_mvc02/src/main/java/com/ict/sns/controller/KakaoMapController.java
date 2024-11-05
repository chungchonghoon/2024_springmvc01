package com.ict.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KakaoMapController {
		
		@GetMapping("/kakao_map01")
		public ModelAndView kakaMap01() {
				return new ModelAndView("sns/kakao_map01");
		}
		@GetMapping("/kakao_map02")
		public ModelAndView kakaMap02() {
			return new ModelAndView("sns/kakao_map02");
		}
		@GetMapping("/kakao_map03")
		public ModelAndView kakaMap03() {
			return new ModelAndView("sns/kakao_map03");
		}
		@GetMapping("/kakao_map04")
		public ModelAndView kakaMap04() {
			return new ModelAndView("sns/kakao_map04");
		}
}
