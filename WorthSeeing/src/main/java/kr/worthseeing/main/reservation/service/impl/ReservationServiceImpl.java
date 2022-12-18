package kr.worthseeing.main.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.repository.ReservationRepository;
import kr.worthseeing.main.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	BlockGroupRepository blockGroupRepository;

	// 예약
	@Override
	public void insertReservation(Reservation reservation) {

		reservationRepo.save(reservation);
	}

	// 예약 취소
	@Override
	public void deleteReservation(Reservation reservation) {
		reservationRepo.deleteById(reservation.getReservation_seq());
	}

	// 예약 가능 목록
	public List<Reservation> selectReservation(Reservation reservation) {

		return (List<Reservation>) reservationRepo.findAll();

	}

	@Autowired
	private BlockGroupRepository blockGroupRepo;
	
	// 10프로 예약결제페이지
	@Override
	public Reservation selectReservationCreditInfo(Reservation reservation) {
		
		
		
		
		

		return  reservationRepo.findById(reservation.getReservation_seq()).get();
	}
}
