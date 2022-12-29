//package kr.worthseeing;
//
//import org.junit.jupiter.api.Test;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import kr.worthseeing.block.entity.Block;
//import kr.worthseeing.block.repository.BlockRepository;
//import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
//import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
//import kr.worthseeing.blockgroup.entity.BlockGroup;
//import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
//import kr.worthseeing.event.coupon.entity.Coupon;
//import kr.worthseeing.event.coupon.repository.CouponRepository;
//import kr.worthseeing.main.auction.entity.Auction;
//import kr.worthseeing.main.auction.repository.AuctionRepository;
//import kr.worthseeing.main.reservation.entity.Reservation;
//import kr.worthseeing.main.reservation.entity.ReservationUsers;
//import kr.worthseeing.main.reservation.repository.ReservationRepository;
//import kr.worthseeing.main.reservation.repository.ReservationUsersRepository;
//import kr.worthseeing.status.entity.Status;
//import kr.worthseeing.status.repository.StatusRepository;
//import kr.worthseeing.users.entity.Users;
//import kr.worthseeing.users.repository.UsersRepository;
//
///*
// * TestCode 실행방법
// * 
// * @Test 순서대로 하나씩 주석 풀면서 계속 실행
// * 
// * 제일 처음 @Test 실행할 때만 application.properties에 create로 설정하고 실행
// * 두번째부터는 update로 변경하고 실행
// * 두번째에 update로 안 바꿨으면 처음부터 다시 실행
// * 
// */
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class reservationTest {
//
//	@Autowired
//	private StatusRepository statusRepo;
//
//	@Autowired
//	private UsersRepository usersRepo;
//
//	@Autowired
//	private BlockGroupRepository blockGroupRepo;
//
//	@Autowired
//	private BlockRepository blockRepo;
//
//	@Autowired
//	private ReservationRepository reservationRepo;
//
//	@Autowired
//	private ReservationUsersRepository reservationUsersRepo;
//
//	@Autowired
//	private CouponRepository couponRepo;
//
//	@Autowired
//	private AuctionRepository auctionRepo;
//	
//	@Autowired
//	private BlockGroupWaitingRepository blockGroupWaitingRepo;
//
////	@Test
//	public void StatusInsert() {
//		for (int i = 1; i < 5; i++) {
//			System.out.println(DecimalToBinary("A", i));
//		}
//
//		for (int i = 1; i < 4; i++) {
//			System.out.println(DecimalToBinary("B", i));
//		}
//
//		for (int i = 1; i < 8; i++) {
//			System.out.println(DecimalToBinary("C", i));
//		}
//
//		for (int i = 1; i < 5; i++) {
//			System.out.println(DecimalToBinary("D", i));
//		}
//
//	}
//
////	@Test
//	public void insertBlock() {
//
//		// block
//
//		for (int i = 0; i < 289; i++) {
//
//			Block block = new Block();
//
//			block.setBlock_seq(i + 1);
//
//			BlockGroup blockGroup = new BlockGroup();
//			blockGroup.setBlockGroup_seq(i + 1); // error 나면 seq 보고 변경하기
//			Status status = new Status();
//			status.setStatus_seq(10);
//			block.setStatus(status);
//			
//			block.setBlockGroup(blockGroup);
//
//			blockRepo.save(block);
//		}
//
//		// 예약
////		for (int i = 0; i < 15; i++) {
////
////			Reservation reservation = new Reservation(1000, 14);
////			BlockGroup blockGroup = new BlockGroup();
////			blockGroup.setBlockGroup_seq(i + 1); // error 나면 seq 보고 변경하기
////			reservation.setBlockGroup(blockGroup);
//
////			reservationRepo.save(reservation);
//
////		}
//
//		// 쿠폰
//		Status status = new Status();
//		status.setStatus_seq(2);
//
//		Users users = new Users();
//		users.setUserId("testid");
//
//		for (int i = 0; i < 10; i++) {
//			Coupon coupon = new Coupon(30000, "1234-1234-1234");
//			coupon.setStatus(status);
//			coupon.setUsers(users);
//			couponRepo.save(coupon);
//		}
//		for (int i = 0; i < 10; i++) {
//			Coupon coupon = new Coupon(50000, "1111-1111-1111");
//			coupon.setStatus(status);
//			coupon.setUsers(users);
//			couponRepo.save(coupon);
//		}
//		for (int i = 0; i < 10; i++) {
//			Coupon coupon = new Coupon(100000, "4444-4444-4444");
//			coupon.setStatus(status);
//			coupon.setUsers(users);
//			couponRepo.save(coupon);
//		}
//
//	}
//
////	@Test
//	public void insertReservationUserId() {
//		Users users = new Users();
//		users.setUserId("testid");
//
//		Reservation reservation = new Reservation();
//		reservation.setReservation_seq(28); // error 나면 seq 보고 변경하기
//
//		ReservationUsers reservationUserId = new ReservationUsers();
//
//		reservationUserId.setReservation(reservation);
//		reservationUserId.setUsers(users);
//
//		reservationUsersRepo.save(reservationUserId);
//
//		Auction auction = new Auction();
//
//		auction.setReservation(reservation);
//		auction.setUserId(users.getUserId());
//
//		auctionRepo.save(auction);
//
//	}
//
////	@Test
//	public void updateBlockGroupClickCnt() {
//
//		for (int i = 0; i < 289; i++) {
//			BlockGroup findBlockGroup = blockGroupRepo.findById(i + 1).get();
//
//			Random rand = new Random();
//
//			findBlockGroup.setClickCnt(rand.nextInt(1000));
//
//			blockGroupRepo.save(findBlockGroup);
//
//		}
//
//	}
//
////	@Test
//	public void ReservationUsers() {
//		ReservationUsers reservationUsers = new ReservationUsers();
//
//		Reservation reservation = new Reservation();
//		reservation.setReservation_seq(28);
//
//		reservationUsers.setReservation(reservation);
//
//		Users users = new Users();
//		users.setUserId("user1");
//
//		reservationUsers.setUsers(users);
//
//		reservationUsersRepo.save(reservationUsers);
//	}
//
//	int DecimalToBinary(String A, int number) {
//		int Binary_number = 0;
//		int count = 0;
//		while (number != 0) {
//			int remainder = number % 2;
//			double temp = Math.pow(10, count);
//			Binary_number += remainder * temp;
//			number /= 2;
//
//			count++;
//		}
//
//		Status status = new Status(A, String.valueOf(String.format("%04d", Binary_number)));
//		statusRepo.save(status);
//		return Binary_number;
//	}
//	// 아래 #1, #2, #3를 하나씩 해서 3번 테스트 실행하시오!
//
//	 @Test
//	public void CouponStatus() {
//		// 쿠폰
//		Status status = new Status();
//		for (int i = 0; i < 20; i++) {
//
//			status.setStatus_seq(5);
//			int couponSize = 3;
//
//			final char[] possibleCharacters = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D',
//					'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
//					'Y', 'Z' };
//
//			final int possibleCharacterCount = possibleCharacters.length;
//			Random rnd = new Random();
//			int currentIndex = 0;
//			int j = 0;
//			String couponnum = "";
//			while (currentIndex < couponSize) {
//				StringBuffer buf = new StringBuffer(16);
//				// i는 8자리의 랜덤값을 의미
//				for (j = 8; j > 0; j--) {
//					buf.append(possibleCharacters[rnd.nextInt(possibleCharacterCount)]);
//				}
//				couponnum += buf.toString() + "-";
//				currentIndex++;
//			}
//			couponnum = couponnum.substring(0, couponnum.length() - 1);
//			System.out.println("=====adsf==>" + couponnum);
//
//			// #1
////				Coupon coupon1 = new Coupon(30000, couponnum);
////				coupon1.setStatus(status);
////				couponRepo.save(coupon1);
//
////			 #2
//				Coupon coupon2 = new Coupon(50000, couponnum);
//				coupon2.setStatus(status);
//				couponRepo.save(coupon2);
//			// #3
////			Coupon coupon3 = new Coupon(100000, couponnum);
////			coupon3.setStatus(status);
////			couponRepo.save(coupon3);
//		}
//
//	}
//
////	@Test
//	public void updateBlockGroupEndDate() {
//		for (int i = 1; i <= 289; i++) {
//			BlockGroup findBlockGroup = blockGroupRepo.findById(i).get();
//
////			LocalDate now = LocalDate.now();
////			Random rand = new Random();
////
////			LocalDate result1 = now.plusDays(rand.nextInt(30));
////
////			result1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
////			System.out.println("format--->" + result1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
////
////			findBlockGroup.setEndDate(result1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//			findBlockGroup.setBlockNumber("" + i);
//			blockGroupRepo.save(findBlockGroup);
//
//		}
//	}
//
////	@Test
//	public void zzzz() {
//		for (int i = 0; i < 9; i++) {
//			BlockGroup findBlockGroup = blockGroupRepo.findById(i + 1).get();
//			
//			//userId 너무 많아서 바꾸려고 한거 
////			Users user=new Users();
////			user.setUserId("admin");
////			findBlockGroup.setUsers(user);
//			
//			findBlockGroup.setMinBlockSeq(1);
//			
////			findBlockGroup.setCImg("https://kwangan2-worthseeing-burket.s3.eu-west-2.amazonaws.com/defaultIMG.png");
//
////			findBlockGroup.setStatus(status);
//
//			blockGroupRepo.save(findBlockGroup);
//
//		}
//
//	}
//	
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	
////	@Test
//	public void insertBlockGroup() {
//
//		Users users = new Users();
//		users.setUserId("admin");
//
//		for (int i = 0; i < 289; i++) {
//			BlockGroup blockGroup = new BlockGroup(i+1, "https://www.naver.com", "https://kwangan2-worthseeing-burket.s3.eu-west-2.amazonaws.com/defaultIMG.png",
//					"C:/serverImage/serverimg.png", 0);
//			blockGroup.setUsers(users);
//			blockGroup.setWidth(100);
//			blockGroup.setHeight(100);
//			blockGroup.setMinBlockSeq(i+1);
//			
//			blockGroupRepo.save(blockGroup);
//		}
//
//	}
//	
////	@Test
//	public void updateBlockBlockGroupSeq() {
//		List<Block> blockList = (List<Block>) blockRepo.listblock();
//		
//		int i = 1;
//		
//		for (Block blockItem : blockList) {
//			BlockGroup blockGroup = new BlockGroup();
//			blockGroup.setBlockGroup_seq(i);
//			
//			blockItem.setBlockGroup(blockGroup);
//			i++;
//			blockRepo.save(blockItem);
//		}
//		
//	}
//	
////	@Test
//	public void insertBlockTest() {
//		for (int i = 1; i <= 289; i++) {
//			Block block = new Block();
//			block.setBlock_seq(i);
//			
//			BlockGroup blockGroup = new BlockGroup();
//			blockGroup.setBlockGroup_seq(1);
//			block.setBlockGroup(blockGroup);
//			
//			BlockGroupWaiting blockGroupWaiting = new BlockGroupWaiting();
//			blockGroupWaiting.setBlockGroupWaiting_seq(1);
//			block.setBlockGroupWaiting(blockGroupWaiting);
//			
//			Status status = new Status();
//			status.setStatus_seq(10);
//			block.setStatus(status);
//			
//			if (i % 17 == 0) {
//				block.setXLocation(17);
//				block.setYLocation((i / 17));
//			} else {
//				block.setXLocation(i % 17);
//				block.setYLocation(i / 17 + 1);
//			}
//
//			blockRepo.save(block);
//
//		}
//	}
//
//}