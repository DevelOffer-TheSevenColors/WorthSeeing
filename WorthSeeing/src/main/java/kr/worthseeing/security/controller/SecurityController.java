package kr.worthseeing.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.worthseeing.users.entity.Role;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.service.UsersService;

@Controller
@RequestMapping("/system")
public class SecurityController {
	@Autowired
	private UsersService userService;
	
	@GetMapping("/login")
	public String login() {
		return "/system/login";
	}	

	@GetMapping("/join")
	public String memberJoin(Users user) {
		return "/system/join";
	}
	
	@PostMapping("/joinProc")
	public String memberJoinProc(Users user) {	
		
		for(Users user_db:userService.listUsers())	{
			if(user_db.getUserId().equals(user.getUserId())) {
				return "/system/joinFail";
			}
		}
		
		user.setRole(Role.ROLE_MEMBER);

		userService.insertUsers(user);	

		return "redirect:/system/login"; 
	}
	
}