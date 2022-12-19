package kr.worthseeing.main.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.main.reservation.repository.ReservationRepository;
import kr.worthseeing.main.reservation.repository.ReservationUsersRepository;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.users.entity.Users;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	private ReservationUsersRepository reservationUsersRepo;

	// 보증금 10퍼 결제하기 버튼 클릭 시 예약자 수 + 1 / ReservationUserId 테이블에 데이터 insert
	@Override
	public void insertReservationUsers(Reservation reservation, String userId) {

		Users users2 = new Users();
		users2.setUserId(userId);

		ReservationUsers reservationUserId = new ReservationUsers();

		reservationUserId.setReservation(reservation);
		reservationUserId.setUsers(users2);

		reservationUsersRepo.save(reservationUserId);
		
//		ReservationUsers reservationUsers = new ReservationUsers();
//
//		reservationUsers.setReservation(reservation);
//
//		reservationUsers.setUsers(users);
//
//		reservationUsersRepo.save(reservationUsers);
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
