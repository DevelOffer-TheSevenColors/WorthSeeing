package kr.worthseeing.main.reservation.service;

import java.util.List;

import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.users.entity.Users;

public interface ReservationService {

	void insertReservation(Reservation reservation, Users users);
	
	void deleteReservation(Reservation reservation);
	
	public List<Reservation> selectReservation(Reservation reservation);
	
	public Reservation selectReservationCreditInfo(Reservation reservation);
}
