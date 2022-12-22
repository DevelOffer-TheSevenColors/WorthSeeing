package kr.worthseeing.reply.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.reply.entity.Reply;

public interface ReplyService {

	void insertReply(Reply reply, Notify notify);

	void deleteReply(Reply reply);

	Page<Reply> listReply(Notify notify, Pageable pageable);

}
