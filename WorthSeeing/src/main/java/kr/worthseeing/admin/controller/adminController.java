package kr.worthseeing.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.worthseeing.admin.service.AdminService;
import kr.worthseeing.log.service.LogService;
import kr.worthseeing.users.entity.Users;

@Controller
@RequestMapping("/admin")
public class adminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping("/adminUserList")
	public String selectUsersList(Model model ,  @PageableDefault Pageable pageable) {
		
		Page<Users> userList = adminService.selectUsers(pageable);
		int nowPage=  userList.getPageable().getPageNumber()+1;
		
		model.addAttribute("userList", userList);
		model.addAttribute("nowPage", nowPage);
		
		return "/admin/adminUserList";
	
		
	}
	
	// 변경하기 버튼 클릭 시
	@PostMapping("/blackList")
	public String blackList(@RequestParam List<String> userId, @RequestParam List<String> blackYn) {
		adminService.blackList(userId, blackYn);
		
		
		return "redirect:/admin/adminUserList";
		
		
	}
	
	@GetMapping("/salePriceChart")
	public String salePriceChart(Model model,String startYear) {
		
		if(startYear==null) {
			startYear = "2022";
		}
		
		model.addAttribute("startYear",startYear);
		model.addAttribute("salePriceList", logService.saleChart(startYear));
		
		return "/admin/salePriceChart";
	}
	
	@GetMapping("/blockPriceChart")
	public String salePriceChart(Model model,String startYear,String block_seq) {
		
		if(startYear==null) {
			startYear = "2022";
			block_seq = "1";
		}
		
		model.addAttribute("startYear",startYear);
		model.addAttribute("block_seq",block_seq);		
		model.addAttribute("blockPriceList", logService.blockChart(block_seq,startYear));
		return "/admin/blockPriceChart";
	}
	
}
