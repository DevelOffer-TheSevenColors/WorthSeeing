package kr.worthseeing.main.reservation.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUserId;

public interface ReservationService {

	void insertReservation(Reservation reservation,ReservationUserId reservationUserid);
	
	void deleteReservation(Reservation reservation);
	
	public List<Reservation> selectReservation(Reservation reservation);
	
	public Reservation selectReservationCreditInfo(Reservation reservation);
}
