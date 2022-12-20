package kr.worthseeing.reply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.worthseeing.reply.entity.Reply;
import kr.worthseeing.reply.service.ReplyService;


@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	//댓글 목록
	@RequestMapping("/listReply")
	public void listReply(Reply reply, @PageableDefault Pageable pageable, Model model) {
	}
}
