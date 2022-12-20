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
import org.springframework.web.bind.annotation.RequestParam;

import kr.worthseeing.notify.dto.SearchDTO;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.notify.service.NotifyService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.status.entity.Status;

@Controller
public class NotifyController {

	@Autowired
	private NotifyService notifyService;

	// 글 목록
	@RequestMapping("/notify")
	public String getList(@PageableDefault Pageable pageable, Model model, String status) {
		System.out.println("controller status===>" + status);
		System.out.println(notifyService.getListNotify(pageable, "2").getTotalPages());
		System.out.println(notifyService.getListNotify(pageable, "2").getTotalElements());
		model.addAttribute("notifyList", notifyService.getListNotify(pageable, status));
		return "/notify/notify";
	}

	// 관리자가 공지글 등록
	@GetMapping("/notify/insertNotify")
	public String insertNotify() {
		return "/notify/insertNotify";
	}

	// 사용자가 문의글 등록
	@GetMapping("/notify/insertContact")
	public String insertContact() {
		return "/notify/insertContact";
	}

	@PostMapping("/notify/insertNotifyProc")
	public String insertNotifyProc(Notify notify) {
		notifyService.insertNotify(notify);
		return "redirect:/notify";
	}
	
	@PostMapping("/notify/insertContactProc")
	public String insertContactProc(Notify notify) {
		notifyService.insertContact(notify);
		return "redirect:/notify";
	}

	// 상세글
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
	// 글 삭제
	@GetMapping("/notify/deleteNotifyProc")
	public String deleteNotifyProc(Notify notify) {
		notifyService.deleteNotify(notify);
		return "/notify/deleteNotifyProc";
	}

}
