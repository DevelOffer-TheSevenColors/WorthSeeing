package kr.worthseeing.notify.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import kr.worthseeing.notify.dto.SearchDTO;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.status.entity.Status;

public interface NotifyService {

	void insertNotify(Notify notify);

	void updateNotify(Notify notify);

	void deleteNotify(Notify notify);

	Notify getNotify(Notify notify);

	Page<Notify> getNotify(Pageable pageable, Status status);
	
	List<Notify> listNotify();


}
