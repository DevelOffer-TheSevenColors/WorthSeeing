package kr.worthseeing.main.reservation.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.main.reservation.entity.ReservationLog;

public interface ReservationLogRepository  extends CrudRepository<ReservationLog, Integer>{
	
}
