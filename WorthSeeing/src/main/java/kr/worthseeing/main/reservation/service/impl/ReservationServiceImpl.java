package kr.worthseeing.main.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationLog;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.main.reservation.repository.ReservationLogRepository;
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

   @Autowired
   private ReservationLogRepository reservationLogRepo;
   
   @Autowired
   private BlockGroupWaitingRepository blockGroupWaitingRepo;
   
   @Autowired
   private BlockRepository blockRepo;
   
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
   public String insertReservationUsers(Reservation reservation, String userId) {

      String message = "이미 예약된 블록입니다.";
      Reservation reservation_db = reservationRepo.findById(reservation.getReservation_seq()).get();

      Users users2 = UsersRepo.findById(userId).get();

      users2.setUserId(userId);

      ReservationUsers reservationUsers = null;

      if (reservationUsersRepo.findOneReservationUsers(reservation.getReservation_seq(), userId).isEmpty()) {
         reservationUsers = new ReservationUsers();
         reservationUsers.setReservation(reservation);
         reservationUsers.setUsers(users2);

         reservation_db.setUserCnt(reservation_db.getUserCnt() + 1);

         reservationRepo.save(reservation_db);
         reservationUsersRepo.save(reservationUsers);
         message = "예약이 되었습니다.";
      }
      
      ReservationLog reservationLog = new ReservationLog();
      
      reservationLog.setReservationTime(new Date());
      reservationLog.setReservation_seq(reservation_db.getReservation_seq());
      reservationLog.setStartPrice(reservation_db.getStartPrice());
      reservationLog.setUseId(userId);
      

      reservationLogRepo.save(reservationLog);
      
      return message;
   }

   // 예약 취소
   @Override
   public void deleteReservation(Reservation reservation) {
      reservationRepo.deleteById(reservation.getReservation_seq());
   }

   // 예약 가능 목록
   @Override
   public Page<Reservation>selectReservation(Pageable pageable) {

      int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
      pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "reservation_seq");
      Page<Reservation> reservationPaging = reservationRepo.listReservation(pageable);

      return reservationPaging;
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

      ReservationUsers reservationUsers2 = reservationUsersRepo.findById(reservationUsers.getReservationUsers_seq()).get();

      Reservation findReservation = reservationRepo.findById(reservation.getReservation_seq()).get();
      
      
      if (!reservationUsersRepo.findOneReservationUsers(reservation.getReservation_seq(), userId).isEmpty()) {

         reservationUsers2.setReservation(reservation);
         reservationUsers2.setUsers(users2);
         
           findReservation.setUserCnt(findReservation.getUserCnt()-1); 
         
         reservationUsersRepo.delete(reservationUsers2);
      }
      int blockGroupWaitingSeq = reservationRepo.findById(reservation.getReservation_seq()).get().getBlockGroupWaiting().getBlockGroupWaiting_seq();
      if(reservationRepo.findById(reservation.getReservation_seq()).get().getUserCnt() ==0) {
         reservationRepo.deleteById(reservation.getReservation_seq());
         for(Block block : blockRepo.findAuctionBlock(String.valueOf(blockGroupWaitingSeq))) {
            blockRepo.updateBlock_GroupWaitingSeq(block.getBlock_seq());
            blockRepo.updateBlock_StatusSeq(block.getBlock_seq());
         }
         blockGroupWaitingRepo.deleteById(blockGroupWaitingSeq);
      }
      
   }
   
   @Override
   public String myBlockGroupWaitingYN() {
      String flag = "no";
      for(BlockGroupWaiting blockGroupWaiting : blockGroupWaitingRepo.findAll()) {
         if(blockGroupWaiting !=null) {
            if(blockGroupWaiting.getStatus().getStatus_seq() == 14) {
               flag = "yes";
            }
         }
      }      
      return flag;
   }

}