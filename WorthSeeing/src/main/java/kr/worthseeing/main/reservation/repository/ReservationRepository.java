package kr.worthseeing.main.reservation.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.main.reservation.entity.Reservation;

public interface ReservationRepository  extends CrudRepository<Reservation, Integer>{
	
}
