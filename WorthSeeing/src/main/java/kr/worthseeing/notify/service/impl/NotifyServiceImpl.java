
package kr.worthseeing.notify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;

import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.notify.entity.QNotify;
import kr.worthseeing.notify.repository.NotifyRepository;
import kr.worthseeing.notify.service.NotifyService;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;

@Service
public class NotifyServiceImpl implements NotifyService {

	@Autowired
	private NotifyRepository notifyRepo;
	  
	//공지 글 등록
	@Override
	@Transactional
	public void insertNotify(Notify notify, Users users) {
		Status status = new Status();
		status.setStatus_seq(2);
		System.out.println("impl users---->" + users);
		notify.setStatus(status);
		notify.setUsers(users);
		
//		Reply reply = new Reply();
//		notify.setReplyList(null);
		notifyRepo.save(notify);
	}
	
	@Override
	public void insertNotifyCnt(Notify notify) {
		Notify notify_db = notifyRepo.findById(notify.getNotifySeq()).get();
		notify_db.setViewCnt(notify_db.getViewCnt()+1);
		notifyRepo.save(notify_db);
	}
	
	//문의 글 등록
	@Override
	public void insertContact(Notify notify, Users users) {
		Status status = new Status();
		status.setStatus_seq(4);
		notify.setStatus(status);

		Users users2 = new Users();
		users2.setUserId(users.getUserId());
		notify.setUsers(users2);
//		notify.setReplyList(null);
		notifyRepo.save(notify);
	}

	//공지글 삭제
	@Override
	public void deleteNotify(Notify notify) {
		notifyRepo.deleteById(notify.getNotifySeq());
	}

	//공지 상세글, 조회수
	@Override
	public Notify getNotify(Notify notify) {
		Notify findNotify = notifyRepo.findById(notify.getNotifySeq()).get();
		findNotify.setViewCnt(findNotify.getViewCnt() + 1);
		System.out.println(">>>>>>>>>>"+findNotify);
		return findNotify; 
	}
	
	//문의글 상세, 조회수
	@Override
	public Notify getContact(Notify notify) {
		Notify findNotify = notifyRepo.findById(notify.getNotifySeq()).get();
		return findNotify;
	}
	
	
	//글 목록 페이징, 분류
	@Override
	public Page<Notify> getListNotify(Pageable pageable, String status) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QNotify qnotify = QNotify.notify;
		if(status==null) {
			status = "1";
		} 
		if(!status.equals("1")) {
			builder.and(qnotify.status.status_seq.like("%" + status + "%"));
		} else {
			builder.and(qnotify.status.status_seq.like("%" + "%"));
			
		}
		
		System.out.println("impl---->" + status);
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "notifySeq");
		System.out.println("여긴뭐?"+notifyRepo.findAll(builder, pageable));
		return notifyRepo.findAll(builder, pageable);
	}
	


}
