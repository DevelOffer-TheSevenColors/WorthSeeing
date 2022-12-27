//package kr.worthseeing;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import kr.worthseeing.users.entity.Users;
//import kr.worthseeing.users.repository.UsersRepository;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class InsertData {
//
//	@Autowired
//	private UsersRepository usersRepo;
//	
//	@Test
//	public void insertUser() {
//		
//		Users users = new Users(
//					"user1",
//					"user1",
//					"사용자",
//					"사용자1-닉네임",
//					"부산",
//					"센텀 광안대교",
//					"email@gmail.com",
//					"010-1234-1234"
//				);
//		
//		usersRepo.save(users);
//		
//	}
//	
//	
//}
