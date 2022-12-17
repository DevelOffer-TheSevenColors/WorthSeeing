package kr.worthseeing.notify.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.notify.service.NotifyService;
import kr.worthseeing.status.entity.Status;

@Controller
public class NotifyController {
	
	@Autowired
	private NotifyService notifyService;
	
	@RequestMapping("/notify")
	public String getNotifyList(Pageable pageable, Status status, Model model){
		model.addAttribute("notifyList", notifyService.listNotify());
		
//		페이징
//		Page<Notify> getNotifyList = notifyService.getNotify(pageable, status);
//		
//		model.addAttribute("getNotifyList", getNotifyList);
		model.addAttribute("pageable", pageable);
		
		return "/notify";
	}
	
	@GetMapping("/insertNotify")
	public void insertNotify() {
		
	}
	
//	@PostMapping("/insertNotifyProc")
//	public String insertNotifyProc() {
//		
//	}
	
}
