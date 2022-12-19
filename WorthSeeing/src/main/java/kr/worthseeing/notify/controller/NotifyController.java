package kr.worthseeing.notify.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.worthseeing.notify.dto.SearchDTO;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.notify.service.NotifyService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.status.entity.Status;

@Controller
public class NotifyController {
	
	@Autowired
	private NotifyService notifyService;
	
	@RequestMapping("/notify")
	public String getList(Pageable pageable, Model model, SearchDTO search){
		model.addAttribute("notifyList", notifyService.getListNotify(pageable, search));
		return "/notify/notify";
	}
	
	@GetMapping("/notify/insertNotify")
	public String insertNotify() {
		return "/notify/insertNotify";
	}
	
	@PostMapping("/notify/insertNotifyProc")
	public String insertNotifyProc(Notify notify, @AuthenticationPrincipal SecurityUser principal) {
		notifyService.insertNotify(notify);
		return "redirect:/notify";
	}  
	
	@GetMapping("/notify/getNotify")
	public String getNotify(Notify notify, Model model, @AuthenticationPrincipal SecurityUser principal) {
		model.addAttribute("notify", notifyService.getNotify(notify));
		model.addAttribute("principal", principal);
		return "/notify/getNotify";
	}
	
//	@PostMapping("/notify/updateNotifyProc")
//	public String updateNotifyProc(Notify notify) {
//		notifyService.updateNotify(notify);
//		return "/notify/updateNotifyProc";
//	}
	
	@GetMapping("/notify/deleteNotifyProc")
	public String deleteNotifyProc(Notify notify) {
		notifyService.deleteNotify(notify);
		return "/notify/deleteNotifyProc";
	}
	    
}
