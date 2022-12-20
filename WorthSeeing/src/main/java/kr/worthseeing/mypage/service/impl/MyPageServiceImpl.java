package kr.worthseeing.mypage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.repository.CouponRepository;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.auction.repository.AuctionLogRepository;
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

	@Autowired
	private AuctionLogRepository auctionLogRepo;

	@Override
	public void getMyPage() {

	}

	@Override
	public void getClick(BlockGroup blockGroup, Users users) {
		BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
		Users findUsers = usersRepo.findById(users.getUserId()).get();
		
		findBlockGroup.setClickCnt(findBlockGroup.getClickCnt() + 1);
		findUsers.setDailyClick(findUsers.getDailyClick() + 1);
		
		blockGroupRepo.save(findBlockGroup);
		usersRepo.save(findUsers);

		if (findUsers.getDailyClick() == 10) {
			findUsers.setPoint(findUsers.getPoint() + 500);
			usersRepo.save(findUsers);
		}

	}

	@Override
	public List<BlockGroup> getBlockGroupUserId(String userId) {

		return (List<BlockGroup>) blockGroupRepo.findByUserId(userId);
	}

	@Override
	public List<Coupon> getCouponUserId(String userId) {

		return (List<Coupon>) couponRepo.findByUserId(userId);
	}

	@Override
	public Map<Integer, List<AuctionLog>> getAuctionLogUserId(String userId) {
		Map<Integer, List<AuctionLog>> auctionLogMap = new HashMap<Integer, List<AuctionLog>>();
		auctionLogMap.put(1, auctionLogRepo.findByUserId(userId, 1));
		auctionLogMap.put(2, auctionLogRepo.findByUserId(userId, 2));
		System.out.println("map--->" + auctionLogMap.get(1).toString());
		System.out.println("map--->" + auctionLogMap.get(2).toString());

		return auctionLogMap;
	}

	@Override
	public List<BlockGroup> getListBlockGroup() {

		return (List<BlockGroup>) blockGroupRepo.findAll();
	}

}
