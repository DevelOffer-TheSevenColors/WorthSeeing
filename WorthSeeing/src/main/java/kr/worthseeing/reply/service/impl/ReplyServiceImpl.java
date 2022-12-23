package kr.worthseeing.reply.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.reply.entity.QReply;
import kr.worthseeing.reply.entity.Reply;
import kr.worthseeing.reply.repository.ReplyRepository;
import kr.worthseeing.reply.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepo;
 
	@Override
	public void insertReply(Reply reply, Notify notify) {
		reply.setNotify(notify);

		replyRepo.save(reply);
	}

	// 댓글목록, 페이징
	@Override
	public Page<Reply> listReply(Notify notify, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		QReply qreply = QReply.reply;

		builder.and(qreply.notify.notifySeq.eq(notify.getNotifySeq()));

		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "replySeq");

		return replyRepo.findAll(builder, pageable);

	}

	// 댓글삭제
	@Override
	public void deleteReply(Reply reply) {
		replyRepo.delete(reply);
	}

}
