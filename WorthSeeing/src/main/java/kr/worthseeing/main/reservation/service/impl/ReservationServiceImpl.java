package kr.worthseeing.main.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUserId;
import kr.worthseeing.main.reservation.repository.ReservationRepository;
import kr.worthseeing.main.reservation.repository.ReservationUserIdRepository;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.users.entity.Users;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	private ReservationUserIdRepository reservationUseridRepo;

	// 예약
	@Override
	public void insertReservation(Reservation reservation, Users users) {
		System.out.println("reservation==>" + reservation);
		Reservation findreservation = reservationRepo.findById(reservation.getReservation_seq()).get();

		findreservation.setUserCnt(findreservation.getUserCnt() + 1); // 예약자 수 + 1

		ReservationUserId reservationUserid = new ReservationUserId();

		reservationUserid.setUserId("testid");
		reservationUserid.setReservation(findreservation);
		System.out.println("reservationUserid-==>" + reservationUserid);
		reservationRepo.save(findreservation);
		reservationUseridRepo.save(reservationUserid);
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

	// 10프로 예약결제페이지
	@Override
	public Reservation selectReservationCreditInfo(Reservation reservation) {

		return reservationRepo.findById(reservation.getReservation_seq()).get();
	}
}
