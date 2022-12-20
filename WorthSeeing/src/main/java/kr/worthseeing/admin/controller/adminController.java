package kr.worthseeing.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.worthseeing.admin.service.AdminService;
import kr.worthseeing.users.entity.Users;

@Controller
@RequestMapping("/admin")
public class adminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping("/adminUserList")
	public String selectUsersList(Model model ,  @PageableDefault Pageable pageable) {
		
		Page<Users> userList = adminService.selectUsers(pageable);
		model.addAttribute("userList", userList);
		
		return "/admin/adminUserList";
	
		
	}
	
	// 변경하기 버튼 클릭 시
	@PostMapping("/blackList")
	public String blackList(@RequestParam List<String> userId, @RequestParam List<String> blackYn) {
		adminService.blackList(userId, blackYn);
		
		
		return "redirect:/admin/adminUserList";
		
		
	}
}
