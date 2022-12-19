package kr.worthseeing.notify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import kr.worthseeing.notify.dto.SearchDTO;
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
	
	//글 등록
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

//	//글 수정
//	@Override
//	public void updateNotify(Notify notify) {
//		Notify findNotify = notifyRepo.findById(notify.getNotify_seq()).get();
//		findNotify.setContent(notify.getContent());
//		findNotify.setTitle(notify.getTitle());
//		notifyRepo.save(findNotify);
//	}
//	
	//글 삭제
	@Override
	public void deleteNotify(Notify notify) {
		notifyRepo.deleteById(notify.getNotify_seq());
	}
	
	//상세글, 조회수
	@Override
	public Notify getNotify(Notify notify) {
		Notify findNotify = notifyRepo.findById(notify.getNotify_seq()).get();
		findNotify.setViewCnt(findNotify.getViewCnt() + 1);
		return findNotify; 
	}
	`
	//페이징, 분류
	@Override
	public Page<Notify> getListNotify(Pageable pageable, SearchDTO search) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QNotify qnotify = QNotify.notify;
		builder.and(qnotify.status.status_seq.like("%" + search.getStatus() + "%"));
		if(search.getClass().equals("TITLE")) {
			builder.and(qnotify.title.like("%"+ search.getStatus()+"%"));
		}else if(search.getClass().equals("CONTENT")) {
			builder.and(qnotify.content.like("%"+search.getStatus()+"%"));
		}
		
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "notify_seq");
		
		System.out.println("notifyRepo==> "+ notifyRepo.findAll(builder, pageable));
		
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
