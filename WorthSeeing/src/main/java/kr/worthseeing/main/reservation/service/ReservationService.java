package kr.worthseeing.main.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.users.entity.Users;

public interface ReservationService {

	// Reservation의 BlockGroup_seq 가져오기
	List<Integer> listReservationBlockGroupSeq();
	
	//결제후 예약목록에 추가
	String insertReservationUsers(Reservation reservation, String userId);

	//마이예약목록에서 삭제
	void deleteReservationUsers(Reservation reservation, String userId, ReservationUsers reservationUsers);
	
	void deleteReservation(Reservation reservation);
	
	public Page<Reservation> selectReservation(Pageable pageable);
	
	public List<ReservationUsers> selectMyReservation(String userid);
	
	public Reservation selectReservationCreditInfo(Reservation reservation);
	
	public List<ReservationUsers> findOneReservation(Reservation reservation, Users user);
	
	public int getReservationSeq(int blockGroup_seq);
	
	public String myBlockGroupWaitingYN();
	
}
