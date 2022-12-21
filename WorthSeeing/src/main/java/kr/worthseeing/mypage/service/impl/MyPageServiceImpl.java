package kr.worthseeing.mypage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.repository.CouponRepository;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.auction.repository.AuctionLogRepository;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.mypage.service.MyPageService;
import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.repository.UsersRepository;

@Service
@Component
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

//	@Override
//	public void getClick(BlockGroup blockGroup, Users users) {
//		BlockGroup findBlockGroup = blockGroupRepo.findById(blockGroup.getBlockGroup_seq()).get();
//		Users findUsers = usersRepo.findById(users.getUserId()).get();
//
//		findBlockGroup.setClickCnt(findBlockGroup.getClickCnt() + 1);
//		findUsers.setDailyClick(findUsers.getDailyClick() + 1);
//
//		blockGroupRepo.save(findBlockGroup);
//		usersRepo.save(findUsers);
//
//		if (findUsers.getDailyClick() == 10) {
//			findUsers.setPoint(findUsers.getPoint() + 500);
//			usersRepo.save(findUsers);
//		}
//
//	}

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
	public List<BlockGroupWaiting> selectBlockGroupWaiting(String userId) {
		
		
		return null;
	}

	
	
	
	@Scheduled(cron= "0 0 0 * * *", zone = "Asia/Seoul") //매일 자정
	public void updateUsersPoint() {
		usersRepo.updateUsersPoint();

//		updateUsers.setPoint(users.getPoint());
//		usersRepo.save(updateUsers);
		System.out.println("============>" + "10초마다 실행");
	}

//	@Scheduled(cron = "3 * * * * *", zone = "Asia/Seoul")
//	public void test() {
//		System.out.println("3초마다 실행");
//	}

	@Override
	public List<BlockGroup> getListBlockGroup() {

		return (List<BlockGroup>) blockGroupRepo.findAll();
	}


}
