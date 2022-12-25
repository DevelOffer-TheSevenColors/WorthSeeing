package kr.worthseeing.main.reservation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import kr.worthseeing.main.auction.repository.AuctionRepository;
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
	
	@Autowired
	private AuctionRepository auctionRepo;

	@Override
	public List<Integer> listReservationBlockGroupSeq() {
		return reservationRepo.listReservationBlockGroupSeq();
	}
	
	@Override
	public int getReservationSeq(int blockGroup_seq) {
		return reservationRepo.getReservationSeqFromBlockGroupSeq(blockGroup_seq);
	}

	// 보증금 10퍼 결제하기 버튼 클릭 시 예약자 수 + 1 / ReservationUserId 테이블에 데이터 insert
	@Override
	public void insertReservationUsers(Reservation reservation, String userId) {

		Reservation reservation_db = reservationRepo.findById(reservation.getReservation_seq()).get();

		Users users2 = UsersRepo.findById(userId).get();

		users2.setUserId(userId);

		System.out.println("=====>1" + reservation);
		System.out.println("=====>2" + userId);
		ReservationUsers reservationUsers = null;

		if (reservationUsersRepo.findOneReservationUsers(reservation.getReservation_seq(), userId).isEmpty()) {
			reservationUsers = new ReservationUsers();
			reservationUsers.setReservation(reservation);
			reservationUsers.setUsers(users2);

			reservation_db.setUserCnt(reservation_db.getUserCnt() + 1);

			reservationRepo.save(reservation_db);
			reservationUsersRepo.save(reservationUsers);
		}
	}

	// 예약 취소
	@Override
	public void deleteReservation(Reservation reservation) {
		reservationRepo.deleteById(reservation.getReservation_seq());
	}

	// 예약 가능 목록
	@Override
	public Map<String, Object> selectReservation(Pageable pageable) {

		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "reservation_seq");
		
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Reservation> reservationPaging = reservationRepo.listReservation(pageable);
		
		if (reservationPaging.getTotalElements() == 0) {
			map.put("flag", "no");
			
		} else {
			map.put("flag", "yes");
			map.put("reservationList", reservationRepo.listReservation(pageable));
		}

		return map;

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

	@Override
	public void deleteReservationUsers(Reservation reservation, String userId, ReservationUsers reservationUsers) {

		Users users2 = UsersRepo.findById(userId).get();
		users2.setUserId(userId);

		ReservationUsers reservationUsers2 = reservationUsersRepo.findById(reservationUsers.getReservationUsers_seq())
				.get();

		if (!reservationUsersRepo.findOneReservationUsers(reservation.getReservation_seq(), userId).isEmpty()) {

			reservationUsers2.setReservation(reservation);
			reservationUsers2.setUsers(users2);

			reservationUsersRepo.delete(reservationUsers2);
		}

	}

	@Override
	public String auctionStartYes() {
		String flag = "yes";
		System.out.println(auctionRepo.findAll());
		if (auctionRepo.findAll().iterator().hasNext()) {
			flag = "no";
		}

		return flag;
	}

}