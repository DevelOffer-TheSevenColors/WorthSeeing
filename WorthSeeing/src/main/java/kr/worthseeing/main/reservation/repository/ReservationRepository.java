package kr.worthseeing.main.reservation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import kr.worthseeing.main.reservation.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

	@Query("select r from Reservation r")
	Page<Reservation> listReservation(Pageable pageable);

	@Query("select r.blockGroupWaiting.blockGroupWaiting_seq from Reservation r")
	List<Integer> listReservationBlockGroupSeq();

	@Query("select r.reservation_seq from Reservation r where r.blockGroupWaiting.blockGroupWaiting_seq = ?1")
	int getReservationSeqFromBlockGroupSeq(int blockGroupWaiting_seq);

	@Query("select r from Reservation r where block_Group_Waiting_Seq = ?1")
	Reservation findReservation(String block_group_waiting_seq);
	
//	@Query("DELETE FROM Reservation r Where block_group_waiting_seq = ?1")
//	@Modifying
//	@Transactional
//	void deleteReservation(int block_group_watiting_seq);
	

}
