package kr.worthseeing.notify.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.worthseeing.notify.dto.SearchDTO;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.users.entity.Users;

public interface NotifyService {
 
	void insertNotify(Notify notify, Users users);

	Notify getNotify(Notify notify);
	
	void deleteNotify(Notify notify);
	
	Page<Notify> getListNotify(Pageable pageable,  String status); 
	
	//문의하기(contact)
	void insertContact(Notify notify, Users users);
	
	Notify getContact(Notify notify);
	
	public void insertNotifyCnt(Notify notify);
	

}
