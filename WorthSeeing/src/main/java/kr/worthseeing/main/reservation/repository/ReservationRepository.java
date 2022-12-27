package kr.worthseeing.main.reservation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.main.reservation.entity.Reservation;

public interface ReservationRepository  extends CrudRepository<Reservation, Integer>{
	
	@Query("select r from Reservation r")
	Page<Reservation> listReservation(Pageable pageable);
	
	@Query("select r.blockGroupWaiting.blockGroupWaiting_seq from Reservation r")
	List<Integer> listReservationBlockGroupSeq();
	
	@Query("select r.reservation_seq from Reservation r where r.blockGroupWaiting.blockGroupWaiting_seq = ?1")
	int getReservationSeqFromBlockGroupSeq(int blockGroup_seq);
	
}
