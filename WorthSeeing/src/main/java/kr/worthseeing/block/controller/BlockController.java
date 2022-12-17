package kr.worthseeing.block.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlockController {
	
	@GetMapping("/main")
	public String mainPage() {
		System.out.println("testetset");
		return "/main";
	}
	
}
