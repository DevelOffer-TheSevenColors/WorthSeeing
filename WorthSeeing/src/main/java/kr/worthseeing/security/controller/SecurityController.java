package kr.worthseeing.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.worthseeing.users.entity.Role;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.service.UsersService;
 
@Controller
@RequestMapping("/system")
public class SecurityController {
	@Autowired
	private UsersService userService;

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception, Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "/system/login";
	} 

	@GetMapping("/join")
	public String memberJoin(Users user) {
		return "/system/join";
	}
	
	@GetMapping("/find")
	public String memberFind() {
		return "/system/find";
	}
	
	@PostMapping("/findProc")
	public String memberFindProc(Users user,Model model) {
		model.addAttribute("result",userService.findUser(user));
		return "/system/find";
	}
	 
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "/system/accessDenied";
	}

	@PostMapping("/joinProc")
	public String memberJoinProc(Users user) {

		for (Users user_db : userService.listUsers()) {
			if (user_db.getUserId().equals(user.getUserId())) {
				return "/system/joinFail";
			}
		}

		user.setRole(Role.ROLE_USER);

		userService.insertUsers(user);

		return "redirect:/system/login";
	}
	
	

}
