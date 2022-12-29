package kr.worthseeing;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.main.reservation.repository.ReservationRepository;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertBlockGroup {

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private BlockGroupRepository blockGroupRepo;
	
	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;

	
//	@Test
	public void insertBlockGroup() {
		Users users = new Users();
		users.setUserId("user1");
	
		for (int i = 0; i < 153; i++) {
			BlockGroup blockGroup = new BlockGroup(
					i+1,
					"https://www.naver.com", 
					"/cimg/clientimg.png",
					"C:/serverImage/serverimg.png", 
					500
			);
			blockGroup.setUsers(users);

			blockGroupRepo.save(blockGroup);
		}

	}
	
	@Test
	void weafwef() {
		reservationRepo.deleteAll();
	}

}
