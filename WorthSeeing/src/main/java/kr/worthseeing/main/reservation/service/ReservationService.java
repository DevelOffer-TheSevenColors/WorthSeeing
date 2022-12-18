package kr.worthseeing.main.reservation.service;

import java.util.List;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.reservation.entity.Reservation;

public interface ReservationService {

	void insertReservation(Reservation reservation);
	
	void deleteReservation(Reservation reservation);
	
	public List<Reservation> selectReservation(Reservation reservation);
	
	public Reservation selectReservationCreditInfo(Reservation reservation);
}
