package kr.worthseeing.notify.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.worthseeing.notify.dto.SearchDTO;
import kr.worthseeing.notify.entity.Notify;

public interface NotifyService {

	void insertNotify(Notify notify);

	void deleteNotify(Notify notify);

	Notify getNotify(Notify notify);
	
	Page<Notify> getListNotify(Pageable pageable,  String status);
	
	
	//문의하기(contact)
	void insertContact(Notify notify);
	
	Notify getContact(Notify notify);
	
	
	//Page<Notify> listNotify(Pageable pageable, Status status);


}
