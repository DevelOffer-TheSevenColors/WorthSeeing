package kr.worthseeing.main.reservation.service;

import java.util.List;

import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.users.entity.Users;

public interface ReservationService {

	//결제후 예약목록에 추가
	void insertReservationUsers(Reservation reservation, String userId);

	//마이예약목록에서 삭제
	void deleteReservationUsers(Reservation reservation, String userId, ReservationUsers reservationUsers);
	
	void deleteReservation(Reservation reservation);
	
	public List<Reservation> selectReservation(Reservation reservation);
	
	public List<ReservationUsers> selectMyReservation(String userid);
	
	public Reservation selectReservationCreditInfo(Reservation reservation);
	
	public List<ReservationUsers> findOneReservation(Reservation reservation, Users user);
	
	public void insertUserMaxPrice(ReservationUsers reservationUser);
}
