package kr.worthseeing.main.reservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.main.reservation.entity.Reservation;

public interface ReservationRepository  extends CrudRepository<Reservation, Integer>{
	@Query("select r from Reservation r")
	Page<Reservation> listReservation(Pageable pageable);
}
