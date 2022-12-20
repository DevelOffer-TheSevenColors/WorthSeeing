package kr.worthseeing.reply.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.reply.entity.Reply;
import kr.worthseeing.reply.repository.ReplyRepository;
import kr.worthseeing.reply.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyRepository replyRepo;
	
	@Override
	public void insertReply(Reply reply) {
		replyRepo.save(reply);
	}
	
	//댓글목록, 페이징
	@Override
	public List<Reply> listReply(Notify notify, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0:(pageable.getPageNumber() -1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "userId");
		
		List<Reply> replyList = new ArrayList<Reply>();
		for(Reply reply : replyRepo.findAll()) {
			Notify allNotify = reply.getNotify();
			if(allNotify.getUsers().equals(notify.getUsers())) {
				replyList.add(reply);
			}
		}
		
		return replyList;
	}
	
	//댓글 수정
	@Override
	public void updateReply(Reply reply) {
		Reply findReply = replyRepo.findById(reply.getReply_seq()).get();
		
		findReply.setReplyContent(reply.getReplyContent());
		findReply.setReplyDate(reply.getReplyDate());
		findReply.setReplyer(reply.getReplyer());
		
	}
	
	//댓글삭제
	@Override
	public void deleteReply(Reply reply) {
		replyRepo.delete(reply);
	}
	
	
	
}
