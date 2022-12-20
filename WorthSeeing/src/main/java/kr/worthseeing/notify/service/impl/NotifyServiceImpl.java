package kr.worthseeing.notify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
	public void insertNotify(Notify notify) {
		Status status = new Status();
		status.setStatus_seq(1);
		notify.setStatus(status);
		Users users = new Users();
		users.setUserId("user1");
		notify.setUsers(users);
		notifyRepo.save(notify);
	}
	
	//문의 글 등록
	@Override
	public void insertContact(Notify notify) {
		Status status = new Status();
		status.setStatus_seq(4);
		notify.setStatus(status);
		Users users = new Users();
		users.setUserId("user1");
		notify.setUsers(users);
		notifyRepo.save(notify);
	}

//	//글 수정
//	@Override
//	public void updateNotify(Notify notify) {
//		Notify findNotify = notifyRepo.findById(notify.getNotify_seq()).get();
//		findNotify.setContent(notify.getContent());
//		findNotify.setTitle(notify.getTitle());
//		notifyRepo.save(findNotify);
//	}
//	
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
		return findNotify;
	}
	
	//문의글 상세, 조회수
	@Override
	public Notify getContact(Notify notify) {
		Notify findNotify = notifyRepo.findById(notify.getNotifySeq()).get();
		findNotify.setViewCnt(findNotify.getViewCnt() + 1);
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
		
		
//		builder.and(qnotify.viewCnt.like("%" + status + "%"));
		System.out.println("impl---->" + status);
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "notifySeq");
		
//		return notifyRepo.getNotifyList(pageable);
		System.out.println("여긴뭐?"+notifyRepo.findAll(builder, pageable));
		return notifyRepo.findAll(builder, pageable);
	}

//	@Override
//	public Page<Notify> listNotify(Pageable pageable,Status status) {
//		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
//		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "notify_seq");
//		
//		return notifyRepo.getNotifyList(pageable);
//	}

}
