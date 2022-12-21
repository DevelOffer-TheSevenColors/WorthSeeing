 package kr.worthseeing.reply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//댓글 목록
	@RequestMapping("/listReply")
	public String listReply(Reply reply, @PageableDefault Pageable pageable, Model model) {
		
		return "/listReply";
	}
	
	@PostMapping("/insertReplyProc")
	public String insertReplyProc(Reply reply, Notify notify, Status status, @AuthenticationPrincipal SecurityUser principal) {
		System.out.println("================>" + principal);
		reply.setReplyer(principal.getUsers().getUserId());
		
		replyService.insertReply(reply, notify);
		return "redirect:/notify/getDetail?notifySeq="+notify.getNotifySeq()+"&status_seq="+status.getStatus_seq();
	}
}
