package kr.worthseeing.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;
import kr.worthseeing.users.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepository usersRepo;
	
	@Override
	public void insertUsers(Users users) {
		usersRepo.save(users);
	}
	
	@Override
	public void updateUsers(Users users) {
		Users findUsers = usersRepo.findById(users.getUserId()).get();
		
		findUsers.setAddress(users.getAddress()); // 주소
		findUsers.setDetailAddress(users.getDetailAddress()); // 상세 주소
		findUsers.setEmail(users.getEmail()); // 이메일
		findUsers.setBlackYn(users.getBlackYn()); // 블랙 여부
		findUsers.setFinishedAuctionCnt(users.getFinishedAuctionCnt()); // 블럭 낙찰 횟수
		findUsers.setNickName(users.getNickName()); // 닉네임
		findUsers.setPoint(users.getPoint()); // 포인트
		findUsers.setReservationCnt(users.getReservationCnt()); // 블럭 예약 횟수
		findUsers.setTel(users.getTel()); // 전화번호
		findUsers.setTotalMoney(users.getTotalMoney()); // 총 결제 금액
		findUsers.setUserPw(users.getUserPw()); // 비밀번호
		
		usersRepo.save(findUsers);
	}
	
	@Override
	public void deleteUsers(Users users) {
		usersRepo.deleteById(users.getUserId());
	}
	
	@Override
	public Users getUsers(Users users) {
		Users findUsers = usersRepo.findById(users.getUserId()).get();
		
		usersRepo.save(findUsers);
		
		return findUsers;
	}
	
	@Override
	public Users listUsers() {
		return (Users) usersRepo.findAll();
	}
	
	
}
