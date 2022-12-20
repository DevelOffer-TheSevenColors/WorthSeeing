package kr.worthseeing.reply.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.reply.entity.Reply;

public interface ReplyService {
	
void insertReply(Reply reply);
	
	void updateReply(Reply reply);
	
	void deleteReply(Reply reply);
	
	List<Reply> listReply(Notify notify, Pageable pageable);
	

}
