package kr.worthseeing.mypage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.repository.CouponRepository;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.mypage.service.MyPageService;
import kr.worthseeing.security.config.SecurityUser;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
public class MyPageServiceImpl implements MyPageService {
	
	@Autowired
	private BlockGroupRepository blockGroupRepo;
	
	@Autowired
	private UsersRepository usersRepo;
	
	@Autowired
	private CouponRepository couponRepo;
	
	@Autowired
	private AuctionRepository auctionRepo;

	@Override
	public void getMyPage() {
		
	}
	
	@Override
	public void getClick(BlockGroup blockGroup, @AuthenticationPrincipal SecurityUser principal) {
		BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
		Users findUsers = usersRepo.findById(principal.getUsers().getUserId()).get();
		findBlockGroup.setClickCnt(findBlockGroup.getClickCnt()+1);
		findUsers.setDailyClick(findUsers.getDailyClick()+1);
		blockGroupRepo.save(findBlockGroup);
		usersRepo.save(findUsers);
	}
	
	@Override
	public List<BlockGroup> getBlockGroupUserId(String userId) {
		
		return (List<BlockGroup>) blockGroupRepo.findByUserId(userId);
	}
	
	@Override
	public List<Coupon> getCouponUserId(String userId) {
		
		return (List<Coupon>)couponRepo.findByUserId(userId);
	}
	
	
	
	
	
	
	
	@Override
	public List<BlockGroup> getListBlockGroup() {
		
		return (List<BlockGroup>) blockGroupRepo.findAll();
	}
	

}
