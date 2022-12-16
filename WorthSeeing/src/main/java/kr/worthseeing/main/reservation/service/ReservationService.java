package kr.worthseeing.main.reservation.service;

import java.util.List;

import kr.worthseeing.main.reservation.entity.Reservation;

public interface ReservationService {

	void insertReservation(Reservation reservation);
	
	void deleteReservation(Reservation reservation);
	
	public List<Reservation> selectReservation(Reservation reservation);
}
