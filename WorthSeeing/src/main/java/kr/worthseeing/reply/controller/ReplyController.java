package kr.worthseeing.reply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.reply.entity.Reply;
import kr.worthseeing.reply.service.ReplyService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.status.entity.Status;

@Controller
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	ReplyService replyService;

	// 댓글 목록
	@RequestMapping("/listReply")
	public String listReply(Reply reply, @PageableDefault Pageable pageable, Model model) {
		return "/listReply";
	}

	// 댓글 작성
	@PostMapping("/insertReplyProc")
	public String insertReplyProc(Reply reply, Notify notify, Status status,
			@AuthenticationPrincipal SecurityUser principal) {
		reply.setReplyer(principal.getUsers().getName());

		replyService.insertReply(reply, notify);
		return "redirect:/notify/getDetail?notifySeq=" + notify.getNotifySeq() + "&status_seq="
				+ status.getStatus_seq();
	}
	@GetMapping("/deleteReplyProc")
	public String deleteReplyProc(Reply reply, Status status, Notify notify,@AuthenticationPrincipal SecurityUser principal) {
		replyService.deleteReply(reply);
		
		return "redirect:/notify/getContact?notifySeq=" + notify.getNotifySeq() + "&status_seq=" + status.getStatus_seq();
	}
}
