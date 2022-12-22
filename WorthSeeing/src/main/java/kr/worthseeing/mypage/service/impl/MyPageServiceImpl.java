package kr.worthseeing.mypage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockGroupWaiting.repository.BlockGroupWaitingRepository;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.blockgroup.repository.BlockGroupRepository;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.coupon.repository.CouponRepository;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.auction.repository.AuctionLogRepository;
import kr.worthseeing.main.auction.repository.AuctionRepository;
import kr.worthseeing.main.reservation.entity.Reservation;
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
	
	@Autowired
	private BlockGroupWaitingRepository blockGroupWaitingRepo;
	

	@Override
	public void getMyPage() {

	}

	@Override
	public void getClick(Users users) {
		Users findUsers = usersRepo.findById(users.getUserId()).get();

		findUsers.setPoint(findUsers.getPoint() + 500);
		findUsers.setDailyClickCheck("yes");
		
		usersRepo.save(findUsers);
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
	public void getUserPoint(Users users,String price) {
		Users findUsers = usersRepo.findById(users.getUserId()).get();
//		List<Coupon> findcoupon = (List<Coupon>) couponRepo.findCoupon(1);
		findUsers.setPoint(findUsers.getPoint() - Integer.parseInt(price));
		usersRepo.save(findUsers);
		System.out.println(findUsers);
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
	public List<BlockGroupWaiting> selectBlockGroupWaiting(String userId,int status_seq) {
		
		List<BlockGroupWaiting> findBlockWaiting =   blockGroupWaitingRepo.selectBlockGroupWaiting(userId);
		System.out.println("====>5"+findBlockWaiting);
		
		return findBlockWaiting;
	}
	
	
	

	@Override
	public List<BlockGroup> getListBlockGroup() {

		return (List<BlockGroup>) blockGroupRepo.findAll();
	}

}
