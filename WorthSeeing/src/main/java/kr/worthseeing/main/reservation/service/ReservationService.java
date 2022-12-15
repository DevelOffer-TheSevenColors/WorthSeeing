package kr.worthseeing.main.reservation.service;

import kr.worthseeing.main.reservation.entity.Reservation;

public interface ReservationService {

	void insertReservation(Reservation reservation);
	
	void deleteReservation(Reservation reservation);
}
