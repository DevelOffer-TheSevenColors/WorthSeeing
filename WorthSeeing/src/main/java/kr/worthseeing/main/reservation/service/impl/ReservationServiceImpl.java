package kr.worthseeing.main.reservation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.main.reservation.repository.ReservationRepository;
import kr.worthseeing.main.reservation.repository.ReservationUsersRepository;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	private ReservationUsersRepository reservationUsersRepo;
	
	
	
	@Autowired
	private UsersRepository UsersRepo;
	
	@Override
	public void insertUserMaxPrice(ReservationUsers reservationUser) {
		reservationUsersRepo.save(reservationUser);
	}

	// 보증금 10퍼 결제하기 버튼 클릭 시 예약자 수 + 1 / ReservationUserId 테이블에 데이터 insert
		@Override
		public void insertReservationUsers(Reservation reservation, String userId) {

			Users users2 = UsersRepo.findById(userId).get();
			
			users2.setUserId(userId);
			
			ReservationUsers reservationUsers = null;
			
			if(reservationUsersRepo.findOneReservationUsers(reservation.getReservation_seq(), userId).isEmpty()) {
				reservationUsers =new ReservationUsers();
				
				reservationUsers.setReservation(reservation);
				reservationUsers.setUsers(users2);

				reservationUsersRepo.save(reservationUsers);
			}
			
	}
		
		// 보증금 10퍼 결제하기 버튼 클릭 시 예약자 수 + 1 / ReservationUserId 테이블에 데이터 insert
		@Override
		public void deleteReservationUsers(Reservation reservation, String userId, ReservationUsers reservationUsers) {
			
			Users users2 = UsersRepo.findById(userId).get();
			users2.setUserId(userId);
			
			ReservationUsers reservationUsers2 = reservationUsersRepo.findById(reservationUsers.getReservationUsers_seq()).get();
			
			if(!reservationUsersRepo.findOneReservationUsers(reservation.getReservation_seq(), userId).isEmpty()) {
				
				reservationUsers2.setReservation(reservation);
				reservationUsers2.setUsers(users2);
				
				reservationUsersRepo.delete(reservationUsers2);
			}
			
		}

	// 예약 취소
	@Override
	public void deleteReservation(Reservation reservation) {
		reservationRepo.deleteById(reservation.getReservation_seq());
	}

	// 예약 가능 목록
	@Override
	public List<Reservation> selectReservation(Reservation reservation) {

		return (List<Reservation>) reservationRepo.findAll();

	}

	// 예약 가능 목록
	@Override
	public List<ReservationUsers> findOneReservation(Reservation reservation, Users user) {	
		return reservationUsersRepo.findOneReservationUsers(reservation.getReservation_seq(), user.getUserId());
	}

	// 나의 예약 리스트 목록
		@Override
		public List<ReservationUsers> selectMyReservation(String userid) {

			return reservationUsersRepo.findReservationUsers(userid);
			
		}
	
	
	// 10프로 예약결제페이지
	@Override
	public Reservation selectReservationCreditInfo(Reservation reservation) {

		return reservationRepo.findById(reservation.getReservation_seq()).get();
	}
}
