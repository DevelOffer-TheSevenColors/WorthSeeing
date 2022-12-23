package kr.worthseeing.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.worthseeing.admin.service.AdminService;
import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.repository.BlockRepository;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private BlockRepository blockRepo;
	
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

	   @Override
	   public List<Integer> blockChart(String startYear) {
	      int [] monthPrice = new int[12];
	      List<Integer> chartPrice = new ArrayList<Integer>();
	      for (Block block : blockRepo.findAll()) {
	         for (int i = 1; i <= 12; i++) {
	            String i_str = String.valueOf(i);
	            if (block.getEndDate() != null) {
	               SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
	               if (format.format(block.getEndDate()) != null) {
	                  if(i<=9)  i_str = "0"+i;
	                  if (format.format(block.getEndDate()).equals(startYear + i_str)) {
	                     monthPrice[i - 1] += block.getBlockPrice();
	                     System.out.println("@@==> enddate = " + block.getEndDate());
	                     System.out.println("@@==> index = " + (i - 1));
	                     System.out.println("@@==> price = " + block.getBlockPrice());
	                  }
	               }
	            }
	         }
	      }
	      for(int i=0;i<=3;i++) {
	         chartPrice.add(monthPrice[0+3*i]+monthPrice[1+3*i]+monthPrice[2+3*i]);
	      }
	      return chartPrice;
	   }
}
