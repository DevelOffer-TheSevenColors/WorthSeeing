package kr.worthseeing.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.worthseeing.admin.service.AdminService;
import kr.worthseeing.block.repository.BlockLogRepository;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	UsersRepository userRepo; 

	@Autowired
	BlockRepository blockRepo;
	
	@Autowired
	BlockLogRepository blockLogRepo;
	
	@Override
	public Page<Users> selectUsers(Pageable pageable) {

		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "joindate");

		return userRepo.findAll(pageable);
	}

	@Override
	public void blackList(List<String> userId, List<String> blackYn) {
		int i = 0;
		
		for (String userIdItem : userId) {
			Users findUsers = userRepo.findById(userIdItem).get();
			findUsers.setBlackYn(blackYn.get(i++));
			userRepo.save(findUsers);
		}

	}

}
