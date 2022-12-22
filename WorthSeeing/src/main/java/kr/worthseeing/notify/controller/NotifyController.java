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
import kr.worthseeing.reply.service.ReplyService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;

@Controller
public class NotifyController {

	@Autowired
	private NotifyService notifyService;

	@Autowired
	private ReplyService replyService;

	// 글 목록
	@RequestMapping("/notify")
	public String getList(@PageableDefault Pageable pageable, Model model, String status) {

		model.addAttribute("notifyList", notifyService.getListNotify(pageable, status));

		return "/notify/notify";
	}

	// 공지글 등록
	@GetMapping("/notify/insertNotify")
	public String insertNotify() {
		return "/notify/insertNotify";
	}

	// 문의글 등록
	@GetMapping("/notify/insertContact")
	public String insertContact() {
		return "/notify/insertContact";
	}

	// 사용자가 공지글 등록
	@PostMapping("/notify/insertNotifyProc")
	public String insertNotifyProc(Notify notify) {
		notifyService.insertNotify(notify);
		return "redirect:/notify";
	}

	// 문의하기 글 등록
	@PostMapping("/notify/insertContactProc")
	public String insertContactProc(Notify notify) {
		notifyService.insertContact(notify);
		return "redirect:/notify";
	}

	// 문의글 상세
	@RequestMapping("/notify/getContact")
	public String getContact(Notify notify, Status status, Model model, @AuthenticationPrincipal SecurityUser principal,
			Pageable pageable) {
		System.out.println("controller principal.getUsers().getUserId()--->" + principal.getUsers().getUserId());
		Users users = new Users();
		users.setUserId(principal.getUsers().getUserId());
		notify.setUsers(users);

		model.addAttribute("principal", principal);
		model.addAttribute("notify", notifyService.getContact(notify));

		model.addAttribute("status_seq", notifyService.getContact(notify).getStatus().getStatus_seq());
		model.addAttribute("replyList", replyService.listReply(notify, pageable));

		return "/notify/getContact";
	}

	// 제목 클릭 시
	@RequestMapping("/notify/getDetail")
	public String getDetail(Notify notify, Status status, Model model,
			@AuthenticationPrincipal SecurityUser principal) {
		model.addAttribute("notify", notifyService.getContact(notify));
		model.addAttribute("principal", principal);

		notify.setViewCnt(notify.getViewCnt() + 1);

		if (status.getStatus_seq() == 4) {

			return "forward:/notify/getContact";

		} else if (status.getStatus_seq() == 2) {

			return "/notify/getNotify";

		} else {

			return "/main";
		}

	}

	// 공지 상세글
	@GetMapping("/notify/getNotify")
	public String getNotify(Notify notify, Model model, @AuthenticationPrincipal SecurityUser principal) {
		model.addAttribute("notify", notifyService.getNotify(notify));
		model.addAttribute("principal", principal);
		return "/notify/getNotify";
	}

	// 공지글 삭제
	@GetMapping("/notify/deleteNotifyProc")
	public String deleteNotifyProc(Notify notify) {
		notifyService.deleteNotify(notify);
		return "/notify/deleteNotifyProc";
	}

}
