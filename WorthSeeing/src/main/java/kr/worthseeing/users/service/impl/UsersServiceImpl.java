package kr.worthseeing.users.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.worthseeing.users.entity.Role;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;
import kr.worthseeing.users.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Users getUsers(Users users) {

		// null 인지 아닌지 if문을 쓰지않고 optional 클래스를 사용하여 null인지 확인가능, null이더라도 일단 메소드를 제공해줌
		Optional<Users> findUser = userRepo.findById(users.getUserId());
		if (findUser.isPresent()) // findMember 객체가 값을 가지고 있다면 true, findMember != null
			return findUser.get();
		else
			return null;
	}

	@Override
	@Transactional
	public void insertUsers(Users users) {
		users.setUserPw(encoder.encode(users.getUserPw()));
		users.setJoindate(new Date());
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
		findUser.setRole(Role.ROLE_USER);
		userRepo.save(findUser);
	}

	@Override
	public String findUser(Users user) {
		String flag = "회원정보가 다릅니다.";
		if (!user.getUserId().isEmpty()) {
			Optional<Users> userdb_op = userRepo.findById(user.getUserId());
			if (!userdb_op.isEmpty()) {
				Users userdb = userdb_op.get();
				if (userdb != null) {
					if (userdb.getName().equals(user.getName()) && userdb.getEmail().equals(user.getEmail())
							&& userdb.getUserId().equals(user.getUserId())) {
						String randomPW = "";
						for (int i = 0; i < 8; i++) {
							char randomPW_ = (char) ((int) (Math.random() * 25) + 97);
							randomPW += randomPW_;
						}
						flag = "회원님의 임시비밀번호는 " + randomPW + " 입니다.";
						userdb.setUserPw(encoder.encode(randomPW));
						userRepo.save(userdb);
					}
				}
			}
		} else {
			for (Users userdb : userRepo.findUser(user.getEmail())) {
				if (userdb.getName().equals(user.getName()) && userdb.getEmail().equals(user.getEmail())) {
					flag = "회원님의 아이디는 ' " + userdb.getUserId() + " ' 입니다.";
				}
			}
		}
		return flag;
	}

	@Override
	public void deleteUsers(Users user) {
		userRepo.deleteById(user.getUserId());
	}

	@Override
	public List<Users> listUsers() {
		List<Users> userList = (List<Users>) userRepo.findAll();
		return userList;
	}

}
