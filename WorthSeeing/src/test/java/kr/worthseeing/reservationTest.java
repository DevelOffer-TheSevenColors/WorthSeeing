package kr.worthseeing;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.Test;
import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.repository.CouponRepository;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUserId;
import kr.worthseeing.main.reservation.repository.ReservationRepository;
import kr.worthseeing.main.reservation.repository.ReservationUserIdRepository;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.status.repository.StatusRepository;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

/*
 * TestCode 실행방법
 * 
 * @Test 순서대로 하나씩 주석 풀면서 계속 실행
 * 
 * 제일 처음 @Test 실행할 때만 application.properties에 create로 설정하고 실행
 * 두번째부터는 update로 변경하고 실행
 * 두번째에 update로 안 바꿨으면 처음부터 다시 실행
 * 
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class reservationTest {

	@Autowired
	private StatusRepository statusRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private BlockGroupRepository blockGroupRepo;

	@Autowired
	private BlockRepository blockRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private ReservationUserIdRepository reservationUserIdRepo;
	
	@Autowired
	private CouponRepository couponRepo;

//	@Test
	public void StatusIns() {}
	
//	@Test
	public void StatusInsert() {
		for (int i = 1; i < 5; i++) {
			System.out.println(DecimalToBinary("A", i));
		}

		for (int i = 1; i < 4; i++) {
			System.out.println(DecimalToBinary("B", i));
		}

		for (int i = 1; i < 8; i++) {
			System.out.println(DecimalToBinary("C", i));
		}

		for (int i = 1; i < 5; i++) {
			System.out.println(DecimalToBinary("D", i));
		}

	}

//	@Test
	public void insertBlockGroup() {
 
		Users users = new Users("user1", "user1", "사용자", "사용자1-닉네임", "부산", "센텀 광안대교", "email@gmail.com",
				"010-1234-1234");

		usersRepo.save(users); 

		BlockGroup blockGroup = new BlockGroup("https://www.naver.com", "/cimg/clientimg.png",
				"C:/serverImage/serverimg.png", 500);

		blockGroup.setUsers(users);

		blockGroupRepo.save(blockGroup);

	}

	@Test
	public void insertBlock() {
		BlockGroup blockGroup = new BlockGroup();
		blockGroup.setBlockGroup_seq(19);

		Status status = new Status();
		status.setStatus_seq(2);
		
		Users users = new Users();
		users.setUserId("user1");
/*
		for (int i = 0; i < 153; i++) {

			Block block = new Block();
			block.setBlock_seq(i + 1);
			block.setBlockGroup(blockGroup);
			block.setStatus(status);

			blockRepo.save(block);
		}
		
		for (int i = 0; i < 15; i++) {

			Reservation reservation = new Reservation(1000, 14);
			
			reservation.setBlockGroup(blockGroup);
			
			reservationRepo.save(reservation);
			
		}
		
		
		for(int i=0; i<10; i++) {
			Coupon coupon = new Coupon(30000, "1234-1234-1234");
			coupon.setStatus(status);
			coupon.setUsers(users);
			couponRepo.save(coupon);
		}
		for(int i=0; i<10; i++) {
			Coupon coupon = new Coupon(50000, "1111-1111-1111");
			coupon.setStatus(status);
			coupon.setUsers(users);
			couponRepo.save(coupon);
		}
		for(int i=0; i<10; i++) {
			Coupon coupon = new Coupon(100000, "4444-4444-4444");
			coupon.setStatus(status);
			coupon.setUsers(users);
			couponRepo.save(coupon);
		}
		*/
		
		
		

	}

	
//	@Test 안됨
	public void insertReservationUserId() {
		Users users = new Users();
		users.setUserId("user1");
		
		Reservation reservation = new Reservation();
		reservation.setReservation_seq(30);
		
		ReservationUserId reservationUserId = new ReservationUserId();
		
		reservationUserId.setReservation(reservation);
		reservationUserId.setUsers(users);
		
		reservationUserIdRepo.save(reservationUserId);
	}
	
	
	
	
	
	
	
	
	int DecimalToBinary(String A, int number) {
		int Binary_number = 0;
		int count = 0;
		while (number != 0) {
			int remainder = number % 2;
			double temp = Math.pow(10, count);
			Binary_number += remainder * temp;
			number /= 2;

			count++;
		}

		Status status = new Status(A, String.valueOf(String.format("%04d", Binary_number)));
		statusRepo.save(status);
		return Binary_number;
	}

}
