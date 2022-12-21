package kr.worthseeing.reply.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.reply.entity.Reply;

public interface ReplyService {

	void insertReply(Reply reply, Notify notify);

	// void updateReply(Reply reply);

	void deleteReply(Reply reply);

	Page<Reply> listReply(Notify notify, Pageable pageable);

}
