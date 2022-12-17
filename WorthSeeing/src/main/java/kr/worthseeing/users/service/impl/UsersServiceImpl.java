package kr.worthseeing.users.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.worthseeing.users.entity.Role;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;
import kr.worthseeing.users.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Users getUsers(Users users) {
		
		// null 인지 아닌지 if문을 쓰지않고 optional 클래스를 사용하여 null인지 확인가능, null이더라도 일단 메소드를 제공해줌
		Optional<Users> findUser = userRepo.findById(users.getUserId());
		if(findUser.isPresent())	// findMember 객체가 값을 가지고 있다면 true, findMember != null
			return findUser.get();
		else 
			return null;
	}
	
	@Override
	@Transactional
	public void insertUsers(Users users) {
		users.setUserPw(encoder.encode(users.getUserPw()));
		userRepo.save(users);
	}

	@Override
	public void updateUsers(Users user) {
		Users findUser = userRepo.findById(user.getUserId()).get();
		findUser.setAddress(user.getAddress());
		findUser.setDetailAddress(user.getDetailAddress());
		findUser.setEmail(user.getEmail());
		findUser.setName(user.getName());
		findUser.setNickName(user.getNickName());
		findUser.setTel(user.getTel());
		findUser.setRole(Role.ROLE_MEMBER);
		userRepo.save(findUser);
	}

	@Override
	public void deleteUsers(Users user) {
		userRepo.deleteById(user.getUserId());
	}
	
	@Override
	public List<Users> listUsers(){
		List<Users> userList = (List<Users>) userRepo.findAll();
		return userList;
	}
	
}
