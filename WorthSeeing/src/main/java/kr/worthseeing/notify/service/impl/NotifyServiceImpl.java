package kr.worthseeing.notify.service.impl;

import java.util.List;

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

@Service
public class NotifyServiceImpl implements NotifyService {

	@Autowired
	private NotifyRepository notifyRepo;

	@Override
	public void insertNotify(Notify notify) {
		notifyRepo.save(notify);
	}

	@Override
	public void updateNotify(Notify notify) {
		Notify findNotify = notifyRepo.findById(notify.getNotify_seq()).get();
		findNotify.setContent(notify.getContent());
		findNotify.setTitle(notify.getTitle());
		notifyRepo.save(findNotify);
	}

	@Override
	public void deleteNotify(Notify notify) {
		notifyRepo.deleteById(notify.getNotify_seq());
	}

	@Override
	public Notify getNotify(Notify notify) { //조회수
		Notify findNotify = notifyRepo.findById(notify.getNotify_seq()).get();
		findNotify.setViewCnt(findNotify.getViewCnt() + 1);
		return findNotify; 
	}
	
	@Override
	public Page<Notify> getNotify(Pageable pageable, Status status) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QNotify qnotify = QNotify.notify;
//		String status = String.valueOf(searchDTO.getStatus());
		builder.and(qnotify.status.status_seq.like("%" + 2 + "%"));
		
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "notify_seq");
		
		System.out.println("notifyRepo==> "+ notifyRepo.findAll(builder, pageable));
		
		return notifyRepo.findAll(builder, pageable);
	}
	
	
	@Override
	public Page<Notify> listNotify(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "notify_seq");
		
		return notifyRepo.getNotifyList(pageable);
	}
	
	    
	

}
