package kr.worthseeing.notify.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.worthseeing.notify.dto.SearchDTO;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.status.entity.Status;

public interface NotifyService {

	void insertNotify(Notify notify);

//	void updateNotify(Notify notify);

	void deleteNotify(Notify notify);

	Notify getNotify(Notify notify);

	Page<Notify> getListNotify(Pageable pageable, SearchDTO search);
	
	//Page<Notify> listNotify(Pageable pageable, Status status);


}
