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

//	@Test
	public void insertBlock() {
		BlockGroup blockGroup = new BlockGroup();
		blockGroup.setBlockGroup_seq(19);

		Status status = new Status();
		status.setStatus_seq(2);

		for (int i = 0; i < 153; i++) {

			Block block = new Block();
			block.setBlock_seq(i + 1);
			block.setBlockGroup(blockGroup);
			block.setStatus(status);

			blockRepo.save(block);
		}

	}

	@Test
	public void insertReservation() {
		
		
		
		
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
