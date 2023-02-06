package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/pb")
	@ResponseBody
	public String index() {
		return "pb에 오신것을 환영합니다.";
	}

	@RequestMapping("/accessDenied")
	public String accessDenied(){
		return "AccessDenied";
	}

	@RequestMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
}
